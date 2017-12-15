package game.objectinformation;

/**
 * Enumerates object types, first enumerable.ordinal() == 0.
 *
 * HOW TO ADD NEW SPRITES:
 *      1. Add drawable to res/drawable folder.
 *      2. Add object ID to this enum list.
 *      3. Add drawable to IDHandler spriteSheet list, method initialize.
 *      4. Make according additions to class IDHandler's typeToID HashMap. (if needed)
 *      5. Set object stats in Stats!
 *      6. Profit.
 *
 *
 * Created by Emil on 25/08/2017.
 */

public enum ID {

    DEFAULT,
    PLAYER,
    FIRE,
    CAT,


    BLOCK,
    GOAL,


    //particle effects!
    JUMP,
    EXPLOSION,

    //levelcreator IDs
    LEVELPLAYER,
    LEVELBLOCKS,
    LEVELHAZARDS,
    LEVELENEMIES,
    LEVELINTERACTIVES

}


