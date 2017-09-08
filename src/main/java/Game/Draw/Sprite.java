package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Emil on 01/09/2017.
 */

public abstract class Sprite {

    protected SpriteSheet sheet;
    protected ID id;
    protected int currentCol =0;
    protected int currentRow = 0;
    protected int animationCounter = 0;
    protected int animationThreshold = 10;
    protected int oldAnimationType = AnimationInfo.DEFAULT;

    public Sprite(ID id) {
        sheet = IDHandler.getSpriteSheet(id);
        this.id = id;
    }

    public abstract void draw(Canvas canvas, Rect destination, int animationType);
}
