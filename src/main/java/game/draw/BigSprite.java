package game.draw;

import android.graphics.Canvas;
import android.graphics.Rect;

import game.objectinformation.ID;
import game.util.GameTime;

/**
 * Sprite representative of small objects, mashed together.
 * Explanation; destinations[]: represents the destination rectangles, which put together represents the entire object.
 *              srcFull: if there is any destination rectangle of size == maxNbr, then this source rectangle corresponds to that rect.
 *              srcRemainder: analogously from above, if destination rect size < maxNbr, then this is the source rectangle correspondant. *
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

    /**
     * Creates a BigSprite, which is the visual representation of a "big object".
     * @param id The type of BigSprite created.
     * @param size The size of the bigsprite, i.e. the number of parts included into a "big object".
     * @param horizontal The orientation of the BigSprite. False means vertical orientation.
     * @param rect The rectangle representing position and size of the object.
     */
    public BigSprite(ID id, int size, boolean horizontal, Rect rect) {
        super(id);
        this.horizontal = horizontal;
        remainder = !(size%maxNbr == 0);
        if (remainder)
            destinations = new Rect[size/maxNbr + 1];
        else
            destinations = new Rect[size/maxNbr];
        init(size, rect);
    }

    /*
    Creates the necessary amount of destination rectangles, depending on the size of BigObject
    and the maxNbr, i.e the sprite sheet size. (Can't store infinite sprite sheets, 10x10 is standard).
     */
    private void init(int nbrBlocks, Rect rect) {
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
                    destinations[i] = new Rect(rect.left + x, rect.top, rect.left + x + width * maxNbr, rect.top + height);
                else
                    destinations[i] = new Rect(rect.left, rect.top + y, rect.left + width, rect.top + y + height * maxNbr);
                x += maxNbr * width;
                y += maxNbr * height;
                blocksLeft -= maxNbr;
            }
        }
        //destination rectangles for remainder size sprite
        if (blocksLeft > 0) {

            if (horizontal)
                destinations[destinations.length - 1] = new Rect(rect.left + x, rect.top, rect.left +x +  nbrBlocksMod * width, rect.top + height);
            else
                destinations[destinations.length - 1] = new Rect(rect.left, rect.top+ y, rect.left + width, rect.top + y + nbrBlocksMod*height );

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

    @Override
    public void draw(Canvas canvas, GameTime gameTime, Rect destination, int animationType) {
        //handle animation
        if (animationType != AnimationInfo.NO_ANIMATION) {
            if (animationType != oldAnimationType) {
                currentCol = 0;
                currentRow = 0;
                animationCounter = 0;
            }
            animate(animationType, gameTime);

        }
        //draw all destination rectangles
        for (int i = 0; i<destinations.length; i++) {
            if (remainder && i == destinations.length -1)
                canvas.drawBitmap(sheet.getBitmap(), srcRemainder, destinations[i], null);
            else
                canvas.drawBitmap(sheet.getBitmap(), srcFull, destinations[i], null);
        }

    }

    /*
    Right now horizontal oriented BigSprites are animated from top to bottom, vertical are from left to right.

    Horizontal: (x is current image shown, 0 the rest of sprite sheet)
                x x x              0 0 0             0 0 0
                0 0 0      -->     x x x      -->    0 0 0
                0 0 0              0 0 0             x x x
     */
    private void animate(int animationType, GameTime gameTime) {
        int width = sheet.getWidth();
        int height = sheet.getHeight();
        if (horizontal) {
            srcFull.set(0, currentRow * height, maxNbr * width, currentRow * height + height);
            srcRemainder.set(0, currentRow * height, nbrBlocksMod * width, currentRow * height + height);
        } else {
            srcFull.set(currentCol * width, currentRow * height, currentCol * width + width, currentRow * height + height * maxNbr);
            srcRemainder.set(currentCol * width, currentRow * height, currentCol * width + width, currentRow * height + nbrBlocksMod * height);
        }

        if (animationCounter >= animationThreshold) {
            animationCounter = 0;

            //AnimationInfo temp = AnimationInfo.getAnimationInfo(id, animationType);
            if (horizontal)
                currentRow = ++currentRow % maxNbr;
            else
                currentCol = ++currentCol % maxNbr;
        }
        animationCounter += gameTime.elapsedTime();
        oldAnimationType = animationType;

    }
}
