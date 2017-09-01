package Game.Draw;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Emil on 01/09/2017.
 */

public class SpriteSheet {

    private int cols;
    private int rows;

    private Bitmap bitmap;
    private int gridSize; //quadratic grid!

    public SpriteSheet(IDs id, int gridSize) {
        bitmap = IDHandler.getBitmap(id);
        this.gridSize = gridSize;
        init();
    }


    private void init() {
        cols = 0;
        rows = 0;
        for(int i = gridSize; i < bitmap.getHeight(); i+= gridSize) {
            cols++;
        }
        for (int j = gridSize; j < bitmap.getWidth(); j += gridSize) {
            rows++;
        }

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
