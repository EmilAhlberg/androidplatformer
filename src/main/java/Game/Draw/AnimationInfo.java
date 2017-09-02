package Game.Draw;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class defines which sprites in sheet that'll make up the animation.
 * Created by Emil on 01/09/2017.
 */

public class AnimationInfo {

    public final static int DEFAULT = 0;
    public final static int RUNNING = 1;
    public final static int JUMPING = 2;

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
        AnimationInfo pRunning = new AnimationInfo(0, 1, 2, 1);
        animationInfos[IDs.PLAYER.ordinal()].put(RUNNING, pRunning);


        //BLOCK

        //HAZARDS
        AnimationInfo fire = new AnimationInfo(0, 0, 2, 0);
        animationInfos[IDs.FIRE.ordinal()].put(DEFAULT, fire);

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

    public static AnimationInfo getAnimationInfo(IDs id, int animationType) {
        return animationInfos[id.ordinal()].get(animationType);
    }

}
