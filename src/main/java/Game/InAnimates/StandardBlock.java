package Game.InAnimates;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Emil on 9/1/2017.
 */

public class StandardBlock extends Block {

    public static final int BLOCK_WIDTH = 20;
    public static final int BLOCK_HEIGHT = 20;

    public StandardBlock(Point p) {
        super(new Rect(p.x, p.y, p.x + BLOCK_WIDTH, p.y + BLOCK_HEIGHT), 0.2);
    }

    @Override
    public void update() {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
