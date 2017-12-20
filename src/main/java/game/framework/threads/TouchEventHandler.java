package game.framework.threads;

import java.util.concurrent.LinkedBlockingQueue;

import game.framework.GameMonitor;
import game.util.MotionEventInfo;

/**
 * Created by Emil on 20/12/2017.
 */

public class TouchEventHandler implements Runnable {
    private GameMonitor monitor;
    private LinkedBlockingQueue<MotionEventInfo> blockingQueue;

    public TouchEventHandler(LinkedBlockingQueue<MotionEventInfo> blockingQueue, GameMonitor monitor) {
        this.blockingQueue = blockingQueue;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                monitor.putTouchEvent(blockingQueue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
