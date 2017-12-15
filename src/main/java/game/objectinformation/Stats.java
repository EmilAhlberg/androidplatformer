package game.objectinformation;

import java.util.HashMap;

/**
 * Class keeping track of hard-coded parameters.
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
            put(ID.EXPLOSION, 3);
            put(ID.JUMP, 3);
            put(ID.CATDEATH, 20);
        }
    };

    private static HashMap<ID, Integer> height = new HashMap<ID, Integer>() {
        {
            put(ID.PLAYER, 30);
            put(ID.BLOCK, 20);
            put(ID.FIRE, 20);
            put(ID.GOAL, 40);
            put(ID.CAT, 20);
            put(ID.EXPLOSION, 3);
            put(ID.JUMP, 3);
            put(ID.CATDEATH, 20);
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

//    private static HashMap<ID, Integer> particleSize = new HashMap<ID, Integer>() {
//        {
//
//        }
//    };

    private static HashMap<ID, Integer> particleLife = new HashMap<ID, Integer>() {
        {
            put(ID.EXPLOSION, 300);
            put(ID.JUMP, 150);
            put(ID.CATDEATH, 400);
        }
    };

    private static HashMap<ID, Float> particleSpeed = new HashMap<ID, Float>() {
        {
            put(ID.EXPLOSION, (float)3);
            put(ID.JUMP, (float)2);
            put(ID.CATDEATH, (float)1.);
        }
    };

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

    public static double friction(ID id) {
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

    /**
     * Retrieves the speed of a particle based on particle type.
     * @param id The type of particle.
     * @return The speed, if the ID was present, DEFAULT otherwise.
     */
    public static float particleSpeed(ID id) {
        if (particleSpeed.containsKey(id))
            return particleSpeed.get(id);
        return DEFAULT;
    }
}
