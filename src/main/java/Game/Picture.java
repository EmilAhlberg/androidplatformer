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
    private Rect rect;

    public Picture(IDs id, Rect rect) {
        sprite = IDHandler.getDrawable(id);
        this.rect = rect;
    }

    public void draw(Canvas canvas) {
        sprite.setBounds(rect);
        sprite.draw(canvas);
    }

    public void setBounds(Rect rect) {
        this.rect = rect;
    }
}
