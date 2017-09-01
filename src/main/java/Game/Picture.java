package Game;


import android.graphics.Canvas;
import android.graphics.Rect;

import Game.Draw.IDs;
import Game.Draw.Sprite;

/**
 * Created by Emil on 23/08/2017.
 */

public class Picture {

    private Sprite sprite;
    private Rect rect;

    public Picture(IDs id, Rect rect) {
        sprite = new Sprite(id);
        this.rect = rect;
    }

    public void draw(Canvas canvas) {
        sprite.draw(canvas, rect);
    }

    public void setBounds(Rect rect) {
        this.rect = rect;
    }
}
