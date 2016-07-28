package by.ansgar.space.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import by.ansgar.space.Main;

/**
 * Created by kirila on 2.4.16.
 */
public class Enemy {

    private Vector3 position;
    private Vector3 velocity;
    private Texture enemy;
    private Rectangle enemyBounds;
    private int health;
    private int hit;
    private long hast;
    private int movement;
    private int dx;

    public Enemy(float x, float y, int enemyLvl) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        switch (enemyLvl) {
            case 1:
                enemy = new Texture("enemy_1.png");
                health = 2;
                hit = 5;
                hast = 500000000;
                enemyBounds = new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
                movement = -50;
                break;
            case 2:
                enemy = new Texture("enemy_2.png");
                health = 5;
                hit = 10;
                hast = 700000000;
                enemyBounds = new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
                movement = -30;
                break;
            case 3:
                enemy = new Texture("enemy_3.png");
                health = 7;
                hit = 30;
                hast = 700000000;
                enemyBounds = new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
                movement = -50;
                break;
            case 4:
                enemy = new Texture("enemy_4.png");
                health = 5;
                hit = 10;
                hast = 700000000;
                enemyBounds = new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
                movement = -70;
                break;
            case 5:
                enemy = new Texture("enemy_5.png");
                health = 6;
                hit = 15;
                hast = 700000000;
                enemyBounds = new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
                movement = -10;
                break;
            case 6:
                enemy = new Texture("enemy_6.png");
                health = 20;
                hit = 10;
                hast = 700000000;
                enemyBounds = new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
                movement = -10;
                break;
        }
        double angle = Math.toRadians(Math.random() * 360);
        dx = (int) (Math.sin(angle) * 2);

    }

    public void update(float dt) {
        position.x+=dx;
        if (position.x < 0) dx = -dx;
        if (position.x > Main.WIDTH - enemy.getWidth()) dx = -dx;
        velocity.scl(dt);
        position.add(0, movement * dt, 0);
        velocity.scl(1 / dt);
        enemyBounds.setPosition(position.x, position.y);
    }

    public boolean collide(Rectangle rectangle) {
        return enemyBounds.overlaps(rectangle);
    }

    public void dispose() {
        enemy.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public long getHast() {
        return hast;
    }

    public void setHast(long hast) {
        this.hast = hast;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle getEnemyBounds() {
        return enemyBounds;
    }

    public void setEnemyBounds(Rectangle enemyBounds) {
        this.enemyBounds = enemyBounds;
    }

    public Texture getEnemy() {
        return enemy;
    }

    public void setEnemy(Texture enemy) {
        this.enemy = enemy;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}
