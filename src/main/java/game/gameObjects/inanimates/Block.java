package game.gameObjects.inanimates;

import game.visuals.AnimationInfo;
import game.gameObjects.GameObject;
import game.util.Stats;
import game.framework.util.GameTime;
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
