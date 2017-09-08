package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Emil on 02/09/2017.
 */

public class AnimatedSprite extends Sprite {

    private int currentCol =0;
    private int currentRow = 0;
    private int animationCounter = 0;
    private int animationThreshold = 10;
    private Rect src;
    private int oldAnimationType = AnimationInfo.DEFAULT;

    public AnimatedSprite (ID id) {
        super(id);
        src = new Rect(0,0,0,0);
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


    private Rect animate(int animationType) {
        int width = sheet.getWidth();
        int height = sheet.getHeight();
        src.set(currentCol*width, currentRow*height,currentCol*width + width, currentRow*height + height);
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
        if (id == ID.PLAYER) {
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
