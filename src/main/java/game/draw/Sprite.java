package game.draw;

import android.graphics.Canvas;
import android.graphics.Rect;

import game.objectinformation.ID;
import game.objectinformation.IDHandler;
import game.util.GameTime;

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

    /**
     * Creates a Sprite.
     * @param id The type of sprite created.
     */
    public Sprite(ID id) {
        sheet = IDHandler.getSpriteSheet(id);
        this.id = id;
    }

    public abstract void draw(Canvas canvas, GameTime gameTime, Rect destination, int animationType);
}
