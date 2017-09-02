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
    private int gridSize; //quadratic grid!

    public SpriteSheet(IDs id, int gridSize) {
        bitmap = IDHandler.getBitmap(id);
        this.gridSize = gridSize;
        init(id);
    }


    private void init(IDs id) {
        cols = 0;
        rows = 0;
        for(int i = gridSize; i < bitmap.getHeight(); i+= gridSize) {
            rows++;
        }
        for (int j = gridSize; j < bitmap.getWidth(); j += gridSize) {
            cols++;
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
