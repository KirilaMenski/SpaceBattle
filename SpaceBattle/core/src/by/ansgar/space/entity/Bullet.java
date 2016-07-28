package by.ansgar.space.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by kirila on 3.4.16.
 */
public class Bullet {

    /*Fields*/
    private ShapeRenderer bullet;
    private Vector3 position;
    private Vector3 velocity;
    private int movement;
    private Rectangle bulletBounds;
    private int radius;
    private Color color;
    private int obj;

    public Bullet(float x, float y, int obj) {
        this.obj = obj;
        bullet = new ShapeRenderer();
        radius = 3;
        bulletBounds = new Rectangle(x, y, radius, radius);
        switch (obj){
            case 0:
                position = new Vector3(x, y, 0);
                velocity = new Vector3(0, 0, 0);
                movement = 120;
                color = Color.GREEN;
                break;
            case  1:
                position = new Vector3(x, y, 0);
                velocity = new Vector3(0, 0, 0);
                movement = -120;
                color = Color.RED;
                break;
        }
    }

    /*Update bullet*/
    public void update(float dt) {
        velocity.scl(dt);
        position.add(0, movement * dt, 0);
        velocity.scl(1 / dt);
        bulletBounds.setPosition(position.x, position.y);
    }

    /*Draw bullet*/
    public void drawBullet() {
        bullet.setColor(color);
        bullet.begin(ShapeRenderer.ShapeType.Filled);
        bullet.circle(position.x, position.y, radius);
        bullet.end();
    }

    /*Dispose*/
    public void dispose() {
        bullet.dispose();
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
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

    public ShapeRenderer getBullet() {
        return bullet;
    }

    public void setBullet(ShapeRenderer bullet) {
        this.bullet = bullet;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Rectangle getBulletBounds() {
        return bulletBounds;
    }

    public void setBulletBounds(Rectangle bulletBounds) {
        this.bulletBounds = bulletBounds;
    }

    public int getObj() {
        return obj;
    }

    public void setObj(int obj) {
        this.obj = obj;
    }
}
