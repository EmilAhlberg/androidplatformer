package Game.Util;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import Game.Framework.GameDisplay;

/**
 * Created by Emil on 31/08/2017.
 */

public class Background {

    private Bitmap bkg;
    private int x,y;
    private int scrollSpeed = 3;

    public Background(Bitmap bitmap) {
        bkg = bitmap;
        //y = bkg.getHeight() - GameDisplay.WINDOW_HEIGHT;


    }

    private void update() {

        y += scrollSpeed;
        if (y >= bkg.getHeight())
            y = 0;
    }

    public void draw (Canvas canvas) {
        update();
        canvas.drawBitmap(bkg, x, y, null);
        if (y>0) {
            canvas.drawBitmap(bkg, x, y - bkg.getHeight(), null);
        }
    }
}
