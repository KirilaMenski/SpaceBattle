package by.ansgar.space.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import by.ansgar.space.Main;

/**
 * Created by kirila on 2.4.16.
 */
public class Player {

    /*Fields*/
    private Texture playerImg;
    private Vector3 position;
    private Vector3 velocity;
    private int movement;
    private Rectangle playerBounds;
    private int hit;
    private long hast;
    private int health;

    public Player(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        playerImg = new Texture("player_ship.png");
        movement = 50;
        playerBounds = new Rectangle(position.x, position.y, playerImg.getWidth(), playerImg.getHeight());
        hast = 1000000000;
        health = 50;
    }

    /*Update player*/
    public void update(float dt) {
//        velocity.scl(dt);
        if (position.x < 0) position.x = 0;
        if (position.x > Main.WIDTH) position.x = Main.WIDTH - playerImg.getWidth();
//        position.add(0, movement * dt, 0);
//        velocity.scl(1 / dt);
        playerBounds.setPosition(position.x, position.y);

    }

    public void dispose() {
        playerImg.dispose();
    }

    public Texture getPlayerImg() {
        return playerImg;
    }

    public void setPlayerImg(Texture playerImg) {
        this.playerImg = playerImg;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public Rectangle getPlayerBounds() {
        return playerBounds;
    }

    public void setPlayerBounds(Rectangle playerBounds) {
        this.playerBounds = playerBounds;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public long getHast() {
        return hast;
    }

    public void setHast(long hast) {
        this.hast = hast;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
