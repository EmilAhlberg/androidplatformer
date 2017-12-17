package game.framework;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import game.android.GameActivity;
import game.util.GameTime;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop implements Runnable {

    private GameActivity game;
    private Handler handler;
    private final int timeLimit = 15; //!!!
    private GameLoopMonitor glMonitor;

    public GameLoop(GameActivity game, Handler handler, GameLoopMonitor glm) {
        this.game = game;
        this.handler = handler;
        glMonitor = glm;
    }

    @Override
    public void run() {
        double currentTime = System.currentTimeMillis();
        double newTime = 0;
        glMonitor.setGameTime(new GameTime(currentTime));
        while (!Thread.currentThread().isInterrupted()) {

            // Fix busy wait!!! //aha! snyggt // Fixat ;)
            newTime = System.currentTimeMillis();

            double temp = timeLimit - (newTime - currentTime);
            if (temp > 0) {
                try {
                    Thread.sleep((int)temp);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            //if (newTime - currentTime > timeLimit) {
            glMonitor.updateGameTime(newTime);
            updateLoop(glMonitor.getGameTime());
            currentTime = newTime;
            //}
        }
    }

    private void updateLoop(GameTime gameTime) {
        //long millis = System.currentTimeMillis();
        game.updateWorld(gameTime);
        //Log.d("updateLoop", "Update world: " + (System.currentTimeMillis() - millis));
        //millis = System.currentTimeMillis();

        //game.drawWorld();

        //Log.d("updateLoop", "draw world: " + (System.currentTimeMillis() - millis));
        //millis = System.currentTimeMillis();
        Message m = handler.obtainMessage();
        m.what = 0;
        m.sendToTarget();
        //Log.d("updateLoop", "Handle messages: " + (System.currentTimeMillis() - millis));
    }
}
