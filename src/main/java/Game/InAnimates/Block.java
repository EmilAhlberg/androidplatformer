package Game.InAnimates;

import android.graphics.Point;
import android.graphics.Rect;

import Game.*;

/**
 * Created by Emil on 2016-11-22.
 */

public abstract class Block extends GameObject {

    public static final int BLOCK_WIDTH = 20;
    public static final int BLOCK_HEIGHT = 20;
    private double friction;

    public Block(Point p, double friction) {
        super(new Rect(p.x, p.y, p.x + BLOCK_WIDTH, p.y + BLOCK_HEIGHT));
        this.friction = friction;
    }

    public double getFriction () {
        return friction;
    }


}
