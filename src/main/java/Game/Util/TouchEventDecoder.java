package Game.Util;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

import Game.Framework.GameDisplay;

/**
 * Created by Emil on 2016-11-27.
 */

public class TouchEventDecoder {

    private Point firstClickPos;
    private Point secondClickPos;
    private int touchAction, firstPointerId, secondPointerId;
    private int nbrFingersDown;


    public TouchEventDecoder (Point firstPos, Point secondPos) {
        this.firstClickPos = firstPos;
        this.secondClickPos = secondPos;
        nbrFingersDown = 0;

    }


    public void decodeTouchEvent (MotionEvent event, Point p) {
        int action = event.getAction();
        touchAction = action;

        if (event.getPointerCount() > 1) {
            if (firstPointerId == 0) {
                secondPointerId = 1;
            } else {
                secondPointerId = 0;
            }
            firstClickPos.x = (int)event.getX(event.getPointerId(firstPointerId));
            firstClickPos.y = (int)event.getY(event.getPointerId(firstPointerId));
            secondClickPos.x = (int)event.getX(event.getPointerId(secondPointerId));
            secondClickPos.y = (int)event.getY(event.getPointerId(secondPointerId));
            nbrFingersDown = 2;
            if (action == MotionEvent.ACTION_POINTER_UP) {
                nbrFingersDown = 1;
                firstPointerId = 1;
                firstClickPos.x = (int)event.getX(event.getPointerId(firstPointerId));
                firstClickPos.y = (int)event.getY(event.getPointerId(firstPointerId));
            } else if (action == MotionEvent.ACTION_POINTER_2_UP) {
                nbrFingersDown = 1;
                firstPointerId = 0;
                firstClickPos.x = (int)event.getX(event.getPointerId(firstPointerId));
                firstClickPos.y = (int)event.getY(event.getPointerId(firstPointerId));
            }
        } else {
            nbrFingersDown = 1;
            firstClickPos.x = (int)event.getX();
            firstClickPos.y = (int)event.getY();
            if (action == MotionEvent.ACTION_DOWN) {
                firstPointerId = 0;
            } else if (action == MotionEvent.ACTION_UP) {
                nbrFingersDown = 0;
            }
        }

        firstClickPos = new Point(firstClickPos.x * GameDisplay.WINDOW_WIDTH / p.x, firstClickPos.y * GameDisplay.WINDOW_HEIGHT / p.y);
        secondClickPos = new Point(secondClickPos.x * GameDisplay.WINDOW_WIDTH / p.x, secondClickPos.y * GameDisplay.WINDOW_HEIGHT / p.y);

        //Log.d("MultiTouch", "FirstClickPos = (" + firstClickPos.getX() + ", " + firstClickPos.getY() + ")" + " : SecondClickPos = (" + secondClickPos.getX() + ", " + secondClickPos.getY() + ")");

        //Log.d("MultiTouch", "" + firstPointerId + " : " + secondPointerId + " : " + nbrFingersDown);
        //debugMultiTouch();
    }

    public void switchPositions() {
        if (firstPointerId == 0)
            firstPointerId = 1;
        else
            firstPointerId = 0;
        Point temp = firstClickPos;
        firstClickPos = secondClickPos;
        secondClickPos = temp;
    }

    private void debugMultiTouch() {
        if (touchAction == MotionEvent.ACTION_DOWN) {
            Log.d("MultiTouch", "ACTION_DOWN");
        } else if (touchAction == MotionEvent.ACTION_UP) {
            Log.d("MultiTouch", "ACTION_UP");
        } else if (touchAction == MotionEvent.ACTION_POINTER_DOWN) {
            Log.d("MultiTouch", "ACTION_POINTER_DOWN");
        } else if (touchAction == MotionEvent.ACTION_POINTER_UP) {
            Log.d("MultiTouch", "ACTION_POINTER_UP");
        } else if (touchAction == MotionEvent.ACTION_MOVE){
            Log.d("MultiTouch", "ACTION_MOVE");
        } else if (touchAction == MotionEvent.ACTION_POINTER_INDEX_SHIFT){
            Log.d("MultiTouch", "ACTION_POINTER_INDEX_SHIFT");
        } else if (touchAction == MotionEvent.ACTION_CANCEL){
            Log.d("MultiTouch", "ACTION_CANCEL");
        } else if (touchAction == MotionEvent.ACTION_POINTER_INDEX_MASK){
            Log.d("MultiTouch", "ACTION_POINTER_INDEX_MASK");
        } else if (touchAction == MotionEvent.ACTION_BUTTON_PRESS){
            Log.d("MultiTouch", "ACTION_BUTTON_PRESS");
        } else if (touchAction == MotionEvent.ACTION_SCROLL){
            Log.d("MultiTouch", "ACTION_SCROLL");
        } else if (touchAction == MotionEvent.ACTION_HOVER_ENTER){
            Log.d("MultiTouch", "ACTION_HOVER_ENTER");
        } else if (touchAction == MotionEvent.ACTION_MASK){
            Log.d("MultiTouch", "ACTION_MASK");
        } else if (touchAction == MotionEvent.ACTION_OUTSIDE){
            Log.d("MultiTouch", "ACTION_OUTSIDE");
        } else if (touchAction == MotionEvent.ACTION_POINTER_2_UP){
            Log.d("MultiTouch", "ACTION_POINTER_2_UP");
        } else if (touchAction == MotionEvent.ACTION_POINTER_2_DOWN){
            Log.d("MultiTouch", "ACTION_POINTER_2_DOWN");
        } else {
            Log.d("MultiTouch", "UNKNOWN");
        }
    }

    public Point getFirstClickPos() {
        return firstClickPos;
    }

    public Point getSecondClickPos() {
        return secondClickPos;
    }

    public int getNbrFingersDown () {
        return nbrFingersDown;
    }
}
