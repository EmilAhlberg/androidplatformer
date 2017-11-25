package Game.InAnimates;

import android.graphics.Point;

import Game.Draw.AnimationInfo;
import Game.GameObject;
import Game.ObjectInformation.Stats;
import Game.Util.GameTime;

/**
 * Created by Emil on 9/1/2017.
 */

public class Block extends GameObject {

    private double friction;


    public Block(Point p, int nbrBlocks, boolean horizontal) {
        super(p, nbrBlocks, horizontal);
        this.friction = Stats.friction(id);
        animationType = AnimationInfo.NO_ANIMATION;
    }

    @Override
    public void update(GameTime gameTime) {

    }

    public double getFriction () {
        return friction;
    }


}
