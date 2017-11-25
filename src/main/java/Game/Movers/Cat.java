package Game.Movers;

import android.graphics.Point;

import Game.ObjectInformation.ID;
import Game.GameObject;
import Game.Util.GameTime;

/**
 * Created by Emil on 19/09/2017.
 */

public class Cat extends Collider {
    private GameObject ground;
    private int tempSpeed = 1;
    private int fakeGravity = 1;

    public Cat(Point p) {
        super(p);
    }

    @Override
    public void update(GameTime gameTime) {
        if(ground != null) {
            if (ground.getRect().right <= rect.right || ground.getRect().left >=rect.left)
                tempSpeed = tempSpeed*-1;
        }
        //mh.applyForce(10, 0);
        //mh.updateSpeed(friction, grounded);
        move(tempSpeed, fakeGravity);
    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {
        if (collisionType == COLLISION_BOTTOM && g.getID() == ID.BLOCK) {
            ground = g;
        } else if (collisionType == COLLISION_LEFT || collisionType == COLLISION_RIGHT) {
            tempSpeed = tempSpeed * -1;
        }

    }
}
