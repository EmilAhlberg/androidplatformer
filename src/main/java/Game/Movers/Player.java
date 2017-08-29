package Game.Movers;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;

import Game.GameObject;
import Game.Util.TouchEventDecoder;

/**
 * Created by Emil on 8/27/2017.
 */

public class Player extends Collider {

    private TouchEventDecoder ted;
    private Point clickPos;

    public Player(Point p) {
        super(new Rect(p.x, p.y, p.x + 20, p.y + 20));
        ted = new TouchEventDecoder(new Point(0,0), new Point(0, 0));
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        ted.decodeTouchEvent(event, p);
        clickPos = ted.getFirstClickPos();
    }

    @Override
    public void update() {

    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {

    }
}
