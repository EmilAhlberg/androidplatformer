package game.movers;

import game.*;
import game.framework.World;
import game.objectinformation.ID;
import game.objectinformation.Stats;
import game.util.Vector;


/**
 * Created by Emil on 2016-11-22.
 */

public abstract class Mover extends GameObject {
    private final int GRAVITY = 20;
    private final Vector MAX_SPEED = new Vector(Stats.height(ID.BLOCK)/3, Stats.height(ID.BLOCK) * 2/3);
    private Vector force =  new Vector(0, GRAVITY);
    private Vector acceleration = new Vector();
    protected Vector speed = new Vector();

    public Mover(Vector v) {
        super(v);
    }

    public void deactivateMover() {
        isActive = false;
        force.set(0,0);
        speed.set(0,0);
        acceleration.set(0,0);
        moveTo(World.DEFAULT_POSITION);
    }

    /**
     * Moves the gameObject to the coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void moveTo(double x, double y) {
        rect.offset((int)(x - rect.left), (int)(y - rect.top));
    }

    /**
     * Moves the gameObject to the coordinates
     * @param v vector position
     */
    public void moveTo(Vector v) {
       moveTo(v.x, v.y);
    }

    /**
     * Frictionless moving
     */
    public void move(boolean affectedByGravity) {
        move(0, affectedByGravity);
    }

    /**
     * Moves the entity.
     * @param friction affects horizontal movement, where 0 is no friction, 1 is 100% friction.
     */
    public void move(float friction, boolean affectedByGravity) {
        updateSpeed(friction, affectedByGravity);
        rect.offset((int)speed.x, (int)speed.y);
    }

    /**
     * Makes the mover jump
     * @param force The force of the jump, the higher the value the higher the mover jumps
     */
    protected void jump (float force) {
        applyForce(0, -force);
    }


    /*
     * Updates the horizontal and vertical speeds of the mover.     *
     * @param friction Value between 0 and 1 that determines how slippery the ground is, where 0 is frictionless.
     */
    private void updateSpeed(float friction, boolean affectedByGravity) {
        updateAcceleration();
        force.set(0, 0);
        if (affectedByGravity)
            force.y = GRAVITY;
        updateVerticalSpeed();
        updateHorizontalSpeed(friction);
    }

    private void updateHorizontalSpeed(float friction) {
        speed.x = speed.x * (1 - friction) + acceleration.x;
        float absSpeed = Math.abs(speed.x);
        if (absSpeed > MAX_SPEED.x /*&& grounded*/)
            speed.x = speed.x / absSpeed * MAX_SPEED.x;
    }

    private void updateVerticalSpeed() {
        speed.y = speed.y + acceleration.y;
        float absSpeed = Math.abs(speed.y);
        if (absSpeed > MAX_SPEED.y)
            speed.y = speed.y / absSpeed * MAX_SPEED.y;
    }

    private void updateAcceleration() {
        acceleration.set(new Vector(force.x /30, force.y /30));
    }

    public void applyForce(float x, float y) {
        force.x += x;
        force.y += y;
    }
}
