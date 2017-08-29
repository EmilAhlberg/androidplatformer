package Game.Movers;

import android.graphics.Rect;

import Game.*;
import Game.Framework.LevelCreator;
import Game.InAnimates.Block;
import Game.Util.IDs;
import Game.Util.MovementHandler;

import java.util.ArrayList;


/**
 * Created by Emil on 2016-11-22.
 */

public abstract class Mover extends GameObject {

    protected MovementHandler mh;

    public Mover(Rect rect) {
        super(rect);
        mh = new MovementHandler();
    }
}
