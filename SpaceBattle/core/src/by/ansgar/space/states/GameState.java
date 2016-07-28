package by.ansgar.space.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import by.ansgar.space.Main;
import by.ansgar.space.entity.Bullet;
import by.ansgar.space.entity.Enemy;
import by.ansgar.space.entity.Player;

/**
 * Created by kirila on 2.4.16.
 */
public class GameState extends State {

    /*Fields*/
    private Player player;
    private Texture bg;
    private Vector3 toush;
    private Vector2 bgPosition1, bgPosition2;
    private Array<Bullet> bullets, enemyBullets;
    private Array<Enemy> enemies;
    private long bulletLastTime;
    private int lvl;
    private BitmapFont wave, scoreStr;
    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;
    private long score;

    public GameState(GameStateManager gsm) {
        super(gsm);
        player = new Player(Main.WIDTH / 2, 0);
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        bg = new Texture("bg1.jpg");
        toush = new Vector3();
        bgPosition1 = new Vector2(0, camera.position.y - camera.viewportHeight / 2);
        bgPosition2 = new Vector2(0, (camera.position.y - camera.viewportHeight / 2) + bg.getHeight());
        bullets = new Array<Bullet>();
        enemyBullets = new Array<Bullet>();
        enemies = new Array<Enemy>();
        lvl = 1;
        wave = new BitmapFont();
        scoreStr = new BitmapFont();
        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;
        score = 0;
    }

    @Override
    protected void input() {
        if (Gdx.input.isTouched()) {
            toush.set(Gdx.input.getX(), 0, 0);
            camera.unproject(toush);
            player.getPlayerBounds().x = (int) toush.y - player.getPlayerImg().getWidth();
            player.getPosition().x = toush.x;
        }
    }

    @Override
    public void update(float dt) {
        input();
        camera.update();
        //Update background
        updateBackground();

        //Player update
        player.update(dt);

        //set camera position equals player position
        camera.position.y = player.getPosition().y + Main.HEIGHT / 2;

        //Update bullets
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).update(dt);
            if (bullets.get(i).getPosition().y > Main.HEIGHT) {
                bullets.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyBullets.size; i++) {
            enemyBullets.get(i).update(dt);
            if (enemyBullets.get(i).getPosition().y < 0) {
                enemyBullets.removeIndex(i);
            }
        }
        if (TimeUtils.nanoTime() - bulletLastTime > player.getHast() && enemies.size != 0)
            bulletGeneration();

        //Update enemies
        for (int i = 0; i < enemies.size; i++) {
            enemies.get(i).update(dt);

            if (enemies.get(i).collide(player.getPlayerBounds())) {

            }
            try {
                for (int j = 0; j < bullets.size; j++) {
                    if (enemies.get(i).collide(bullets.get(j).getBulletBounds())) {
                        enemies.get(i).setHealth(enemies.get(i).getHealth() - 1);
                        bullets.removeIndex(j);
                        if (enemies.get(i).getHealth() <= 0) {
                            enemies.removeIndex(i);
                            score += enemies.get(i).getHealth();
                        }
                    }
                }

                if (enemies.get(i).getPosition().y < 0) {
                    enemies.removeIndex(i);
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }


    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //Draw background
        batch.draw(bg, bgPosition1.x, bgPosition1.y);
        batch.draw(bg, bgPosition2.x, bgPosition2.y);
        //Draw player
        batch.draw(player.getPlayerImg(), player.getPosition().x, player.getPosition().y);

        //Draw wave
        if (enemies.size == 0 && waveTimer == 0) {
            waveTimer = System.nanoTime();
        }
        if (waveTimer > 0) {
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000;
            waveTimer = System.nanoTime();
            wave.draw(batch, "Wave № " + lvl, 200, 400);
        }
        if (waveTimerDiff > waveDelay) {
            generateEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;

        }

        //Draw enemies
        for (int i = 0; i < enemies.size; i++) {
            batch.draw(enemies.get(i).getEnemy(), enemies.get(i).getPosition().x,
                    enemies.get(i).getPosition().y);
        }

        //Draw score
        scoreStr.draw(batch, "Score: " + score, 10, 780);

        batch.end();

        //Draw player bullets
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).drawBullet();
        }
        for (int i = 0; i < enemyBullets.size; i++) {
            enemyBullets.get(i).drawBullet();
        }

    }

    //Generate bullets
    public void bulletGeneration() {
        bullets.add(new Bullet(player.getPosition().x + player.getPlayerImg().getWidth() / 2,
                player.getPlayerImg().getHeight(), 0));
        for (int i = 0; i < enemies.size; i++) {
            enemyBullets.add(new Bullet(enemies.get(i).getPosition().x + enemies.get(i).getEnemy().getWidth() / 2,
                    enemies.get(i).getPosition().y, 1));
        }
        bulletLastTime = TimeUtils.nanoTime();
    }

    //Generate enemies
    public void generateEnemies() {
        for (int i = 0; i < 10 * lvl; i++) {
            enemies.add(new Enemy(MathUtils.random(Main.WIDTH - 64), MathUtils.
                    random(player.getPosition().y + Main.HEIGHT, player.getPosition().y
                            + Main.HEIGHT + Main.HEIGHT * 2), MathUtils.random(1, 2 * lvl - lvl)));
        }
        lvl++;

    }

    public void updateBackground() {
        if (camera.position.y - (camera.viewportHeight / 2) > bgPosition1.y + bg.getHeight()) {
            bgPosition1.add(0, bg.getHeight() * 2);
        }
        if (camera.position.y - (camera.viewportHeight / 2) > bgPosition2.y + bg.getHeight()) {
            bgPosition2.add(0, bg.getHeight() * 2);
        }
    }

    @Override
    public void dispose() {
        player.dispose();
        bg.dispose();
        scoreStr.dispose();
        wave.dispose();
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}
