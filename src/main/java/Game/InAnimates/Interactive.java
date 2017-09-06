package Game.InAnimates;

import android.graphics.Rect;

import Game.Framework.World;
import Game.GameObject;

/**
 * Created by Emil on 9/6/2017.
 */

public abstract class Interactive extends GameObject {

    public Interactive(Rect rect) {
        super(rect);
    }

    public abstract void affectPlayer (World w);
}
