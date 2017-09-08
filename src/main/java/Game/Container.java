package Game;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import Game.InAnimates.BigBlock;

/**
 * Created by Emil on 29/08/2017.
 */

public class Container {

    protected ArrayList<GameObject> gameObjects;

    public Container(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void update() {
        for (GameObject g : gameObjects)
            g.update();
    }
    public void draw(Canvas canvas, Rect pRect) {
        int radius = 933;
        for (GameObject g : gameObjects) {
            if (g instanceof BigBlock) {
//                ((BigBlock) g).drawSome(pRect, canvas, radius);
                g.draw(canvas);
            } else {
                Rect gRect = g.getRect();
                if (Math.sqrt((pRect.left - gRect.left)*(pRect.left - gRect.left) + (pRect.top - gRect.top)*(pRect.top - gRect.top)) < radius)
                    g.draw(canvas);
            }
        }
    }
    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }


}
