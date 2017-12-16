package game.inanimates;

import android.graphics.Point;

import game.framework.World;
import game.GameObject;
import game.util.GameTime;
import game.util.Vector;

/**
 * Created by Emil on 9/6/2017.
 */

public class Goal extends GameObject {

    public Goal(Vector v) {
        super(v);
        isActive = true;
    }


    public void affectPlayer() {
        World.NEXT_LEVEL = true;
    }

    @Override
    public void update(GameTime gameTime) {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
