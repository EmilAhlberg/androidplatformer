package Game.InAnimates;

import android.graphics.Point;
import android.graphics.Rect;

import Game.Framework.World;

/**
 * Created by Emil on 9/6/2017.
 */

public class Goal extends Interactive {

    public Goal(Point p) {
        super(new Rect(p.x, p.y, p.x+40, p.y+40));
    }

    @Override
    public void affectPlayer(World w) {
        w.nextLevel();
    }

    @Override
    public void update() {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
