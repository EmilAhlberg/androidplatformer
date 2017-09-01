package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Emil on 01/09/2017.
 */

public class Sprite {


    private int currentCol =0;
    private int currentRow = 0;
    private int animationCounter = 0;
    private int animationThreshold = 10;
    private SpriteSheet sheet;
    private IDs id;

    public Sprite (IDs id) {
        sheet = IDHandler.getSpriteSheet(id);
        this.id = id;
    }

    public void draw(Canvas canvas, Rect destination) {
        Rect src =  animate();
        canvas.drawBitmap(sheet.getBitmap(), src,destination,null);

    }

    private Rect animate() {
        int gridSize = sheet.getGridSize();
        Rect src = new Rect(currentCol*gridSize, currentRow*gridSize,currentCol*gridSize + gridSize, currentRow*gridSize + gridSize);

        if (animationCounter == animationThreshold) {
            animationCounter = 0;

            calculateFrame();

        }
        animationCounter++;

        return src;
    }

    private void animateSequence(int type) {
        AnimationInfo temp = AnimationInfo.getAnimationInfo(id, type);

//        if (currentCol == temp.getEndColumn() && currentRow =)

        if (currentCol == temp.getEndColumn()) {
            if (currentRow == temp.getEndRow()) {
                currentRow = temp.getStartRow();

            } else
                currentRow++;
            currentCol = temp.getStartColumn();

        } else
        currentCol++;

    }

    private void calculateFrame() {
        if (id == IDs.PLAYER) {
            animateSequence(AnimationInfo.RUNNING);

        } else {
            if (currentCol == sheet.getCols()) {
                if (currentRow == sheet.getRows()) {
                    currentRow = 0;

                } else
                    currentRow++;
                currentCol = 0;

            } else
                currentCol++;
        }
    }

}
