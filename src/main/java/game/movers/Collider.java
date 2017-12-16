package game.movers;

import android.graphics.Point;
import android.graphics.Rect;

import game.objectinformation.ID;
import game.GameObject;
import game.inanimates.Block;

/**
 * Created by Emil on 29/08/2017.
 */

public abstract class Collider extends Mover {

    public static final int COLLISION_NO = -1;
    public static final int COLLISION_RIGHT = 1;
    public static final int COLLISION_LEFT = 2;
    public static final int COLLISION_BOTTOM = 3;
    public static final int COLLISION_TOP = 4;

    protected int wallJumpDirection;
    public double friction;
    public boolean grounded;

    public Collider(Point p) {
        super(p);
        wallJumpDirection = 0;
        friction = 0.2;
        grounded = false;
    }

    /**
     * Check if this "Collider" intersects with another GameObject
     * @param other The object to check intersection with
     * @return An integer representing the kind of intersection
     */
    public int intersects(GameObject other) {
        Rect myRect = getRect();
        Rect oRect = other.getRect();

        if (Rect.intersects(myRect, oRect))
        {
            Rect myTemp, oTemp;

            double hd = myRect.centerX() - oRect.centerX();
            double vd = myRect.centerY() - oRect.centerY();

            int width = myRect.width(), height = myRect.height();

            //Squares the first rectangle
            int temp = height-width;
            if (temp < 0) { //Wider than it's tall
                if (vd < 0) {
                    myTemp = new Rect(myRect.left, myRect.bottom-width, myRect.right, myRect.bottom);
                } else {
                    myTemp = new Rect(myRect.left, myRect.top, myRect.right, myRect.top+width);
                }
            } else if (temp > 0) { //Taller than it's wide
                if (hd < 0) {
                    myTemp = new Rect(myRect.right-height, myRect.top, myRect.right, myRect.bottom);
                } else {
                    myTemp = new Rect(myRect.left, myRect.top, myRect.left+height, myRect.bottom);
                }
            } else
                myTemp = myRect;

            width = oRect.width();
            height = oRect.height();

            //Squares the second rectangle
            temp = height-width;
            if (temp < 0) { //Wider than it's tall
                if (vd > 0) {
                    oTemp = new Rect(oRect.left, oRect.bottom-width, oRect.right, oRect.bottom);
                } else {
                    oTemp = new Rect(oRect.left, oRect.top, oRect.right, oRect.top+width);
                }
            } else if (temp > 0) { //Taller than it's wide
                if (hd > 0) {
                    oTemp = new Rect(oRect.right-height, oRect.top, oRect.right, oRect.bottom);
                } else {
                    oTemp = new Rect(oRect.left, oRect.top, oRect.left+height, oRect.bottom);
                }
            } else
                oTemp = oRect;

            // Calculate the vertical and horizontal
            // length between the centres of rectangles
            hd = Math.abs(myTemp.centerX() - oTemp.centerX());
            vd = Math.abs(myTemp.centerY() - oTemp.centerY());

            // Now compare them to know the side of collision
            if (hd < vd) {
                if (myTemp.centerY() > oTemp.centerY()) {
                    // Collision on top side of obj_debug
                    return COLLISION_TOP;
                } else {
                    // Collision on bottom side of obj_debug
                    return COLLISION_BOTTOM;
                }
            } else {
                if (myTemp.centerX() < oTemp.centerX()) {
                    // Collision on right side of obj_debug
                    return COLLISION_RIGHT;
                } else {
                    // Collision on left side of obj_debug
                    return COLLISION_LEFT;
                }
            }
        }
        return COLLISION_NO;
    }

    public void collision(int collisionType, GameObject g) {
        if (g instanceof Block) { //typ
            handleBlockCollision(collisionType, (Block) g);
            if (id == ID.CAT)
                handleCollision(collisionType, g);
        }
        else
            handleCollision(collisionType, g);

    }

    public abstract void handleCollision(int collisionType, GameObject g);

    private void handleBlockCollision(int collisionType, Block g) {
        if (collisionType == COLLISION_BOTTOM) {
            grounded = true;
            moveTo(rect.left, g.getRect().top - rect.height()+1);
            verticalSpeed = 0;
            friction = g.getFriction();
        } else if (collisionType == COLLISION_TOP) {
            moveTo(rect.left, g.getRect().bottom);
            verticalSpeed = 0;
        } else if (collisionType == COLLISION_LEFT) {
            wallJumpDirection = 1;
            moveTo(g.getRect().right, rect.top);
            horizontalSpeed = 0;
        } else if (collisionType == COLLISION_RIGHT) {
            wallJumpDirection = -1;
            moveTo(g.getRect().left-rect.width(), rect.top);
            horizontalSpeed = 0;
        }
    }
}
