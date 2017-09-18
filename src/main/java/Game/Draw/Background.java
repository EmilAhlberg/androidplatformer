package Game.Draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Emil on 31/08/2017.
 */

public class Background {

    private Bitmap bkg;
    private int x,y;
    private int scrollSpeed = 3;      //!

    public Background(Bitmap bitmap) {
        bkg = bitmap;
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
