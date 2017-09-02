package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import Game.Movers.Player;

/**
 * Created by Emil on 02/09/2017.
 */

public class AnimatedSprite extends Sprite {

    private int currentCol =0;
    private int currentRow = 0;
    private int animationCounter = 0;
    private int animationThreshold = 10;
    private int oldAnimationType = AnimationInfo.DEFAULT;

    public AnimatedSprite (IDs id) {
        super(id);
    }

    @Override
    public void draw(Canvas canvas, Rect destination, int animationType) {
        if (animationType != oldAnimationType) {
            AnimationInfo temp = AnimationInfo.getAnimationInfo(id, animationType);
            currentCol = temp.getStartColumn();
            currentRow = temp.getStartRow();
            animationCounter = 0;
        }
        Rect src = animate(animationType);
        oldAnimationType = animationType;
        canvas.drawBitmap(sheet.getBitmap(), src,destination,null);
    }

    public void draw(Canvas canvas, Rect destination) {


    }

    private Rect animate(int animationType) {
        int width = sheet.getWidth();
        int height = sheet.getHeight();
        Rect src = new Rect(currentCol*width, currentRow*height,currentCol*width + width, currentRow*height + height);
        if (animationCounter == animationThreshold) {
            animationCounter = 0;

            AnimationInfo temp = AnimationInfo.getAnimationInfo(id, animationType);

            if (currentCol == temp.getEndColumn()) {
                if (currentRow == temp.getEndRow()) {
                    currentRow = temp.getStartRow();

                } else
                    currentRow++;
                currentCol = temp.getStartColumn();

            } else
                currentCol++;
        }
        animationCounter++;


//
//        Rect src = new Rect(currentCol*gridSize, currentRow*gridSize,currentCol*gridSize + gridSize, currentRow*gridSize + gridSize);
//
//        if (animationCounter == animationThreshold) {
//            animationCounter = 0;
//
//            calculateFrame();
//
//        }
//        animationCounter++;

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
