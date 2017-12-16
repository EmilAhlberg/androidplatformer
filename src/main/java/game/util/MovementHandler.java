//package game.util;
//
//import game.objectinformation.ID;
//import game.objectinformation.Stats;
//
///**
// * Created by Emil on 8/29/2017.
// */
//
//public class MovementHandler {
//
//    private final int GRAVITY = 20;
//    private final int MAX_HORIZONTAL_SPEED = Stats.height(ID.BLOCK)/3;
//    private final int MAX_VERTICAL_SPEED = Stats.height(ID.BLOCK) * 2/3;
//    private double verticalForce, horizontalForce, horizontalAcceleration, verticalAcceleration;
//    public double horizontalSpeed, verticalSpeed;
//
//    public MovementHandler() {
//        horizontalForce = horizontalAcceleration = verticalAcceleration = horizontalSpeed = verticalSpeed = 0;
//        verticalForce = GRAVITY;
//    }
//
//    /**
//     * Updates the horizontal and vertical speeds of the mover
//     * @param friction Value between 0 and 1 that determines how slippery the ground is, where 0 is frictionless.
//     */
//    public void updateSpeed(double friction, boolean grounded) {
//        updateAcceleration();
//        verticalForce = GRAVITY;
//        horizontalForce = 0;
//        verticalSpeed = verticalSpeed + verticalAcceleration;
//        double temp = Math.abs(verticalSpeed);
//        if (temp > MAX_VERTICAL_SPEED)
//            verticalSpeed = verticalSpeed/temp * MAX_VERTICAL_SPEED;
//        horizontalSpeed *= (1-friction);
//        horizontalSpeed = horizontalSpeed + horizontalAcceleration;
//        temp = Math.abs(horizontalSpeed);
//        if (temp > MAX_HORIZONTAL_SPEED /*&& grounded*/)
//                horizontalSpeed = horizontalSpeed / temp * MAX_HORIZONTAL_SPEED;
//        /*else if (temp > MAX_HORIZONTAL_SPEED * 4)
//                horizontalSpeed = horizontalSpeed / temp * MAX_HORIZONTAL_SPEED * 4;*/
//    }
//
//    private void updateAcceleration() {
//        verticalAcceleration = verticalForce / 30;
//        horizontalAcceleration = horizontalForce / 30;
//    }
//
//    public void applyForce(double horizontalChange, double verticalChange) {
//        verticalForce += verticalChange;
//        horizontalForce += horizontalChange;
//    }
//}
