package Game.Framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import Game.Container;
import Game.Movers.Player;

/**
 * Created by Emil on 8/27/2017.
 */

public class World {

    private Player player;
    private Container blocks;

    //private Container enemies;

    public World() {
        player = LevelCreator.getPlayer();
        blocks = LevelCreator.getBlocks();
        //enemies = LevelCreator.getEnemies();
    }

    public void update() {
        player.update();
        //enemies.update();

        CollisionHandler.handleAllCollisions(player, blocks);
    }



    public void draw(Canvas canvas) {
        player.draw(canvas);
        //enemies.draw(canvas);
        blocks.draw(canvas);


    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
