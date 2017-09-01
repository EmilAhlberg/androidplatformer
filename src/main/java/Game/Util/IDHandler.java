package Game.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;

import Game.InAnimates.Block;
import Game.Movers.Player;

/**
 * Created by Emil on 25/08/2017.
 */

public class IDHandler {

    public static Bitmap[] bitmaps = new Bitmap[100];

//    public static Bitmap getDrawable(IDs id) {
//       if (bitmaps[id.ordinal()] != null)
//           return bitmaps[id.ordinal()];
//       else
//           return bitmaps[IDs.DEFAULT.ordinal()];
//    }

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
}
