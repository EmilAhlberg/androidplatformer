package Game.Util;

/**
 * This class defines which sprites in sheet that'll make up the animation.
 * Created by Emil on 01/09/2017.
 */

public class AnimationInfo {

    private int startColumn;
    private int startRow;
    private int endColumn;
    private int endRow;
    private String animationName;

    public AnimationInfo(int sc, int sr, int ec, int er, String name) {
        startColumn = sc;
        startRow = sr;
        endColumn = ec;
        endRow = er;
        animationName = name; //tvek
    }

}
