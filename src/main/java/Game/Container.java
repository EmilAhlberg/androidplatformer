package Game;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import Game.Util.GameTime;

/**
 * Created by Emil on 29/08/2017.
 */

public class Container {

    protected ArrayList<GameObject> gameObjects;

    public Container(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void update(GameTime gameTime) {
        for (GameObject g : gameObjects)
            g.update(gameTime);
    }
    public void draw(Canvas canvas, GameTime gameTime, Rect pRect) {
        int radius = 933;
        for (GameObject g : gameObjects) {
//            if (g instanceof MapObject) {
                g.draw(canvas, gameTime);
//            } else {
//                Rect gRect = g.getRect();
//                if (Math.sqrt((pRect.left - gRect.left)*(pRect.left - gRect.left) + (pRect.top - gRect.top)*(pRect.top - gRect.top)) < radius)
//                    g.draw(canvas);
//            }
        }
    }
    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }


}
