package Game.InAnimates;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

import Game.GameObject;

/**
 * Created by Emil on 9/2/2017.
 */

public class BigBlock extends GameObject{

//    private ArrayList<Block> blocks;
    private double friction;
    private boolean horizontal;


    //Only works for StandardBlock right now (no big ice blocks etc.)

    public BigBlock(Point p, int nbrBlocks, boolean horizontal) { //horizontal is worthless as a parameter but it needs to be there to differentiate the constructors
        super(p, nbrBlocks, horizontal);
        this.horizontal = horizontal;
        init(nbrBlocks);
        this.friction = 0.2;
    }

//    public BigBlock(Point p, int nbrBlocks) {
//        super(new Rect(p.x, p.y, p.x+StandardBlock.BLOCK_WIDTH, p.y+StandardBlock.BLOCK_HEIGHT*nbrBlocks));
//        init(nbrBlocks, false);
//    }

    private void init(int nbrBlocks) {
//        blocks = new ArrayList<>();
//        if (horizontal) {
//            for (int i = 0; i < nbrBlocks; i++)
//                blocks.add(new StandardBlock(new Point(rect.left + StandardBlock.BLOCK_WIDTH*i, rect.top)));
//        } else {
//            for (int i = 0; i < nbrBlocks; i++)
//                blocks.add(new StandardBlock(new Point(rect.left, rect.top + StandardBlock.BLOCK_HEIGHT*i)));
//        }
    }

    public double getFriction () {
        return friction;
    }


//    @Override
//    public void draw(Canvas c) {
//        super.draw();
////        for (Block b : blocks)
////            b.draw(c);
//    }

//    public void drawSome(Rect playerRect, Canvas c, int radius) {
//        for (Block b : blocks) {
//            Rect gRect = b.getRect();
//            if (Math.sqrt(Math.pow(playerRect.left - gRect.left, 2) + Math.pow(playerRect.top - gRect.top, 2)) < radius)
//                b.draw(c);
//        }
//    }

    @Override
    public void update() {
        //BOMB -> ensures this is not used
        throw new NoSuchMethodError();
    }
}
