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

import Game.InAnimates.BigBlock;
import Game.InAnimates.Block;
import Game.InAnimates.Fire;
import Game.InAnimates.Goal;
import Game.InAnimates.StandardBlock;
import Game.Movers.Player;
import Game.Util.MovementHandler;

/**
 * Created by Emil on 2016-11-22.
 */

public class LevelCreator {

    private static Player player;
    private static Container blocks;
    private static Container hazards;
    private static Container interactives;
    //private static Container enemies;

    public static void createLevel(GameActivity ga, int level) {
        String[] mapString = getLevelArray(ga, level);

        ArrayList<GameObject> bs = new ArrayList<>();
        ArrayList<GameObject> hs = new ArrayList<>();
        ArrayList<GameObject> is = new ArrayList<>();

        //Create and add the big blocks
        int temp = 0;
        Point p = new Point(0, 0);
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                if (mapString[i].charAt(k) == 'B') {
                    if (temp == 0)
                        p = new Point((k-1) * StandardBlock.BLOCK_WIDTH, i * StandardBlock.BLOCK_HEIGHT);
                    temp++;
                } else if (temp > 0) {
                    if (temp > 1)
                        bs.add(new BigBlock(p, temp, 0));
                    temp = 0;
                }
            }
            if (temp > 0) {
                if (temp > 1)
                    bs.add(new BigBlock(p, temp, 0));
                temp = 0;
            }
        }

        int lengths[] = new int[mapString.length];
        int max = 0;
        for (int i = 0; i < mapString.length; i++) {
            int l = mapString[i].length();
            lengths[i] = l;
            if (l > max)
                max = l;
        }

        for (int i = 0; i < max; i++) {
            for (int k = 0; k < mapString.length; k++) {
                if (i < lengths[k]) {
                    if (mapString[k].charAt(i) == 'B') {
                        if (temp == 0)
                            p = new Point((i-1) * StandardBlock.BLOCK_WIDTH, k * StandardBlock.BLOCK_HEIGHT);
                        temp++;
                    } else if (temp > 0) {
                        if (temp > 1)
                            bs.add(new BigBlock(p, temp));
                        temp = 0;
                    }
                } else if (temp > 0) {
                    if (temp > 1)
                        bs.add(new BigBlock(p, temp));
                    temp = 0;
                }
            }
            if (temp > 0) {
                if (temp > 1)
                    bs.add(new BigBlock(p, temp));
                temp = 0;
            }
        }

        //enemies = new Container();
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                p = new Point((k-1) * StandardBlock.BLOCK_WIDTH, i * StandardBlock.BLOCK_HEIGHT); //k-1 vänsterorienterar objekt
                switch (mapString[i].charAt(k)) {
                    case 'P':
                        player = new Player(p);
                        break;
                    case 'F':
                        p.y+=StandardBlock.BLOCK_HEIGHT - 16;
                        hs.add(new Fire(p));
                        break;
                    case 'G':
                        is.add(new Goal(p));
                        break;
                }
            }
        }

        blocks = new Container(bs);
        hazards = new Container(hs);
        interactives = new Container(is);
    }

    private static String[] getLevelArray(GameActivity ga, int level) {
        String[] map;
        try {
            switch (level) {
                case 1:
                    map = getStringArrayFromFile(ga, R.raw.level1);
                    break;
                case 2:
                    map = getStringArrayFromFile(ga, R.raw.level2);
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

    public static Container getInteractives() {
        return interactives;
    }

    public static Player getPlayer() {
        return player;
    }
}
