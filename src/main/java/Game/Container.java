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
        int radius = 500/*933*/;
        for (GameObject g : gameObjects) {
            if (g instanceof BigBlock) {
                ((BigBlock) g).drawSome(pRect, canvas, radius);
            } else {
                Rect gRect = g.getRect();
                if (Math.sqrt(Math.pow(pRect.left - gRect.left, 2) + Math.pow(pRect.top - gRect.top, 2)) < radius)
                    g.draw(canvas);
            }
        }
    }
    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }


}
