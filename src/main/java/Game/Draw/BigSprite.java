package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Sprite representative of small objects, mashed together.
 * Created by Emil on 02/09/2017.
 */

public class BigSprite extends Sprite {

    private Rect[] destinations;
    private Rect srcFull;
    private Rect srcRemainder;
    private boolean horizontal;
    private boolean remainder;
    private final int maxNbr = 10; // size of sprite sheet matrix
    private int nbrBlocksMod;

    public BigSprite(ID id, int nbrBlocks, boolean horizontal, Rect object) {
        super(id);
        this.horizontal = horizontal;
        remainder = !(nbrBlocks%maxNbr == 0);
        if (remainder)
            destinations = new Rect[nbrBlocks/maxNbr + 1];
        else
            destinations = new Rect[nbrBlocks/maxNbr];
        init(nbrBlocks, object);
    }

    private void init(int nbrBlocks, Rect object) {
        int x = 0;
        int y = 0;
        int width = sheet.getWidth();
        int height = sheet.getHeight();
        nbrBlocksMod = nbrBlocks % maxNbr;
        int blocksLeft = nbrBlocks;

        //destination rectangles for maximal size sprites

        for (int i = 0; i < destinations.length ; i++) {
            if (blocksLeft >=maxNbr) {
                if (horizontal)
                    destinations[i] = new Rect(object.left + x, object.top, object.left + x + width * maxNbr, object.top + height);
                else
                    destinations[i] = new Rect(object.left, object.top + y, object.left + width, object.top + y + height * maxNbr);
                x += maxNbr * width;
                y += maxNbr * height;
                blocksLeft -= maxNbr;
            }
        }
        //destination rectangles for remainder size sprite
        if (blocksLeft > 0) {

            if (horizontal)
                destinations[destinations.length - 1] = new Rect(object.left + x, object.top, object.left +x +  nbrBlocksMod * width, object.top + height);
            else
                destinations[destinations.length - 1] = new Rect(object.left, object.top+ y, object.left + width, object.top + y + nbrBlocksMod*height );

        }
        //src rectangles
        if (horizontal) {
            srcFull = new Rect(0,0, width*maxNbr, height);
            srcRemainder = new Rect(0,0,width*nbrBlocksMod, height);
        }
        else {
            srcFull = new Rect(0,0, width, height*maxNbr);
            srcRemainder = new Rect(0,0,width, height*nbrBlocksMod);
        }
    }

    /*
        Rect destination can't be used, unless we provide unlimited sprite sizes.
     */
    @Override
    public void draw(Canvas canvas, Rect destination, int animationType) {
        if (animationType != AnimationInfo.NO_ANIMATION) {
            if (animationType != oldAnimationType) {
                currentCol = 0;
                currentRow = 0;
                animationCounter = 0;
            }
            animate(animationType);

        }
        for (int i = 0; i<destinations.length; i++) {
            if (remainder && i == destinations.length -1)
                canvas.drawBitmap(sheet.getBitmap(), srcRemainder, destinations[i], null);
            else
                canvas.drawBitmap(sheet.getBitmap(), srcFull, destinations[i], null);
        }

    }

    private void animate(int animationType) {
        int width = sheet.getWidth();
        int height = sheet.getHeight();
        if (horizontal) {
            srcFull.set(currentCol*width, currentRow*height,currentCol*width + maxNbr*width, currentRow*height + height);
            srcRemainder.set(currentCol*width, currentRow*height,currentCol*width + nbrBlocksMod*width, currentRow*height + height);
        }
        else {
            srcFull.set(currentCol*width, currentRow*height,currentCol*width + width, currentRow*height + height * maxNbr);
            srcRemainder.set(currentCol*width, currentRow*height,currentCol*width + width, currentRow*height + nbrBlocksMod * height);
        }

        if (animationCounter == animationThreshold) {
            animationCounter = 0;

            AnimationInfo temp = AnimationInfo.getAnimationInfo(id, animationType);
            if (horizontal)
                currentRow++;
            else
                currentCol++;

            if (currentCol == maxNbr - 1)
                currentCol = 0;
            if (currentRow == maxNbr - 1)
                currentRow = 0;
        }
        animationCounter++;
        oldAnimationType = animationType;
    }


}
