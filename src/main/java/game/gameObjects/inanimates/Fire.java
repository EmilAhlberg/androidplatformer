package game.gameObjects.inanimates;

import game.visuals.AnimationInfo;
import game.gameObjects.GameObject;
import game.framework.util.GameTime;
import game.util.Vector;

/**
 * Created by Emil on 9/2/2017.
 */

public class Fire extends GameObject{


    public Fire(Vector v, int nbrFires, boolean horizontal) {
        super(v, nbrFires, horizontal);
        animationType = AnimationInfo.DEFAULT;
        isActive = true;
        sprite.animationThreshold = 80;
    }



    @Override
    public void update(GameTime gameTime) {

    }
}
