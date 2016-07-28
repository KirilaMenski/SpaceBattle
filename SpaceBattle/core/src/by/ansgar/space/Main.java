package by.ansgar.space;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import by.ansgar.space.states.GameStateManager;
import by.ansgar.space.states.MenuState;

public class Main extends ApplicationAdapter {

    public static final String TITLE = "Space Battle";
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    private GameStateManager gsm;
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
