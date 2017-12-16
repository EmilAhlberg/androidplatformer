package game.inanimates;

import android.graphics.Point;

import game.draw.AnimationInfo;
import game.GameObject;
import game.objectinformation.Stats;
import game.util.GameTime;
import game.util.Vector;

/**
 * Created by Emil on 9/1/2017.
 */

public class Block extends GameObject {

    private float friction;


    public Block(Vector v, int nbrBlocks, boolean horizontal) {
        super(v, nbrBlocks, horizontal);
        this.friction = Stats.friction(id);
        animationType = AnimationInfo.NO_ANIMATION;
        isActive = true;
    }

    @Override
    public void update(GameTime gameTime) {

    }

    public float getFriction () {
        return friction;
    }


}
