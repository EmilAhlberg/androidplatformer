package game.inanimates;

import android.graphics.Point;

import game.draw.AnimationInfo;
import game.GameObject;
import game.objectinformation.Stats;
import game.util.GameTime;

/**
 * Created by Emil on 9/1/2017.
 */

public class Block extends GameObject {

    private double friction;


    public Block(Point p, int nbrBlocks, boolean horizontal) {
        super(p, nbrBlocks, horizontal);
        this.friction = Stats.friction(id);
        animationType = AnimationInfo.NO_ANIMATION;
        isActive = true;
    }

    @Override
    public void update(GameTime gameTime) {

    }

    public double getFriction () {
        return friction;
    }


}
