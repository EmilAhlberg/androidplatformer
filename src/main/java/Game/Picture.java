package Game;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import Game.Util.IDHandler;
import Game.Util.IDs;

/**
 * Created by Emil on 23/08/2017.
 */

public class Picture {

    private Drawable sprite;

    public Picture(IDs id, Rect rect) {
        sprite = IDHandler.getDrawable(id);
        sprite.setBounds(rect);
    }

    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}
