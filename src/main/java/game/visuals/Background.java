package game.visuals;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import game.framework.game.World;
import game.framework.util.GameTime;

/**
 * Created by Emil on 31/08/2017.
 */

public class Background {

    private Bitmap bkg;
    private int x,y;
    private int scrollSpeed = 1;      //!


    /**
     * Creates the background of the game.
     * @param bitmap The background image.
     */
    public Background(Bitmap bitmap) {
        bkg = bitmap;
    }

    private void update() {

        y += scrollSpeed;
        if (y >= bkg.getHeight())
            y = 0;
    }

    /**
     * Draws the background to the screen.
     * @param canvas The canvas used in the game.
     */
    public void draw (Canvas canvas, Rect source, GameTime gameTime) {
        update();
        canvas.drawBitmap(bkg, source, new Rect(0, 0, World.WINDOW_WIDTH, World.WINDOW_HEIGHT), null);
        canvas.drawBitmap(bkg, x, y, null);
        if (y>0) {
            canvas.drawBitmap(bkg, x, y - bkg.getHeight(), null);
        }
    }
}
