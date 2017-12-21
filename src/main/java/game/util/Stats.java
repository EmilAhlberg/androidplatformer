package game.util;

import java.util.HashMap;

import game.util.ID;

/**
 * Class keeping track of hard-coded parameters.
 * Created by Emil on 08/09/2017.
 */

public class Stats {

    private final static int DEFAULT = 0;

    private static HashMap<ID, Integer> width = new HashMap<ID, Integer>() {
        {
            put(ID.DEFAULT, 40);
            put(ID.PLAYER, 50);
            put(ID.BLOCK, 20);
            put(ID.FIRE, 20);
            put(ID.GOAL, 40);
            put(ID.CAT, 20);
            put(ID.EXPLOSION, 3);
            put(ID.JUMP, 6);
            put(ID.OBJECTDEATH, 20);
        }
    };

    private static HashMap<ID, Integer> height = new HashMap<ID, Integer>() {
        {
            put(ID.DEFAULT, 40);
            put(ID.PLAYER, 50);
            put(ID.BLOCK, 20);
            put(ID.FIRE, 20);
            put(ID.GOAL, 40);
            put(ID.CAT, 20);
            put(ID.EXPLOSION, 3);
            put(ID.JUMP, 2);
            put(ID.OBJECTDEATH, 20);
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

    private static HashMap<ID, Float> friction = new HashMap<ID, Float>() {
        {
            put(ID.BLOCK, 0.2f);
        }
    };

    private static HashMap<ID, Boolean> gravity = new HashMap<ID, Boolean>() {
        {
            put(ID.EXPLOSION, false);
            put(ID.JUMP, false);
            put(ID.OBJECTDEATH, true);
        }
    };

    private static HashMap<ID, Integer> particleLife = new HashMap<ID, Integer>() {
        {
            put(ID.EXPLOSION, 300);
            put(ID.JUMP, 200);
            put(ID.OBJECTDEATH, 400);
        }
    };

//    private static HashMap<ID, Float> particleSpeed = new HashMap<ID, Float>() {
//        {
//            put(ID.EXPLOSION, (float)3);
//            put(ID.JUMP, (float)2);
//            put(ID.OBJECTDEATH, (float)1.);
//        }
//    };

    /**
     * Retrieves the width of an object based on object type
     * @param id The type of object
     * @return The width, if the ID was present, DEFAULT otherwise.
     */

    public static int width(ID id) {
        if (width.containsKey(id))
            return width.get(id);
        return DEFAULT;
    }

    /**
     * Retrieves the height of an object based on object type
     * @param id The type of object
     * @return The height, if the ID was present, DEFAULT otherwise.
     */
    public static int height(ID id) {
        if (height.containsKey(id))
            return height.get(id);
        return DEFAULT;
    }

    /**
     * Retrieves the friction of an object based on object type
     * @param id The type of object.
     * @return The friction, if the ID was present, DEFAULT otherwise.
     */

    public static float friction(ID id) {
        if (friction.containsKey(id))
            return friction.get(id);
        return DEFAULT;
    }

    /**
     * Retrieves the symbol of an object based on object type, used by LevelCreator in
     * interpreting level xml-files.
     * @param id The type of object.
     * @return The symbol, if the ID was present, "!" otherwise.
     */
    public static char symbol(ID id) {
        if (symbol.containsKey(id))
            return symbol.get(id);
        return '!';
    }

    /**
     * Retrieves the lifetime of a particle based on particle type.
     * @param id The type of particle.
     * @return The lifetime, if the ID was present, DEFAULT otherwise.
     */
    public static int particleLife(ID id) {
        if (particleLife.containsKey(id))
            return particleLife.get(id);
        return DEFAULT;
    }

//    /**
//     * Retrieves the speed of a particle based on particle type.
//     * @param id The type of particle.
//     * @return The speed, if the ID was present, DEFAULT otherwise.
//     */
//    public static float particleSpeed(ID id) {
//        if (particleSpeed.containsKey(id))
//            return particleSpeed.get(id);
//        return DEFAULT;
//    }

    public static boolean affectedByGravity(ID id) {
        if (gravity.containsKey(id))
            return gravity.get(id);
        return false;
    }
}
