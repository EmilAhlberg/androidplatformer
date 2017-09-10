package Game.Framework;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.emil.Framework.GameActivity;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop extends Thread {

    private GameActivity game;
    private Handler handler;
    private final int timeLimit = 15;
    private boolean running = true;

    public GameLoop(GameActivity game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    @Override
    public void run() {
        double currentTime = System.currentTimeMillis();
        double newTime = 0;
        while (running) {
            if (newTime - currentTime > timeLimit) {
                updateLoop();
                currentTime = newTime;
            }
            newTime = System.currentTimeMillis();
        }
    }

    private void updateLoop() {
        //long millis = System.currentTimeMillis();
        game.updateWorld();
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
}
