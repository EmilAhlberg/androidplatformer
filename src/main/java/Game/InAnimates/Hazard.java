package Game.InAnimates;

import android.graphics.Rect;

import Game.GameObject;

/**
 * Created by Emil on 9/2/2017.
 */

public abstract class Hazard extends GameObject{

    //A hazard is always static, moving hazards are enemies

    public Hazard(Rect rect) {
        super(rect);
    }
}
