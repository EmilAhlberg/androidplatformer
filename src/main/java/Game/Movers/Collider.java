package Game.Movers;

import android.graphics.Rect;

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

    public Collider(Rect rect) {
        super(rect);
    }

    //https://gamedev.stackexchange.com/questions/74387/platformer-collision-detection-order
    // UNTESTED!!
    public int intersects(GameObject other) {
        Rect myRect = getRect();
        Rect oRect = other.getRect();


        if (Rect.intersects(myRect, oRect))   //statiska anropet?
        {
            // Calculate the vertical and horizontal
            // length between the centres of rectangles

            double hd = Math.abs((myRect.centerX() * myRect.centerX()) + (oRect.centerX() * oRect.centerX()));
            double vd = Math.abs((myRect.centerY() * myRect.centerY()) + (oRect.centerY() * oRect.centerY()));

            // Now compare them to know the side of collision

            if (hd < vd) {
                if (myRect.centerX() < oRect.centerX()) {
                    // Collision on right side of player
                    return COLLISION_RIGHT;
                } else {
                    // Collision on left side of player
                    return COLLISION_LEFT;
                }
            } else if (vd < hd) {
                if (myRect.centerY() < oRect.centerY()) {
                    // Collision on bottom side of player
                    return COLLISION_BOTTOM;
                } else {
                    // Collision on top side of player
                    return COLLISION_TOP;
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
        //TODO -handle block collisions (should be equal for all movers)
    }
}
