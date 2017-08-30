package Game.Movers;

import android.graphics.Rect;
import android.util.Log;

import Game.GameObject;
import Game.InAnimates.Block;
import Game.Util.IDs;

/**
 * Created by Emil on 29/08/2017.
 */

public abstract class Collider extends Mover {

    public static final int COLLISION_NO = -1;
    public static final int COLLISION_RIGHT = 1;
    public static final int COLLISION_LEFT = 2;
    public static final int COLLISION_BOTTOM = 3;
    public static final int COLLISION_TOP = 4;

    protected boolean grounded = false;

    public Collider(Rect rect) {
        super(rect);
    }

    public int intersects(GameObject other) {
        Rect myRect = getRect();
        Rect oRect = other.getRect();


        if (Rect.intersects(myRect, oRect))
        {
            Rect myTemp, oTemp;

            double hd = myRect.centerX() - oRect.centerX(); //Math.abs((myRect.centerX() * myRect.centerX()) + (oRect.centerX() * oRect.centerX())); wtf?
            double vd = myRect.centerY() - oRect.centerY(); //Math.abs((myRect.centerY() * myRect.centerY()) + (oRect.centerY() * oRect.centerY()));

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

            //TODO: Förstå hur dessa funkar och fixa dem, det är inte 100% rätt nu. UPDATE: Tror att det funkar nu, för alla rektanglar.

            // Calculate the vertical and horizontal
            // length between the centres of rectangles
            hd = Math.abs(myTemp.centerX() - oTemp.centerX());
            vd = Math.abs(myTemp.centerY() - oTemp.centerY());

            // Now compare them to know the side of collision
            if (hd < vd) {
                if (myTemp.centerY() > oTemp.centerY()) {
                    // Collision on top side of player
                    return COLLISION_TOP;
                } else {
                    // Collision on bottom side of player
                    return COLLISION_BOTTOM;
                }
            } else {
                if (myTemp.centerX() < oTemp.centerX()) {
                    // Collision on right side of player
                    return COLLISION_RIGHT;
                } else {
                    // Collision on left side of player
                    return COLLISION_LEFT;
                }
            }
        }
        return COLLISION_NO;
    }

    public void collision(int collisionType, GameObject g) {
        if (g.getID() == IDs.BLOCK)
            handleBlockCollision(collisionType, (Block)g);
        else
            handleCollision(collisionType, g);

    }

    public abstract void handleCollision(int collisionType, GameObject g);

    private void handleBlockCollision(int collisionType, Block g) {
        if (collisionType == COLLISION_BOTTOM) {
            grounded = true;
            moveTo(rect.left, g.getRect().top - rect.height());
            mh.verticalSpeed = 0;
        } else if (collisionType == COLLISION_LEFT) {
            //TODO: Change the value of some kind of variable related to walljump
            moveTo(g.getRect().right, rect.top);
            mh.horizontalSpeed = 0;
        } else if (collisionType == COLLISION_RIGHT) {
            //TODO: Change the value of some kind of variable related to walljump
            moveTo(g.getRect().left-rect.width(), rect.top);
            mh.horizontalSpeed = 0;
        }
        //TODO -handle block collisions (should be equal for all movers). Everything is not implemented
    }
}
