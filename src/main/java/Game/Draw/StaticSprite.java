package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * The static sprite is not animated, and suited only for 1x1 size SpriteSheets.
 * Created by Emil on 02/09/2017.
 */

public class StaticSprite extends Sprite {

    private Rect src;

    public StaticSprite(IDs id) {
        super(id);
        src = new Rect(0,0, sheet.getBitmap().getWidth(), sheet.getBitmap().getHeight());
    }

    /*
        AnimationType disregarded in StaticSprite --> no animation.
     */
    @Override
    public void draw(Canvas canvas, Rect destination, int animationType) {
        canvas.drawBitmap(sheet.getBitmap(), src,destination,null);
    }


}
