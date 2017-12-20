package game.inanimates;

import android.graphics.Point;

import game.draw.AnimationInfo;
import game.GameObject;
import game.util.GameTime;
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
