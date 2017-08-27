package Game.Movers;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Emil on 8/27/2017.
 */

public class Player extends Mover {

    public Player(Point p) {
        super(new Rect(p.x, p.y, p.x + 20, p.y + 20));
    }
}
