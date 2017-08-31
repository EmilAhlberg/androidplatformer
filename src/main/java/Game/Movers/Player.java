package Game.Movers;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;

import Game.Framework.GameDisplay;
import Game.GameObject;
import Game.InAnimates.Block;
import Game.Util.TouchEventDecoder;

/**
 * Created by Emil on 8/27/2017.
 */

public class Player extends Collider {

    private final int X_FORCE = 60;
    private final int Y_FORCE = 350;
    private final double WALLJUMP_FORCE = 400;
    private TouchEventDecoder ted;
    private Point clickPos;

    public Player(Point p) {
        super(new Rect(p.x, p.y, p.x + 15, p.y + 30));
        ted = new TouchEventDecoder(new Point(0,0), new Point(0, 0));
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        ted.decodeTouchEvent(event, p);
        clickPos = ted.getFirstClickPos();
    }

    @Override
    public void update() {
        performAction();
        mh.updateSpeed(friction, grounded);
        move(mh.horizontalSpeed, mh.verticalSpeed);
    }

    private void performAction() {
        int fingers = ted.getNbrFingersDown();
        if (fingers > 0) {
            double temp = clickPos.x - GameDisplay.WINDOW_WIDTH/2;
            mh.applyForce(X_FORCE * temp / Math.abs(temp), 0);
        }
        if (fingers > 1) {
            if (grounded) {
                jump(Y_FORCE);
                grounded = false;
                friction = 0.2;
            } else if (wallJumpDirection != 0) {
                mh.applyForce(WALLJUMP_FORCE * wallJumpDirection, -Y_FORCE * 2);
                friction = 0.2;
                ted.switchPositions();
                clickPos = ted.getFirstClickPos();
            }
        }
        wallJumpDirection = 0;
    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {

    }
}
