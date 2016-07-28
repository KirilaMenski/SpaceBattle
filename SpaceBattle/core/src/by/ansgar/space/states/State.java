package by.ansgar.space.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kirila on 2.4.16.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
    }

    protected abstract void input();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();

}
