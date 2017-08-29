package Game.Framework;

import android.os.Handler;
import android.os.Message;

import com.example.emil.Framework.GameActivity;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop {

    private GameActivity game;
    private final int timeLimit = 15;
    private Handler handler;
    private boolean running;
    private Thread t;

    public GameLoop(GameActivity game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void startLoop() {
        running = true;
        t = new Thread(new Runnable() {
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
        });
        t.start();
    }

    public void interrupt() {
        t.interrupt();
    }

    private void updateLoop() {
        //long millis = System.currentTimeMillis();
        game.updateWorld();
        game.drawWorld();
        //Log.d("updateLoop", "Update world: " + (System.currentTimeMillis() - millis));
        //millis = System.currentTimeMillis();
        Message m = handler.obtainMessage();
        m.sendToTarget();
        //Log.d("updateLoop", "Handle messages: " + (System.currentTimeMillis() - millis));
    }

    public void pauseLoop() {
        running = false;
    }


}
