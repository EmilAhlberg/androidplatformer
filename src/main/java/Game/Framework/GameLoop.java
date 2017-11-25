package Game.Framework;

import android.os.Handler;
import android.os.Message;

import Game.Android.GameActivity;
import Game.GameObject;
import Game.Util.GameTime;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop extends Thread {

    private GameActivity game;
    private Handler handler;
    private final int timeLimit = 15; //!!!
    private boolean running = true;
    private double startTime;
    private GameTime gameTime;

    public GameLoop(GameActivity game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    @Override
    public void run() {
        double currentTime = startTime = System.currentTimeMillis();
        double newTime = 0;
        gameTime = new GameTime(startTime);
        while (running) {
            newTime = System.currentTimeMillis();
            if (newTime - currentTime > timeLimit) {
                gameTime.update(newTime);
                updateLoop(gameTime);
                currentTime = newTime;
            }
        }
    }

    private void updateLoop(GameTime gameTime) {
        //long millis = System.currentTimeMillis();
        game.updateWorld(gameTime);
        //Log.d("updateLoop", "Update world: " + (System.currentTimeMillis() - millis));
        //millis = System.currentTimeMillis();

        //game.drawWorld();

        //Log.d("updateLoop", "Draw world: " + (System.currentTimeMillis() - millis));
        //millis = System.currentTimeMillis();
        Message m = handler.obtainMessage();
        m.sendToTarget();
        //Log.d("updateLoop", "Handle messages: " + (System.currentTimeMillis() - millis));
    }

    public void pause() {
        running = false;
    }

    public GameTime getGameTime() {
        return gameTime;
    }
}
