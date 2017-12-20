package game.framework.util;

/**
 *
 * Created by Emil on 25/11/2017.
 */


public class GameTime {

    private double startTime;
    private double currentTime;
    private final int timeLimit = 12; //!!! ~77 ticks/second

    /**
     * Creates an instance of GameTime, which syncs the time of the game.
     * @param startTime the time of the game start.
     */
    public GameTime(double startTime) {
        this.startTime = startTime;
        this.currentTime = startTime;
    }

    /**
     * Updates the GameTime class.
     */

    public void update() {
        currentTime += timeLimit;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    /**
     * Used to add time to various updating counters in game related classes.
     * @return the elapsed time (milliseconds) of the latest updateLoop.
     */
    public double elapsedTime() {
        return timeLimit;
    }

}
