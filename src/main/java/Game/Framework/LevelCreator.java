package Game.Framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import com.example.emil.Framework.GameActivity;
import com.example.emil.app.R;
import Game.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;

import Game.InAnimates.Block;
import Game.InAnimates.Fire;
import Game.InAnimates.StandardBlock;
import Game.Movers.Player;

/**
 * Created by Emil on 2016-11-22.
 */

public class LevelCreator {

    private static Player player;
    private static Container blocks;
    private static Container hazards;
    //private static Container enemies;

    public static void createLevel(GameActivity ga, int level) {
        String[] mapString = getLevelArray(ga, level);

        ArrayList<GameObject> bs = new ArrayList<>();
        ArrayList<GameObject> hs = new ArrayList<>();

        //enemies = new Container();
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                Point p = new Point((k-1) * Block.BLOCK_WIDTH, i * Block.BLOCK_HEIGHT); //k-1 vänsterorienterar objekt
                switch (mapString[i].charAt(k)) {
                    case 'B':
                        bs.add(new StandardBlock(p));
                        break;
                    case 'P':
                        player = new Player(p);
                        break;
                    case 'F':
                        hs.add(new Fire(p));
                        break;
                }
            }
        }

        blocks = new Container(bs);
        hazards = new Container(hs);
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

//    public static Container getEnemies() {
//        return enemies;
//    }

    public static Container getHazards() {
        return hazards;
    }

    public static Container getBlocks() {
        return blocks;
    }

    public static Player getPlayer() {
        return player;
    }
}
