package Game.Movers;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;

import Game.Framework.GameDisplay;
import Game.GameObject;
import Game.Util.TouchEventDecoder;

/**
 * Created by Emil on 8/27/2017.
 */

public class Player extends Collider {

    private final double X_FORCE = 2;
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
        performAction();
        mh.updateSpeed(0.95); //TODO: Fixa så att blocket man står på har en friktion som skickas in som parameter (typ låg friktion på is och i luft, hög på de vanliga blocken osv.)
        move(mh.horizontalSpeed, mh.verticalSpeed);
    }

    private void performAction() {
        int fingers = ted.getNbrFingersDown();
        if (fingers > 1) {

        } else if (fingers > 0) {
            double temp = clickPos.x - GameDisplay.WINDOW_WIDTH/2;
            mh.applyForce(X_FORCE * temp / Math.abs(temp), 0);
        }
    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {

    }
}
