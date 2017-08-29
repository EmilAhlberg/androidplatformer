package Game.Util;

import Game.InAnimates.Block;

/**
 * Created by Emil on 8/29/2017.
 */

public class MovementHandler {

    private final int GRAVITY = 20;
    private final int MAX_SPEED = Block.BLOCK_HEIGHT/2;
    private double verticalForce, horizontalForce, horizontalAcceleration, verticalAcceleration;
    public double horizontalSpeed, verticalSpeed;

    public MovementHandler() {
        horizontalForce = horizontalAcceleration = verticalAcceleration = horizontalSpeed = verticalSpeed = 0;
        verticalForce = GRAVITY;
    }

    /**
     * Updates the horizontal and vertical speeds of the mover
     * @param friction Value between 0 and 1 that determines how slippery the ground is, where 0 is frictionless.
     */
    public void updateSpeed(double friction) {
        updateAcceleration();
        verticalForce = GRAVITY;
        horizontalForce = 0;
        verticalSpeed = verticalSpeed + verticalAcceleration;
        double temp = Math.abs(verticalSpeed);
        if (temp > MAX_SPEED)
            verticalSpeed = verticalSpeed/temp * MAX_SPEED;
        horizontalSpeed *= (1-friction);
        horizontalSpeed = horizontalSpeed + horizontalAcceleration;
        temp = Math.abs(horizontalSpeed);
        if (temp > MAX_SPEED)
            horizontalSpeed = horizontalSpeed/temp * MAX_SPEED;
    }

    private void updateAcceleration() {
        verticalAcceleration = verticalForce;
        horizontalAcceleration = horizontalForce;
    }

    public void applyForce(double horizontalChange, double verticalChange) {
        verticalForce += verticalChange;
        horizontalForce += horizontalChange;
    }
}
