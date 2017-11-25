package Game.Draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import Game.Util.GameTime;

/**
 * Created by Emil on 31/08/2017.
 */

public class Background {

    private Bitmap bkg;
    private int x,y;
    private int scrollSpeed = 3;      //!

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
    public void draw (Canvas canvas, GameTime gameTime) {
        update();
        canvas.drawBitmap(bkg, x, y, null);
        if (y>0) {
            canvas.drawBitmap(bkg, x, y - bkg.getHeight(), null);
        }
    }
}
