package Game.InAnimates;

import android.graphics.Point;
import android.graphics.Rect;

import Game.Framework.World;
import Game.GameObject;

/**
 * Created by Emil on 9/6/2017.
 */

public class Goal extends GameObject {

    public Goal(Point p) {
        super(p);
    }


    public void affectPlayer(World w) {
        w.nextLevel();
    }

    @Override
    public void update() {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
