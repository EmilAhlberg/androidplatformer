package Game.Framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
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

    public World(GameDisplay display) {
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
        blocks.draw(canvas, player.getRect());
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
