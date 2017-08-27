package Game.Framework;

import android.graphics.Point;

import com.example.emil.Framework.GameActivity;
import com.example.emil.app.R;
import Game.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import Game.InAnimates.Block;
import Game.Movers.Player;

/**
 * Created by Emil on 2016-11-22.
 */

public class LevelCreator {

    public static ArrayList<GameObject> createLevel(GameActivity ga, int level) {
        ArrayList<GameObject> temp = new ArrayList<GameObject>();
        String[] mapString = getLevelArray(ga, level);
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                Point p = new Point((k-1) * Block.BLOCK_WIDTH, i * Block.BLOCK_HEIGHT); //k-1 vänsterorienterar objekt
                switch (mapString[i].charAt(k)) {
                    case 'B':
                        Block b = new Block(p);
                        temp.add(b);
                        break;
                    case 'P':
                        Player player = new Player(p);
                        temp.add(0, player);
                        break;
                }
            }
        }
        return temp;
    }

    private static String[] getLevelArray(GameActivity ga, int level) {
        String[] map;
        try {
            switch (level) {
                case 1:
                    map = getStringArrayFromFile(ga, R.raw.level1);
                    break;
                default:
                    map = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        }

        return map;
    }

    private static String[] getStringArrayFromFile(GameActivity ga, int id) throws Exception {
        InputStream fin = ga.getResources().openRawResource(id);
        //FileInputStream fin = new FileInputStream(fl);
        String[] ret = convertStreamToStringArray(fin);
        fin.close();
        return ret;
    }

    private static String[] convertStreamToStringArray(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ArrayList<String> strings = new ArrayList<String>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (line.charAt(4) == '§') {         //skalar bort ram, unviker null vid tom rad
                strings.add(line.substring(4));
            }
        }
        reader.close();
        Object[] objects = strings.toArray();
        String[] stringArray = Arrays.copyOf(objects, objects.length, String[].class);
        return stringArray;
    }
}
