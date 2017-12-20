package game.framework;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import game.util.GameTime;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop implements Runnable {

    private GameMonitor monitor;
    private Handler handler;
    private final int timeLimit = 12; //!!! ~77 ticks/second
    private GameTime gameTime;

    public GameLoop(Handler handler, GameMonitor monitor) {
        this.handler = handler;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        gameTime = new GameTime(lastTime);
        while (!Thread.currentThread().isInterrupted()) {
            gameTime.update(System.currentTimeMillis());
            Bitmap bitmap = monitor.nextFrame(gameTime, !handler.hasMessages(0), timeLimit);
            sendMessage(bitmap);
        }
    }

    private void sendMessage(Bitmap bitmap) {
        if (bitmap != null) {
            Message m = handler.obtainMessage();
            m.what = 0;
            m.obj = bitmap;
            m.sendToTarget();
        }
    }
}
