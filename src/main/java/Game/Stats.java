package Game;

import java.util.HashMap;

import Game.Draw.ID;

/**
 * Created by Emil on 08/09/2017.
 */

public class Stats {

    private final static int DEFAULT = 0;

    private static HashMap<ID, Integer> width = new HashMap<ID, Integer>() {
        {
            put(ID.PLAYER, 20);
            put(ID.BLOCK, 20);
            put(ID.FIRE, 20);
            put(ID.GOAL, 40);
            put(ID.CAT, 20);
        }
    };

    private static HashMap<ID, Integer> height = new HashMap<ID, Integer>() {
        {
            put(ID.PLAYER, 30);
            put(ID.BLOCK, 20);
            put(ID.FIRE, 20);
            put(ID.GOAL, 40);
            put(ID.CAT, 20);
        }
    };

    private static HashMap<ID, Character> symbol = new HashMap<ID, Character>() {
        {
            put(ID.PLAYER,'P');
            put(ID.BLOCK, 'B');
            put(ID.FIRE, 'F');
            put(ID.GOAL, 'G');
            put(ID.CAT, 'C');
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
        if (width.containsKey(id))
            return width.get(id);
        return DEFAULT;
    }

    public static int height(ID id) {
        if (height.containsKey(id))
            return height.get(id);
        return DEFAULT;
    }

    public static double friction(ID id) {
        if (friction.containsKey(id))
            return friction.get(id);
        return DEFAULT;
    }

    public static char symbol(ID id) {
        if (symbol.containsKey(id))
            return symbol.get(id);
        return '!';
    }
}
