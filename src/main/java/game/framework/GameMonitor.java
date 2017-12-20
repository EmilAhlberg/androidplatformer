package game.framework;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;
import game.GameObject;
import game.android.GameActivity;
import game.objectinformation.ID;
import game.util.GameTime;
import game.util.MotionEventInfo;

/**
 * Created by Emil on 12/14/2017.
 */

public class GameMonitor {


    private World world;
    private GameDraw draw;
    private GameTime gameTime;
    private MotionEventInfo lastMotionEvent;
    private MotionEventInfo oldMotionEvent;
    private Handler handler;
    private long time;

    public GameMonitor(GameActivity game, Handler handler) {
        this.handler = handler;
        HashMap<ID,ArrayList<GameObject>> levelInfo = LevelCreator.createLevel(game, game.getIntent().getExtras().getInt("level"));
        world = new World(levelInfo);
        draw = new GameDraw(game);
        time = System.currentTimeMillis();
        gameTime = new GameTime(time);
    }

    public synchronized void putTouchEvent(MotionEventInfo e) {
        lastMotionEvent = e; // minimal blocking of gameLoop
    }


    public synchronized void nextGameCycle() {
        long waitTime = (long)gameTime.getCurrentTime() - System.currentTimeMillis();
        System.out.println(waitTime);
        while (waitTime > 0) {
            try {
                wait(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTime = (long)gameTime.getCurrentTime() - System.currentTimeMillis();
        }
        gameTime.update();
        if (lastMotionEvent != null && lastMotionEvent != oldMotionEvent) {
            world.decodeTouchEvent(lastMotionEvent.getEvent(), lastMotionEvent.getPoint());
            oldMotionEvent = lastMotionEvent;
        }
        world.update(gameTime);
        handleGameState();
    }

    private void handleGameState() {
        try {
            Message m = handler.obtainMessage();
            switch (World.GAME_STATE) {
                case World.RUNNING_STATE:
                    if (!handler.hasMessages(0)) {
                        m.what = 0;
                        m.obj = draw.drawGame(gameTime, world);
                        ;
                        m.sendToTarget();
                    }
                    break;
                case World.GAME_OVER_STATE:
                    m.what = 1;
                    handler.sendMessageAtFrontOfQueue(m);
                    wait();
                    break;
                case World.NEXT_LEVEL_STATE:
                    m.what = 2;
                    handler.sendMessageAtFrontOfQueue(m);
                    wait();
                    break;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
