package Game.Draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import Game.Android.GameActivity;
import com.example.emil.app.R;

import java.util.HashMap;

import Game.InAnimates.Block;
import Game.InAnimates.Fire;
import Game.InAnimates.Goal;
import Game.Movers.Cat;
import Game.Movers.Player;
import Game.Stats;

/**
 * Created by Emil on 25/08/2017.
 */

public class IDHandler {

    public final static int IMAGE_CAP = 100;

    public static SpriteSheet[] sheets = new SpriteSheet[IMAGE_CAP];

    public static SpriteSheet getSpriteSheet(ID id) {
       if (sheets[id.ordinal()] != null)
           return sheets[id.ordinal()];
       else
           return sheets[ID.DEFAULT.ordinal()];
    }

    public static ID getID(Class c) {
        if (typeToID.containsKey(c))
            return typeToID.get(c);
        else
            return ID.DEFAULT;
    }

    private static HashMap<Class, ID> typeToID = new HashMap<Class, ID>() {
        {
            put(Player.class, ID.PLAYER);
            put(Block.class, ID.BLOCK);
            put(Fire.class, ID.FIRE);
            put(Goal.class, ID.GOAL);
            put(Cat.class, ID.CAT);
        }
    };

    public static void initialize(GameActivity gA) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inScaled = false;

        IDHandler.sheets[ID.DEFAULT.ordinal()] = new SpriteSheet(BitmapFactory.decodeResource(gA.getResources(), R.drawable.will_remove_startscreen, options), 50, 50);

        IDHandler.sheets[ID.PLAYER.ordinal()] = new SpriteSheet(BitmapFactory.decodeResource(gA.getResources(), R.drawable.obj_player, options), Stats.width(ID.PLAYER), Stats.height(ID.PLAYER));

        IDHandler.sheets[ID.BLOCK.ordinal()] = new SpriteSheet(BitmapFactory.decodeResource(gA.getResources(), R.drawable.obj_block, options), Stats.width(ID.BLOCK), Stats.height(ID.BLOCK));

        IDHandler.sheets[ID.FIRE.ordinal()] = new SpriteSheet(BitmapFactory.decodeResource(gA.getResources(), R.drawable.obj_fire, options), Stats.width(ID.FIRE), Stats.height(ID.FIRE));

        IDHandler.sheets[ID.GOAL.ordinal()] = new SpriteSheet(BitmapFactory.decodeResource(gA.getResources(), R.drawable.obj_goal, options), Stats.width(ID.GOAL), Stats.height(ID.GOAL));

        IDHandler.sheets[ID.CAT.ordinal()] = new SpriteSheet(BitmapFactory.decodeResource(gA.getResources(), R.drawable.will_remove_block, options), Stats.width(ID.CAT), Stats.height(ID.CAT));

        AnimationInfo.initAnimationInfo();
    }
}
