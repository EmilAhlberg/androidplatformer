package Game.Framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import Game.GameObject;
import Game.Movers.Player;

/**
 * Created by Emil on 8/27/2017.
 */

public class World {

    private Player player;
    private GameDisplay display;
    private ArrayList<GameObject> objects;

    public World(ArrayList<GameObject> objects, GameDisplay display) {
        player = (Player)objects.get(0);
        this.objects = objects;
        this.display = display;
    }

    public void updateWorld() {

    }

    public void drawWorld() {
        Rect r = player.getRect();
        display.beginDraw(new Point(r.left, r.top));
        Canvas c = display.getCanvas();
        for (GameObject g : objects) {
            g.draw(c);
        }
        display.endDraw();
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
