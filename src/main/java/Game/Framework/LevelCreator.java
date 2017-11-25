package Game.Framework;

import android.graphics.Point;

import Game.Android.GameActivity;
import com.example.emil.app.R;
import Game.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import Game.ObjectInformation.ID;
import Game.Draw.Particles;
import Game.InAnimates.Block;
import Game.InAnimates.Fire;
import Game.InAnimates.Goal;
import Game.Movers.Cat;
import Game.Movers.Player;
import Game.ObjectInformation.Stats;

/**
 * Created by Emil on 2016-11-22.
 */

public class LevelCreator {

    private static Player player;
    private static Container blocks;
    private static Container hazards;
    private static Container interactives;
    private static Container enemies;
    //private static Container enemies;

    public static void createLevel(GameActivity ga, int level) {
        String[] mapString = getLevelArray(ga, level);

        ArrayList<GameObject> bs = new ArrayList<>();
        ArrayList<GameObject> hs = new ArrayList<>();
        ArrayList<GameObject> is = new ArrayList<>();
        ArrayList<GameObject> es = new ArrayList<>();

        //Should reset particles!
        Particles.reset();

        //Possible multithread / performance optimization here

        //Create and add the big blocks
        constructBigObjects(mapString, bs, ID.BLOCK);
        //Create and add the big fires
        constructBigObjects(mapString, hs, ID.FIRE);
        //Create and add other objects
        constructSingleObjects(mapString, is, es);

        blocks = new Container(bs);
        hazards = new Container(hs);
        interactives = new Container(is);
        enemies = new Container(es);
    }

    private static void constructSingleObjects(String[] mapString, ArrayList<GameObject> is, ArrayList<GameObject> es){
        Point p;
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                p = new Point((k-1) * Stats.width(ID.BLOCK), i * Stats.height(ID.BLOCK)); //k-1 vänsterorienterar objekt
                switch (mapString[i].charAt(k)) {
                    case 'P':
                        player = new Player(p);
                        break;
                    case 'G':
                        is.add(new Goal(p));
                        break;
                    case 'C':
                        es.add(new Cat(p));
                        break;
                }
            }
        }
    }

    private static void constructBigObjects(String[] mapString, ArrayList<GameObject> list, ID id) {
        int currentSize = 0;
        Point p = new Point(0, 0);
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                if (mapString[i].charAt(k) == Stats.symbol(id)) {
                    if (currentSize == 0)
                        p = new Point((k-1) * Stats.width(ID.BLOCK), i * Stats.height(ID.BLOCK)); //blocks constitutes the minimum grid! has to scale correctly to txt
                    currentSize++;
                } else if (currentSize > 0) {
                    if (currentSize > 1)
                        list.add(factory(p, currentSize, true, id));
                    currentSize = 0;
                }
            }
            if (currentSize > 0) {
                if (currentSize > 1)
                    list.add(factory(p, currentSize, true, id));
                currentSize = 0;
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
                    if (mapString[k].charAt(i) == Stats.symbol(id)) {
                        if (currentSize == 0)
                            p = new Point((i-1) * Stats.width(ID.BLOCK), k * Stats.height(ID.BLOCK));   //blocks constitutes the minimum grid!
                        currentSize++;
                    } else if (currentSize > 0) {
                        if (currentSize > 1)
                            list.add(factory(p, currentSize, false, id));
                        currentSize = 0;
                    }
                } else if (currentSize > 0) {
                    if (currentSize > 1)
                        list.add(factory(p, currentSize, false, id));
                    currentSize = 0;
                }
            }
            if (currentSize > 0) {
                if (currentSize > 1)
                    list.add(factory(p, currentSize, false, id));
                currentSize = 0;
            }
        }
    }

    private static GameObject factory(Point p, int currentSize, boolean orientation, ID id) {
        GameObject g = null;
        switch (id) {
            case BLOCK:
                g = new Block(p,currentSize,orientation);
                break;
            case FIRE:
                g = new Fire(p,currentSize,orientation);
                break;
        }
        return g;
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

    public static Container getEnemies() {
        return enemies;
    }
}
