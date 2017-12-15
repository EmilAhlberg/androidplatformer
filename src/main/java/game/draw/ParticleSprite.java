package game.draw;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import game.objectinformation.ID;
import game.objectinformation.Stats;
import game.util.GameTime;

/**
 * Created by Emil on 15/12/2017.
 */

public class ParticleSprite extends Sprite {

    public static final int STANDARD = 0;
    public static final int BOTTOM_PART_X2 = 1;
    public static final int TOP_PART_X2 = 2;
    public static final int LEFT_PART_X2 = 3;
    public static final int RIGHT_PART_X2 = 4;

    public static final int TOPLEFT_PART_X4 = 5;
    public static final int TOPRIGHT_PART_X4 = 6;
    public static final int BOTTOMLEFT_PART_X4 = 7;
    public static final int BOTTOMRIGHT_PART_X4 = 8;
    private Rect src;

    public ParticleSprite(ID id, int partType, Rect dest, Point p) {
        super(id);
        initRectangles(partType, dest, p, id);
        //src = new Rect(0,0,sheet.getWidth(),sheet.getHeight()/2);
        //dest.set(p.x, p.y, p.x + Stats.width(id), p.y + Stats.height(id));
    }

    private void initRectangles(int partType, Rect dest, Point p, ID id) {
        int srcTop = 0;
        int srcBottom = sheet.getHeight();
        int srcLeft = 0;
        int srcRight = sheet.getWidth();

        int destTop = p.y;
        int destBottom = p.y + Stats.height(id);
        int destLeft = p.x;
        int destRight =  p.x + Stats.width(id);;

        switch (partType) {
            case BOTTOM_PART_X2:
                srcTop = sheet.getHeight()/ 2;
                destTop += Stats.height(id)/2;
                break;
            case TOP_PART_X2:
                srcBottom = sheet.getHeight()/2;
                destBottom -= Stats.height(id)/2;
                break;
            case LEFT_PART_X2:
                srcRight = sheet.getWidth()/2;
                destRight -= Stats.width(id)/2;
                break;
            case RIGHT_PART_X2:
                srcLeft = sheet.getWidth()/2;
                destLeft += Stats.width(id)/2;
                break;
            //which part is which?
            case TOPLEFT_PART_X4:
                srcBottom = sheet.getHeight()/2;
                destBottom -= Stats.height(id)/2;
                srcRight = sheet.getWidth()/2;
                destRight -= Stats.width(id)/2;
                break;
            case TOPRIGHT_PART_X4:
                srcBottom = sheet.getHeight()/2;
                destBottom -= Stats.height(id)/2;
                srcLeft = sheet.getWidth()/2;
                destLeft += Stats.width(id)/2;
                break;
            case BOTTOMLEFT_PART_X4:
                srcTop = sheet.getHeight()/ 2;
                destTop += Stats.height(id)/2;
                srcRight = sheet.getWidth()/2;
                destRight -= Stats.width(id)/2;
                break;
            case BOTTOMRIGHT_PART_X4:
                srcTop = sheet.getHeight()/ 2;
                destTop += Stats.height(id)/2;
                srcLeft = sheet.getWidth()/2;
                destLeft += Stats.width(id)/2;
                break;

        }
        src = new Rect(srcLeft, srcTop, srcRight, srcBottom);
        dest.set(destLeft, destTop, destRight, destBottom);
    }

    @Override
    public void draw(Canvas canvas, GameTime gameTime, Rect destination, int animationType) {
        if (angle != 0) {
            canvas.save();
            canvas.rotate(angle, destination.centerX(), destination.centerY());
            canvas.drawBitmap(sheet.getBitmap(), src, destination, null);
            canvas.restore();
        }
        else
            canvas.drawBitmap(sheet.getBitmap(), src, destination,null);
    }
}
