package Game.Framework;

import java.util.ArrayList;

import Game.Container;
import Game.GameObject;
import Game.Movers.Collider;
import Game.Movers.Mover;
import Game.Movers.Player;

/**
 * Created by Emil on 29/08/2017.
 */

public class CollisionHandler {

    public static void handleCollisions(Collider c, ArrayList<GameObject> gameObjects) {
        for (GameObject g : gameObjects) {
            int collisionType = c.intersects(g);
            if (collisionType != Collider.COLLISION_NO) {
                c.collision(collisionType, g);
                // might want this:
                // g.collision(invertedCollisionType, c)
                // (bilateral collision response)
            }
        }
    }

    public static void handleCollisions(ArrayList<GameObject> enemies, ArrayList<GameObject> blocks) {
        for (GameObject g : enemies) {
            handleCollisions((Collider) g, blocks);
        }
    }


    public static void handleAllCollisions(Player player, Container blocks) {
        CollisionHandler.handleCollisions(player, blocks.getObjects());
        //when enemies are implemented
        //CollisionHandler.handleAllCollisions(player, enemies);
        //CollisionHandler.handleAllCollisions(enemies, blocks);

    }
}
