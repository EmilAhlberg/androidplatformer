package Game;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

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
        for (GameObject g : gameObjects) {
            Rect gRect = g.getRect();
            if (Math.sqrt(Math.pow(pRect.left - gRect.left, 2) + Math.pow(pRect.top - gRect.top, 2)) < 500/*933*/)
                g.draw(canvas);
        }
    }
    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }


}
