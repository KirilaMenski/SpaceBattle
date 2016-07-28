package by.ansgar.space.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by kirila on 2.4.16.
 */
public class GameStateManager {

    private Stack <State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    /*This method pushed state to upper stack*/
    public void push(State state){
        states.push(state);
    }

    /*Delete state from stack, clean resources and pushed state in up*/
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    /*Update state*/
    public void update(float dt){
        states.peek().update(dt);
    }

    /*Render state*/
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }

}
