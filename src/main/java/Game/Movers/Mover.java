package Game.Movers;

import android.graphics.Rect;

import Game.*;
import Game.Framework.LevelCreator;

import java.util.ArrayList;


/**
 * Created by Emil on 2016-11-22.
 */

public abstract class Mover extends GameObject {

    public Mover(Rect rect) {
        super(rect);
    }
}
