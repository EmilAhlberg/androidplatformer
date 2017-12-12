package game.draw;

import android.graphics.Bitmap;

/**
 * Created by Emil on 01/09/2017.
 */

public class SpriteSheet {

    private int cols;
    private int rows;

    private Bitmap bitmap;
    private int width;
    private int height;

    /**
     * Creates a SpriteSheet.
     * @param bitmap The bitmap of the spriteSheet.
     * @param width The width of an image in the sheet.
     * @param height The height of an image in the sheet.
     */
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

    /**
     * Retrieves the bitmap
     * @return the bitmap of the spritesheet
     */

    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Gets the width of an image in the sheet.
     * @return The width of the image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of an image in the sheet.
     * @return The height of the image.
     */
    public int getHeight() {
        return height;
    }
//
//    public int getCols() {
//        return cols;
//    }
//
//    public int getRows() {
//        return rows;
//    }
}
