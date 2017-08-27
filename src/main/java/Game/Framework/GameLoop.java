package Game.Framework;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop {

    private World world;
    private final int timeLimit = 15;
    private Handler handler;
    private boolean running;

    public GameLoop(World world, Handler handler) {
        this.world = world;
        this.handler = handler;
    }

    public void startLoop() {
        running = true;
        new Thread(new Runnable() {
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
        }).start();
    }

    private void updateLoop() {
        //long millis = System.currentTimeMillis();
        world.updateWorld();
        world.drawWorld();
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
