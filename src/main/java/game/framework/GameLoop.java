package game.framework;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import game.android.GameActivity;
import game.movers.Player;
import game.util.GameTime;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop implements Runnable {

    private GameActivity game;
    private Handler handler;
    private final int timeLimit = 12; //!!! ~77 ticks/second
    private GameTime gameTime;
    private GameDraw draw;

    public GameLoop(GameActivity game, Handler handler, Player p, World world) {
        this.game = game;
        this.handler = handler;
        draw = new GameDraw(game, p, world);
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long newTime;
        gameTime = new GameTime(lastTime);
        while (!Thread.currentThread().isInterrupted()) {

            newTime = System.currentTimeMillis();
            long temp = timeLimit - (newTime - lastTime);
            if (temp > 0) {
                try {
                    Thread.sleep((int)temp); //TODO: Change this to a timed wait that can be notified by the ui-thread for smoother gameplay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            gameTime.update(System.currentTimeMillis());
            updateLoop(gameTime);
            lastTime = newTime;
        }
    }

    private void updateLoop(GameTime gameTime) {
        game.updateWorld(gameTime);

        if (!handler.hasMessages(0)) {
            Bitmap b = draw.drawGame(gameTime);
            Message m = handler.obtainMessage();
            m.what = 0;
            m.obj = b;
            m.sendToTarget();
        }
    }
}
