package game.framework.util;

/**
 * Created by Emil on 20/12/2017.
 */

public class GameState {
    private int GAME_STATE;
    public static final int RUNNING_STATE = 0;
    public static final int NEXT_LEVEL_STATE = 1;
    public static final int GAME_OVER_STATE = 2;

    public GameState() {
        GAME_STATE = RUNNING_STATE;
    }


    public int getGameState() {
        return GAME_STATE;
    }

    public void setGameState(int gameState) {
        GAME_STATE = gameState;
    }
}
