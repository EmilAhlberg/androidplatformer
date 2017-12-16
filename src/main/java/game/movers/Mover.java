package game.movers;

import android.graphics.Point;

import game.*;
import game.framework.World;
import game.objectinformation.ID;
import game.objectinformation.Stats;
import game.util.MovementHandler;


/**
 * Created by Emil on 2016-11-22.
 */

public abstract class Mover extends GameObject {

    //protected MovementHandler mh;

    private final int GRAVITY = 20;
    private final int MAX_HORIZONTAL_SPEED = Stats.height(ID.BLOCK)/3;
    private final int MAX_VERTICAL_SPEED = Stats.height(ID.BLOCK) * 2/3;
    private double verticalForce, horizontalForce, horizontalAcceleration, verticalAcceleration;
    public double horizontalSpeed, verticalSpeed;

    public Mover(Point p) {
        super(p);
        //mh = new MovementHandler();
        horizontalForce = horizontalAcceleration = verticalAcceleration = horizontalSpeed = verticalSpeed = 0;
        verticalForce = GRAVITY;
    }

    public void deactivate() {
        isActive = false;
        moveTo(World.DEFAULT_POSITION.x, World.DEFAULT_POSITION.y);
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
     * Moves the gameObject a certain distance
     * @param x horizontal distance
     * @param y vertical distance
     */
    public void move(double x, double y) {                              // typisk movermetod?
        x = Math.round((float)x);
        y = Math.round((float)y);
        rect.offset((int)x, (int)y);
    }

    /**
     * Makes the mover jump
     * @param force The force of the jump, the higher the value the higher the mover jumps
     */
    protected void jump (double force) {
        applyForce(0, -force);
    }


    /**
     * Updates the horizontal and vertical speeds of the mover
     *
     * @param friction Value between 0 and 1 that determines how slippery the ground is, where 0 is frictionless.
     */
    public void updateSpeed(double friction, boolean grounded) {
        updateAcceleration();
        verticalForce = GRAVITY;
        horizontalForce = 0;
        verticalSpeed = verticalSpeed + verticalAcceleration;
        double temp = Math.abs(verticalSpeed);
        if (temp > MAX_VERTICAL_SPEED)
            verticalSpeed = verticalSpeed / temp * MAX_VERTICAL_SPEED;
        horizontalSpeed *= (1 - friction);
        horizontalSpeed = horizontalSpeed + horizontalAcceleration;
        temp = Math.abs(horizontalSpeed);
        if (temp > MAX_HORIZONTAL_SPEED /*&& grounded*/)
            horizontalSpeed = horizontalSpeed / temp * MAX_HORIZONTAL_SPEED;
        /*else if (temp > MAX_HORIZONTAL_SPEED * 4)
                horizontalSpeed = horizontalSpeed / temp * MAX_HORIZONTAL_SPEED * 4;*/
    }

    private void updateAcceleration() {
        verticalAcceleration = verticalForce / 30;
        horizontalAcceleration = horizontalForce / 30;
    }

    public void applyForce(double horizontalChange, double verticalChange) {
        verticalForce += verticalChange;
        horizontalForce += horizontalChange;
    }






}
