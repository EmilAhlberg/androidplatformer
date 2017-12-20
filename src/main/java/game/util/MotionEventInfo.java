package game.util;

import android.graphics.Point;
import android.view.MotionEvent;

/**
 * Created by Emil on 20/12/2017.
 */

public class MotionEventInfo {
    private  MotionEvent e;
    private Point p;

    public MotionEventInfo(MotionEvent e, Point p) {
        this.e = e;
        this.p = p;
    }

    public MotionEvent getEvent() {
        return e;
    }

    public Point getPoint() {
        return p;
    }
}
