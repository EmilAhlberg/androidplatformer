package Game;

import java.util.HashMap;

import Game.Draw.ID;

/**
 * Created by Emil on 08/09/2017.
 */

public class Stats {

    private static HashMap<ID, Integer> width = new HashMap<ID, Integer>() {
        {
            put(ID.PLAYER, 20);
            put(ID.BLOCK, 20);
            put(ID.FIRE, 20);
            put(ID.GOAL, 40);
        }
    };

    private static HashMap<ID, Integer> height = new HashMap<ID, Integer>() {
        {
            put(ID.PLAYER, 30);
            put(ID.BLOCK, 20);
            put(ID.FIRE, 16);
            put(ID.GOAL, 40);
        }
    };

    private static HashMap<ID, Double> friction = new HashMap<ID, Double>() {
        {
            put(ID.BLOCK, 0.2);
        }
    };

    private static HashMap<ID, Integer> stuff = new HashMap<ID, Integer>() {
        {

        }
    };

    private static HashMap<ID, Integer> etc = new HashMap<ID, Integer>() {
        {

        }
    };


    public static int width(ID id) {
        return width.get(id);
    }

    public static int height(ID id) {
        return height.get(id);
    }

    public static double friction(ID id) {
        return friction.get(id);
    }
}
