package Game.Util;

import android.graphics.drawable.Drawable;

import java.util.HashMap;

import Game.InAnimates.Block;

/**
 * Created by Emil on 25/08/2017.
 */

public class IDHandler {

    public static Drawable[] drawables = new Drawable[100];

    public static Drawable getDrawable(IDs id) {
       if (drawables[id.ordinal()] != null)
           return drawables[id.ordinal()];
       else
           return drawables[IDs.DEFAULT.ordinal()];
    }

    /*private static HashMap<Class, IDs> typeToID = new HashMap<Class, IDs>() {
        {
            put(Player.class, IDs.PLAYER);
            put(Block.class, IDs.BLOCK);
        }
    };*/

    /*public static IDs getID(Class c) {
        if (typeToID.containsKey(c))
            return typeToID.get(c);
        else
            return IDs.DEFAULT;
    }*/
}
