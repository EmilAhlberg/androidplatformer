package Game.InAnimates;

import android.graphics.Point;
import android.graphics.Rect;

import Game.*;

/**
 * Created by Emil on 2016-11-22.
 */

public abstract class Block extends GameObject {

    private double friction;

    public Block(Rect rect, double friction) {
        super(rect);
        this.friction = friction;
    }

    public double getFriction () {
        return friction;
    }


}
