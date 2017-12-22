package game.gameObjects.movers;

import game.gameObjects.Collider;
import game.util.ID;
import game.gameObjects.GameObject;
import game.framework.util.GameTime;
import game.util.Vector;
import game.visuals.AnimationInfo;

/**
 * Created by Emil on 19/09/2017.
 */

public class Vacuum extends Collider {
    private static final double MOVE_TIMER = 1600;
    private GameObject ground;
    private final int TURN_FREQUENCY = 500; //! maybe a problem on small platforms
    private int direction = 1; //!! default move direction is right
    private int oldDirection = direction;
    private int turnCounter = TURN_FREQUENCY;
    private static final int VACUUM_FORCE = 12; //!
    private long lastTimeMoved;

    public Vacuum(Vector v) {
        super(v);
        animationType = AnimationInfo.VACUUM_RIGHT;
    }

    @Override
    public void update(GameTime gameTime) {
        if (direction != oldDirection) {
            lastTimeMoved = (long) gameTime.getCurrentTime();
            oldDirection = direction;
        }
        if(ground != null) {
            if ((ground.getRect().right <= rect.right || ground.getRect().left >=rect.left) && turnCounter < 0) {
                direction = direction *-1;
                turnCounter = TURN_FREQUENCY; // gets stuck on edges otherwise
            }
        }
        turnCounter -= gameTime.elapsedTime();
        if (gameTime.getCurrentTime() - lastTimeMoved > MOVE_TIMER) {
            applyForce((VACUUM_FORCE*direction*-2/3), 0);
            if(gameTime.getCurrentTime() - lastTimeMoved > MOVE_TIMER*3/2)
                lastTimeMoved = (long)gameTime.getCurrentTime();
        }
        else
            applyForce(VACUUM_FORCE*direction, 0);

        move(friction, true);
        if(direction == -1) {
            animationType = AnimationInfo.VACUUM_RIGHT;
        } else {
            animationType = AnimationInfo.VACUUM_LEFT;
        }
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
