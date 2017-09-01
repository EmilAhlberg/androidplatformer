package Game.Draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.emil.Framework.GameActivity;
import com.example.emil.app.R;

import java.util.HashMap;

import Game.InAnimates.Block;
import Game.Movers.Player;

/**
 * Created by Emil on 25/08/2017.
 */

public class IDHandler {

    public static Bitmap[] bitmaps = new Bitmap[100];
    public static SpriteSheet[] sheets = new SpriteSheet[100];

    public static SpriteSheet getSpriteSheet(IDs id) {
       if (sheets[id.ordinal()] != null)
           return sheets[id.ordinal()];
       else
           return sheets[IDs.DEFAULT.ordinal()];
    }

    //100% safe?
    public static Bitmap getBitmap(IDs id) {
        if (bitmaps[id.ordinal()] != null)
            return bitmaps[id.ordinal()];
        else
            return bitmaps[IDs.DEFAULT.ordinal()];
    }

    private static HashMap<Class, IDs> typeToID = new HashMap<Class, IDs>() {
        {
            put(Player.class, IDs.PLAYER);
            put(Block.class, IDs.BLOCK);
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
        IDHandler.sheets[IDs.DEFAULT.ordinal()] = new SpriteSheet(IDs.DEFAULT, 20);

        IDHandler.bitmaps[IDs.PLAYER.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.player, options);
        IDHandler.sheets[IDs.PLAYER.ordinal()] = new SpriteSheet(IDs.PLAYER, 40);

        IDHandler.bitmaps[IDs.BLOCK.ordinal()] = BitmapFactory.decodeResource(gA.getResources(), R.drawable.block, options);
        IDHandler.sheets[IDs.BLOCK.ordinal()] = new SpriteSheet(IDs.BLOCK, 20);

        AnimationInfo.initAnimationInfo();
    }
}
