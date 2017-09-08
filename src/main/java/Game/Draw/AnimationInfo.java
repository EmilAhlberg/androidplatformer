package Game.Draw;

import java.util.HashMap;

/**
 * This class defines which sprites in sheet that'll make up the animation.
 * Created by Emil on 01/09/2017.
 */

public class AnimationInfo {

    public final static int NO_ANIMATION = -1;
    public final static int DEFAULT = 0;
    public final static int RUNNING = 1;
    public final static int JUMPING_LEFT = 2;
    public final static int JUMPING_RIGHT = 3;

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
        AnimationInfo pRunning = new AnimationInfo(0, 0, 2, 0);
        animationInfos[ID.PLAYER.ordinal()].put(RUNNING, pRunning);

        AnimationInfo pLeftJumping = new AnimationInfo(0, 1, 0, 1);
        animationInfos[ID.PLAYER.ordinal()].put(JUMPING_LEFT, pLeftJumping);

        AnimationInfo pRightJumping = new AnimationInfo(1, 1, 1, 1);
        animationInfos[ID.PLAYER.ordinal()].put(JUMPING_RIGHT, pRightJumping);

        //BLOCK

        //HAZARDS
        AnimationInfo fire = new AnimationInfo(0, 0, 2, 0);
        animationInfos[ID.FIRE.ordinal()].put(DEFAULT, fire);

        //etc

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
