package Game.Util;

/**
 *
 * Created by Emil on 25/11/2017.
 */


public class GameTime {

    private double startTime;
    private double previousTime;
    private double currentTime;

    /**
     * Creates an instance of GameTime, which syncs the time of the Game.
     * @param startTime the time of the game start.
     */
    public GameTime(double startTime) {
        this.startTime = startTime;
        this.currentTime = startTime;
        this.previousTime = startTime;
    }

    /**
     * Updates the GameTime class.
     * @param newTime the new syncing time of the updateLoop.
     */

    public void update(double newTime) {
        previousTime = currentTime;
        currentTime = newTime;
    }

    /**
     * Used to add time to various updating counters in game related classes.
     * @return the elapsed time (milliseconds) of the latest updateLoop.
     */
    public double elapsedTime() {
        return currentTime - previousTime;
    }

}
