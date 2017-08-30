package Game.InAnimates;

import android.graphics.Point;
import android.graphics.Rect;

import Game.*;

/**
 * Created by Emil on 2016-11-22.
 */

public class Block extends GameObject {

    public static final int BLOCK_WIDTH = 18;
    public static final int BLOCK_HEIGHT = 18;
    private final double FRICTION = 0.2;

    public Block(Point p) {
        super(new Rect(p.x, p.y, p.x + BLOCK_WIDTH, p.y + BLOCK_HEIGHT));
    }

    public double getFriction () {
        return FRICTION;
    }

    @Override
    public void update() {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
