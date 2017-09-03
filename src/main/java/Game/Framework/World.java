package Game.Framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

import com.example.emil.Framework.GameActivity;

import Game.Container;
import Game.Movers.Player;

/**
 * Created by Emil on 8/27/2017.
 */

public class World {

    private Player player;
    private Container blocks;
    private Container hazards;

    private GameActivity gameActivity;

    //private Container enemies;

    public World(GameActivity ga) {
        gameActivity = ga;
        player = LevelCreator.getPlayer();
        player.setWorld(this);
        blocks = LevelCreator.getBlocks();
        hazards = LevelCreator.getHazards();
        //enemies = LevelCreator.getEnemies();
    }

    public void update() {
        player.update();
        //enemies.update();

        CollisionHandler.handleAllCollisions(player, blocks, hazards);
    }

    public void draw(Canvas canvas) {
        player.draw(canvas);
        //enemies.draw(canvas);
        blocks.draw(canvas, player.getRect());
        hazards.draw(canvas, player.getRect());
    }

    public void gameOver() {
        Log.d("wGameOver: ", "ok");
        gameActivity.gameOver();
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
