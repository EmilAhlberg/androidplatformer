package game.framework;

import game.android.GameActivity;
import com.example.emil.app.R;
import game.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import game.objectinformation.ID;
import game.draw.Particles;
import game.inanimates.Block;
import game.inanimates.Fire;
import game.inanimates.Goal;
import game.movers.Cat;
import game.movers.Player;
import game.objectinformation.Stats;
import game.util.Vector;

/**
 * Created by Emil on 2016-11-22.
 */

public class LevelCreator {


    private static final int ENEMY_NUMBER = 10;

    public static HashMap<ID,ArrayList<GameObject>> createLevel(GameActivity ga, int level) {
        String[] mapString = getLevelArray(ga, level);
        ArrayList<GameObject> p = new ArrayList<>();
        ArrayList<GameObject> bs = new ArrayList<>();
        ArrayList<GameObject> hs = new ArrayList<>();
        ArrayList<GameObject> is = new ArrayList<>();
        ArrayList<GameObject> es = new ArrayList<>();

        //Should reset particles!
        Particles.reset();

        initActivateables(p,es);
        //Possible multithread / performance optimization here
        //Create and add the big blocks
        constructBigObjects(mapString, bs, ID.BLOCK);
        //Create and add the big fires
        constructBigObjects(mapString, hs, ID.FIRE);
        //Create and add other objects
        constructSingleObjects(mapString, is, es, p);

        HashMap<ID, ArrayList<GameObject>> map = new HashMap<>();
        map.put(ID.LEVELPLAYER, p);
        map.put(ID.LEVELBLOCKS, bs);
        map.put(ID.LEVELENEMIES, es);
        map.put(ID.LEVELHAZARDS, hs);
        map.put(ID.LEVELINTERACTIVES, is);

        return map;
    }

    private static void initActivateables(ArrayList<GameObject> p, ArrayList<GameObject> es) {
        p.add(new Player(World.DEFAULT_POSITION));
        for(int i = 0; i < ENEMY_NUMBER; i++) {
            es.add(new Cat(World.DEFAULT_POSITION));
        }
    }

    private static void constructSingleObjects(String[] mapString, ArrayList<GameObject> is, ArrayList<GameObject> es, ArrayList<GameObject> pList){
        Vector v;
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                v = new Vector((k-1) * Stats.width(ID.BLOCK), i * Stats.height(ID.BLOCK)); //k-1 vänsterorienterar objekt
                switch (mapString[i].charAt(k)) {
                    case 'P':
                        activateObject(pList, v);
                        break;
                    case 'G':
                        is.add(new Goal(v));
                        break;
                    case 'C':
                        activateObject(es, v);
                        break;
                }
            }
        }
    }

    private static void activateObject(ArrayList<GameObject> list, Vector v) {
        for(GameObject g : list) {
            if (!g.isActive()) {
                g.activate(v);
                break;
            }
        }
    }

    private static void constructBigObjects(String[] mapString, ArrayList<GameObject> list, ID id) {
        int currentSize = 0;
        Vector v = new Vector(0, 0);
        for (int i = 0; i < mapString.length; i++) {
            for (int k = 0; k < mapString[i].length(); k++) {
                if (mapString[i].charAt(k) == Stats.symbol(id)) {
                    if (currentSize == 0)
                        v = new Vector((k-1) * Stats.width(ID.BLOCK), i * Stats.height(ID.BLOCK)); //blocks constitutes the minimum grid! has to scale correctly to txt
                    currentSize++;
                } else if (currentSize > 0) {
                    if (currentSize > 1)
                        list.add(factory(v, currentSize, true, id));
                    currentSize = 0;
                }
            }
            if (currentSize > 0) {
                if (currentSize > 1)
                    list.add(factory(v, currentSize, true, id));
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
                            v = new Vector((i-1) * Stats.width(ID.BLOCK), k * Stats.height(ID.BLOCK));   //blocks constitutes the minimum grid!
                        currentSize++;
                    } else if (currentSize > 0) {
                        if (currentSize > 1)
                            list.add(factory(v, currentSize, false, id));
                        currentSize = 0;
                    }
                } else if (currentSize > 0) {
                    if (currentSize > 1)
                        list.add(factory(v, currentSize, false, id));
                    currentSize = 0;
                }
            }
            if (currentSize > 0) {
                if (currentSize > 1)
                    list.add(factory(v, currentSize, false, id));
                currentSize = 0;
            }
        }
    }

    private static GameObject factory(Vector v, int currentSize, boolean orientation, ID id) {
        GameObject g = null;
        switch (id) {
            case BLOCK:
                g = new Block(v,currentSize,orientation);
                break;
            case FIRE:
                g = new Fire(v,currentSize,orientation);
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
}