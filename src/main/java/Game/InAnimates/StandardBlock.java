package Game.InAnimates;

import android.graphics.Point;

/**
 * Created by Emil on 9/1/2017.
 */

public class StandardBlock extends Block {

    public StandardBlock(Point p) {
        super(p, 0.2);
    }

    @Override
    public void update() {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
