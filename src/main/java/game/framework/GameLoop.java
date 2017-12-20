package game.framework;

/**
 * Created by Emil on 2016-11-04.
 */

public class GameLoop implements Runnable {

    private GameMonitor monitor;

    public GameLoop(GameMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
                monitor.nextGameCycle();
        }
    }
}
