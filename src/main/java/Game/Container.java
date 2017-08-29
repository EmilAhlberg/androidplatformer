package Game;

import android.graphics.Canvas;

import java.util.ArrayList;

import Game.InAnimates.Block;
import Game.Util.IDs;

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
    public void draw(Canvas canvas) {
        for (GameObject g : gameObjects)
            g.draw(canvas);
    }
    public ArrayList<GameObject> getColliders() {
        return gameObjects;
    }


}
