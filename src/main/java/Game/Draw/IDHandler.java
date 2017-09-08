package Game.Draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.emil.Framework.GameActivity;
import com.example.emil.app.R;

import java.util.HashMap;

import Game.InAnimates.BigBlock;
import Game.InAnimates.Block;
import Game.InAnimates.Fire;
import Game.InAnimates.Goal;
import Game.InAnimates.StandardBlock;
import Game.Movers.Player;

/**
 * Created by Emil on 25/08/2017.
 */

public class IDHandler {

    public final static int IMAGE_CAP = 100;

    public static Bitmap[] bitmaps = new Bitmap[IMAGE_CAP];
    public static SpriteSheet[] sheets = new SpriteSheet[IMAGE_CAP];

    public static SpriteSheet getSpriteSheet(IDs id) {
       if (sheets[id.ordinal()] != null)
           return sheets[id.ordinal()];
       else
           return sheets[IDs.DEFAULT.ordinal()];
    }


    public static Bitmap getBitmap(IDs id) {
        if (bitmaps[id.ordinal()] != null)
            return bitmaps[id.ordinal()];
        else
            return bitmaps[IDs.DEFAULT.ordinal()];
    }

    private static HashMap<Class, IDs> typeToID = new HashMap<Class, IDs>() {
        {
            put(Player.class, IDs.PLAYER);
            put(StandardBlock.class, IDs.STANDARDBLOCK);
            put(Fire.class, IDs.FIRE);
            put(Goal.class, IDs.GOAL);
            put(BigBlock.class, IDs.BIGBLOCK);
        }
    };

    public static IDs getID(Class c) {
        if (typeToID.containsKey(c))
            return typeToID.get(c);
        else
            return IDs.DEFAULT;
    }

    public static void initialize(GameActivity gA) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //opts.inDither = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inScaled = false; //skalade om bildstorlek innan, wtf

        IDHandler.bitmaps[IDs.DEFAULT.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.startscreen, options);  //default pic
        IDHandler.sheets[IDs.DEFAULT.ordinal()] = new SpriteSheet(IDs.DEFAULT, 20, 20);

        IDHandler.bitmaps[IDs.PLAYER.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.player_moves, options);
        IDHandler.sheets[IDs.PLAYER.ordinal()] = new SpriteSheet(IDs.PLAYER, 20, 30);

        IDHandler.bitmaps[IDs.STANDARDBLOCK.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.block2, options);
        IDHandler.sheets[IDs.STANDARDBLOCK.ordinal()] = new SpriteSheet(IDs.STANDARDBLOCK, 20, 20);

        IDHandler.bitmaps[IDs.BIGBLOCK.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.big_block, options);
        IDHandler.sheets[IDs.BIGBLOCK.ordinal()] = new SpriteSheet(IDs.BIGBLOCK, 20, 20);

        IDHandler.bitmaps[IDs.FIRE.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.fire_sprite, options);
        IDHandler.sheets[IDs.FIRE.ordinal()] = new SpriteSheet(IDs.FIRE, 20, 20);

        IDHandler.bitmaps[IDs.GOAL.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.goal, options);
        IDHandler.sheets[IDs.GOAL.ordinal()] = new SpriteSheet(IDs.GOAL, 40, 40);

        AnimationInfo.initAnimationInfo();
    }
}
