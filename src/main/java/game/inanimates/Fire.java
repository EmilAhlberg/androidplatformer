package game.inanimates;

import android.graphics.Point;

import game.draw.AnimationInfo;
import game.GameObject;
import game.util.GameTime;

/**
 * Created by Emil on 9/2/2017.
 */

public class Fire extends GameObject{


    public Fire(Point p, int nbrFires, boolean horizontal) {
        super(p, nbrFires, horizontal);
        animationType = AnimationInfo.DEFAULT;
        isActive = true;
    }



    @Override
    public void update(GameTime gameTime) {

    }
}
