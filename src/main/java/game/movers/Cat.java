package game.movers;

import game.objectinformation.ID;
import game.GameObject;
import game.util.GameTime;
import game.util.Vector;

/**
 * Created by Emil on 19/09/2017.
 */

public class Cat extends Collider {
    private GameObject ground;
    private final int TURN_FREQUENCY = 300; //! maybe a problem on small platforms
    private int direction = 1; //!! default move direction is right
    private int turnCounter = TURN_FREQUENCY;
    private static final int CAT_FORCE = 10; //!

    public Cat(Vector v) {
        super(v);
    }

    @Override
    public void update(GameTime gameTime) {
        if(ground != null) {
            if ((ground.getRect().right <= rect.right || ground.getRect().left >=rect.left) && turnCounter < 0) {
                direction = direction *-1;
                turnCounter = TURN_FREQUENCY; // gets stuck on edges otherwise
            }
        }
        turnCounter -= gameTime.elapsedTime();
        applyForce(CAT_FORCE* direction, 0);
        move(friction, true);
    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {
        if (collisionType == COLLISION_BOTTOM && g.getID() == ID.BLOCK) {
            ground = g;
        } else if (collisionType == COLLISION_LEFT || collisionType == COLLISION_RIGHT) {
            direction = direction * -1;
        }

    }
}
