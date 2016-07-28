package by.ansgar.space.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import by.ansgar.space.Main;

/**
 * Created by kirila on 2.4.16.
 */
public class MenuState extends State {

    /*Fields*/
    private static final String START = "Click to Start!";
    private BitmapFont font;
    private Texture bg;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        font = new BitmapFont();
        bg = new Texture("bg_menu.jpg");
    }

    @Override
    protected void input() {
        if (Gdx.input.isTouched()) {
            gsm.push(new GameState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        input();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bg, 0, 0);
        font.setColor(Color.GREEN);
        font.draw(batch, START, camera.viewportWidth / 2 - START.length() * 2,
                camera.viewportHeight - 200);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
