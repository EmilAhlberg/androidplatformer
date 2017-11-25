package Game.InAnimates;

import android.graphics.Point;

import Game.Draw.AnimationInfo;
import Game.GameObject;
import Game.Util.GameTime;

/**
 * Created by Emil on 9/2/2017.
 */

public class Fire extends GameObject{


    public Fire(Point p, int nbrFires, boolean horizontal) {
        super(p, nbrFires, horizontal);
        animationType = AnimationInfo.DEFAULT;
    }



    @Override
    public void update(GameTime gameTime) {

    }
}
