package game.framework;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Handler;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import game.GameObject;
import game.android.GameActivity;
import game.movers.Player;
import game.objectinformation.ID;
import game.util.GameTime;

/**
 * Created by Emil on 12/14/2017.
 */

public class GameMonitor {

    //private LinkedList<MotionEventInfo> touchEvents;
    private Player player;
    private World world;
    private GameDraw draw;

    public GameMonitor(GameActivity game, Handler handler) {
        //touchEvents = new LinkedList<>();
        HashMap<ID,ArrayList<GameObject>> levelInfo = LevelCreator.createLevel(game, game.getIntent().getExtras().getInt("level"));
        Player player = (Player)levelInfo.get(ID.LEVELPLAYER).get(0);
        world = new World(game, levelInfo, handler); // game param should perhaps be removed?
        draw = new GameDraw(game, player);


    }

    public synchronized void putTouchEvent(MotionEvent event, Point p) {
        world.decodeTouchEvent(event, p);
        notifyAll();
    }


    public synchronized Bitmap nextFrame(GameTime gameTime, boolean shouldDraw, long timeLimit) {
        //should be double checked
        try {
            long waitTime = timeLimit - (System.currentTimeMillis() - (long) gameTime.getCurrentTime());
            while (0 < waitTime) {
                wait(waitTime);
                waitTime = timeLimit - (System.currentTimeMillis() - (long) gameTime.getCurrentTime());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        world.update(gameTime);
        if (shouldDraw)
            return draw.drawGame(gameTime, world);
        else
            return null;
    }

}
