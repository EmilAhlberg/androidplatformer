package game.movers;

import android.graphics.Point;

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
     * @param position x-coordinate and y-coordinate in vector
     */
    public void moveTo(Vector position) {
        rect.offset((int)(position.X - rect.left), (int)(position.Y - rect.top));
    }

    /**
     * Moves the gameObject a certain distance
     * @param x horizontal distance
     * @param y vertical distance
     */
    public void move(float x, float y) {       // SHOULD BE REMOVED ASAP!
        x = Math.round(x);
        y = Math.round(y);
        rect.offset((int)x, (int)y);
    }

    public void move(float friction) {
        updateSpeed(friction);
        rect.offset((int)speed.X, (int)speed.Y);
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
    private void updateSpeed(float friction) {
        updateAcceleration();
        force.set(0, GRAVITY);
        updateVerticalSpeed();
        updateHorizontalSpeed(friction);
    }

    private void updateHorizontalSpeed(float friction) {
        speed.X = speed.X* (1 - friction) + acceleration.X;
        float absSpeed = Math.abs(speed.X);
        if (absSpeed > MAX_SPEED.X /*&& grounded*/)
            speed.X = speed.X / absSpeed * MAX_SPEED.X;
    }

    private void updateVerticalSpeed() {
        speed.Y = speed.Y+ acceleration.Y;
        float absSpeed = Math.abs(speed.Y);
        if (absSpeed > MAX_SPEED.Y)
            speed.Y = speed.Y / absSpeed * MAX_SPEED.Y;
    }

    private void updateAcceleration() {
        acceleration.set(new Vector(force.X/30, force.Y/30));
    }

    public void applyForce(float x, float y) {
        force.X += x;
        force.Y += y;
    }
}
