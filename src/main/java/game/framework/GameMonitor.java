package game.framework;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.HashMap;

import game.framework.util.GameDraw;
import game.framework.util.GameState;
import game.framework.util.GameTime;
import game.gameObjects.GameObject;
import game.activities.GameActivity;
import game.framework.game.LevelCreator;
import game.framework.game.World;
import game.util.ID;
import game.util.MotionEventInfo;

/**
 * Created by Emil on 12/14/2017.
 */

public class GameMonitor {

    private World world;
    private GameState gameState;
    private GameDraw draw;
    private GameTime gameTime;
    private MotionEventInfo lastMotionEvent;
    private MotionEventInfo oldMotionEvent;
    private Handler handler;

    public GameMonitor(GameActivity game, Handler handler) {
        this.handler = handler;
        HashMap<ID, ArrayList<GameObject>> levelInfo = LevelCreator.createLevel(game, game.getIntent().getExtras().getInt("level"));
        world = new World(levelInfo);
        draw = new GameDraw(game);
        gameTime = new GameTime(System.currentTimeMillis());
        gameState = new GameState();
    }

    public synchronized void putTouchEvent(MotionEventInfo e) {
        lastMotionEvent = e; // minimal blocking of gameLoop
    }


    public synchronized void nextGameCycle() {
        long waitTime = (long) gameTime.getCurrentTime() - System.currentTimeMillis();
        System.out.println(waitTime);
        while (waitTime > 0) {
            try {
                wait(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTime = (long) gameTime.getCurrentTime() - System.currentTimeMillis();
        }
        gameTime.update();
        if (lastMotionEvent != null && lastMotionEvent != oldMotionEvent) {
            world.decodeTouchEvent(lastMotionEvent.getEvent(), lastMotionEvent.getPoint());
            oldMotionEvent = lastMotionEvent;
        }
        world.update(gameTime, gameState);
        handleGameState();
    }

    private void handleGameState() {
        try {
            Message m = handler.obtainMessage();
            switch (gameState.getGameState()) {
                case GameState.RUNNING_STATE:
                    if (!handler.hasMessages(0)) {
                        m.what = 0;
                        m.obj = draw.drawGame(gameTime, world);
                        m.sendToTarget();
                    }
                    break;
                case GameState.GAME_OVER_STATE:
                    m.what = 1;
                    handler.sendMessageAtFrontOfQueue(m);
                    wait();
                    break;
                case GameState.NEXT_LEVEL_STATE:
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
