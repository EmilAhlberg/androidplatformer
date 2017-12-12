package game.framework;

import android.graphics.Rect;

import java.util.ArrayList;

import game.Container;
import game.GameObject;
import game.movers.Collider;
import game.movers.Player;

/**
 * Created by Emil on 29/08/2017.
 */

public class CollisionHandler {

    public void handleCollisions(Collider c, ArrayList<GameObject> gameObjects) {
        ArrayList<Pair> intersectingObjects = new ArrayList<>();
        for (GameObject g : gameObjects) {
            Rect gRect = g.getRect();
            Rect temp = new Rect(gRect.left, gRect.top, gRect.right, gRect.bottom);
            if (temp.intersect(c.getRect())) {
                int area = temp.width() * temp.height();
                if (intersectingObjects.size() == 0)
                    intersectingObjects.add(new Pair(g, area));
                int size = intersectingObjects.size();
                for (int i = 0; i < size; i++) {
                    if (intersectingObjects.get(i).area < area || i == size-1)
                        intersectingObjects.add(i, new Pair(g, area));
                }
            }
        }
        for (Pair g : intersectingObjects) {
            int collisionType = c.intersects(g.g);
            if (collisionType != Collider.COLLISION_NO) {
                c.collision(collisionType, g.g);
                // might want this:
                // g.collision(invertedCollisionType, c)
                // (bilateral collision response)
            }
        }
    }

    public void handleCollisions(ArrayList<GameObject> enemies, ArrayList<GameObject> blocks) {
        for (GameObject g : enemies) {
            handleCollisions((Collider) g, blocks);
        }
    }


    public void handleAllCollisions(Player player, Container blocks, Container hazards, Container interactives, Container enemies) {
        player.grounded = false;
        player.friction = 0.2; //!
        //player collisions
        handleCollisions(player, blocks.getObjects());
        handleCollisions(player, hazards.getObjects());
        handleCollisions(player, interactives.getObjects());
        handleCollisions(player, enemies.getObjects());
        //remaining enemy collisions
        handleCollisions(enemies.getObjects(), blocks.getObjects());

    }

    class Pair {
        public GameObject g;
        public int area;

        public Pair(GameObject g, int area) {
            this.g = g;
            this.area = area;
        }
    }
}
