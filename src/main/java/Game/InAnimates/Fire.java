package Game.InAnimates;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Emil on 9/2/2017.
 */

public class Fire extends Hazard {

    public Fire(Point p) {
        super(new Rect(p.x, p.y, p.x + 20, p.y + 16));
    }

    @Override
    public void update() {

    }
}
