package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Emil on 01/09/2017.
 */

public abstract class Sprite {

    protected SpriteSheet sheet;
    protected IDs id;

    public Sprite(IDs id) {
        sheet = IDHandler.getSpriteSheet(id);
        this.id = id;
    }

    public abstract void draw(Canvas canvas, Rect destination, int animationType);




}
