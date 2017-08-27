package Game.Framework;

import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;

import Game.GameObject;
import Game.Movers.Player;

/**
 * Created by Emil on 8/27/2017.
 */

public class World {

    private Player player;

    private ArrayList<GameObject> objects;

    public World(ArrayList<GameObject> objects) {
        player = (Player)objects.get(0);
    }

    public void updateWorld() {

    }

    public void drawWorld() {

    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
