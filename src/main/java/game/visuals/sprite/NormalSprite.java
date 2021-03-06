package game.visuals.sprite;

import android.graphics.Canvas;
import android.graphics.Rect;

import game.visuals.AnimationInfo;
import game.util.ID;
import game.framework.util.GameTime;

/**
 * Created by Emil on 02/09/2017.
 */

public class NormalSprite extends Sprite {


    private Rect src;

    /**
     * Creates a NormalSprite.
     * @param id The type of NormalSprite to be created.
     */
    public NormalSprite(ID id) {
        super(id);
        src = new Rect(0,0,0,0);
    }

    @Override
    public void draw(Canvas canvas, GameTime gameTime, Rect destination, int animationType) {
        if (animationType != oldAnimationType) {
            AnimationInfo temp = AnimationInfo.getAnimationInfo(id, animationType);
            currentCol = temp.getStartColumn();
            currentRow = temp.getStartRow();
            animationCounter = 0;
        }
        animate(animationType, gameTime);
        if (angle != 0) {
            canvas.save();
            canvas.rotate(angle, destination.centerX(), destination.centerY());
            canvas.drawBitmap(sheet.getBitmap(), src, destination, null);
            canvas.restore();
        }
        else
            canvas.drawBitmap(sheet.getBitmap(), src, destination,null);
    }


    /*
    Animates the NormalSprite according to the AnimationInfo of the type.
     */
    private void animate(int animationType, GameTime gameTime) {
        int width = sheet.getWidth();
        int height = sheet.getHeight();
        src.set(currentCol*width, currentRow*height,currentCol*width + width, currentRow*height + height);
        if (animationCounter >= animationThreshold) {
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
        animationCounter += gameTime.elapsedTime();
        oldAnimationType = animationType;

    }

//    private void animateSequence(int type) {
//        AnimationInfo temp = AnimationInfo.getAnimationInfo(id, type);
//
////        if (currentCol == temp.getEndColumn() && currentRow =)
//
//        if (currentCol == temp.getEndColumn()) {
//            if (currentRow == temp.getEndRow()) {
//                currentRow = temp.getStartRow();
//
//            } else
//                currentRow++;
//            currentCol = temp.getStartColumn();
//
//        } else
//            currentCol++;
//
//    }

//    private void calculateFrame() {
//        if (id == ID.PLAYER) {
//            animateSequence(AnimationInfo.RUNNING);
//
//        } else {
//            if (currentCol == sheet.getCols()) {
//                if (currentRow == sheet.getRows()) {
//                    currentRow = 0;
//
//                } else
//                    currentRow++;
//                currentCol = 0;
//
//            } else
//                currentCol++;
//        }
//    }
}
