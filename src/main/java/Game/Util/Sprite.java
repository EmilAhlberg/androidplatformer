package Game.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Pair;

import java.util.Vector;

import Game.InAnimates.Block;

/**
 * Created by Emil on 01/09/2017.
 */

public class Sprite {

    private Bitmap bitmap;
    private Rect destination;
    private int gridSize; //quadratic grid!
    private int cols;
    private int rows;
    private int currentCol =0;
    private int currentRow = 0;
    private int animationCounter = 0;
    private int animationThreshold = 10;

    public Sprite (IDs id, int gridSize) {
        bitmap = IDHandler.getBitmap(id);

        this.gridSize = gridSize;
        init();
    }

    private void init() {
        cols = 0;
        rows = 0;
        for(int i = gridSize; i <= bitmap.getHeight(); i+= gridSize) {
            cols++;
        }
        for (int j = gridSize; j <= bitmap.getWidth(); j += gridSize) {
           rows++;
        }

    }


    public void draw(Canvas canvas) {
        Rect src =  animate();
        canvas.drawBitmap(bitmap, src,destination,null);

    }

    private Rect animate() {
        Rect src = new Rect(currentCol*gridSize, currentRow*gridSize,currentCol*gridSize + gridSize, currentRow*gridSize + gridSize);

        if (animationCounter == animationThreshold) {
            animationCounter = 0;

            calculateFrame();

        }
        animationCounter++;

        return src;
    }

    private void calculateFrame() {
        if (currentCol == cols - 1) {
            if (currentRow == rows - 1) {
                currentRow = 0;

            } else
                currentRow++;
            currentCol = 0;

        } else
            currentCol++;
    }

    public void updatePosition(Rect rect) {
        destination = rect;
    }
}
