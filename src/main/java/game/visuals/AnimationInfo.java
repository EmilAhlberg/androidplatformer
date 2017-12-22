package game.visuals;

import java.util.HashMap;

import game.util.ID;
import game.util.IDHandler;

/**
 * This class defines which sprites in sheet that'll make up the animation.
 * Created by Emil on 01/09/2017.
 */

public class AnimationInfo {

    public final static int NO_ANIMATION = -1;
    public final static int DEFAULT = 0;
    public final static int RUNNING_RIGHT = 1;
    public final static int RUNNING_LEFT = 2;
    public final static int JUMPING_LEFT = 3;
    public final static int JUMPING_RIGHT = 4;
    public final static int STANDING_STILL = 5;
    public final static int VACUUM_LEFT = 6;
    public final static int VACUUM_RIGHT = 7;

    private static HashMap<Integer, AnimationInfo>[] animationInfos;

    private int startColumn;
    private int startRow;
    private int endColumn;
    private int endRow;

    public AnimationInfo(int startColumn, int startRow, int endColumn, int endRow) {
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.endColumn = endColumn;
        this.endRow = endRow;
    }

    public static void initAnimationInfo() {

        //SETUP PART
        animationInfos = new HashMap[IDHandler.IMAGE_CAP];
        for (int i = 0; i < animationInfos.length; i++) {
            animationInfos[i] = new HashMap<>();
            animationInfos[i].put(DEFAULT, new AnimationInfo(0,0,0,0));
        }

        //PLAYER
        AnimationInfo pRightRunning = new AnimationInfo(0, 0, 3, 0);
        animationInfos[ID.PLAYER.ordinal()].put(RUNNING_RIGHT, pRightRunning);

        AnimationInfo pLeftRunning = new AnimationInfo(4, 0, 7, 0);
        animationInfos[ID.PLAYER.ordinal()].put(RUNNING_LEFT, pLeftRunning);

        AnimationInfo pLeftJumping = new AnimationInfo(2, 1, 2, 1);
        animationInfos[ID.PLAYER.ordinal()].put(JUMPING_LEFT, pLeftJumping);

        AnimationInfo pRightJumping = new AnimationInfo(1, 1, 1, 1);
        animationInfos[ID.PLAYER.ordinal()].put(JUMPING_RIGHT, pRightJumping);

        AnimationInfo pStandingStill = new AnimationInfo(0, 1, 0, 1);
        animationInfos[ID.PLAYER.ordinal()].put(STANDING_STILL, pStandingStill);

        //BLOCK

        //HAZARDS
        AnimationInfo fire = new AnimationInfo(0, 0, 2, 0);
        animationInfos[ID.FIRE.ordinal()].put(DEFAULT, fire);

        //ENEMIES
        AnimationInfo vacuumLeft = new AnimationInfo(0,1,1,1);
        AnimationInfo vacuumRight = new AnimationInfo(0,0,1,0);
        animationInfos[ID.VACUUM.ordinal()].put(VACUUM_LEFT, vacuumLeft);
        animationInfos[ID.VACUUM.ordinal()].put(VACUUM_RIGHT, vacuumRight);
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public static AnimationInfo getAnimationInfo(ID id, int animationType) {
        return animationInfos[id.ordinal()].get(animationType);
    }

}
