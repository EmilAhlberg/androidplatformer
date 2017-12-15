package game.movers;

import android.graphics.Point;

import game.*;
import game.util.MovementHandler;


/**
 * Created by Emil on 2016-11-22.
 */

public abstract class Mover extends GameObject {

    protected MovementHandler mh;

    public Mover(Point p) {
        super(p);
        mh = new MovementHandler();
    }

    /**
     * Makes the mover jump
     * @param force The force of the jump, the higher the value the higher the mover jumps
     */
    protected void jump (double force) {
        mh.applyForce(0, -force);
    }
}