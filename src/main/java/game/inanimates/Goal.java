package game.inanimates;

import android.graphics.Point;

import game.framework.World;
import game.GameObject;
import game.util.GameTime;

/**
 * Created by Emil on 9/6/2017.
 */

public class Goal extends GameObject {

    public Goal(Point p) {
        super(p);
        isActive = true;
    }


    public void affectPlayer(World w) {
        w.nextLevel();
    }

    @Override
    public void update(GameTime gameTime) {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
