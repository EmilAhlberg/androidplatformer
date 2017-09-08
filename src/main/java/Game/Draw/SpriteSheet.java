package Game.Draw;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Emil on 01/09/2017.
 */

public class SpriteSheet {

    private int cols;
    private int rows;

    private Bitmap bitmap;
    private int width;
    private int height;

    public SpriteSheet(Bitmap bitmap, int width, int height) {
        this.bitmap = bitmap;
        this.height = height;
        this.width = width;
        init();
    }


    private void init() {
        cols = 0;
        rows = 0;
        for(int i = height; i < bitmap.getHeight(); i+= height) {
            rows++;
        }
        for (int j = width; j < bitmap.getWidth(); j += width) {
            cols++;
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
