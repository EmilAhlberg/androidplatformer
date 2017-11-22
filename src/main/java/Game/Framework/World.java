package Game.Framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import Game.Android.GameActivity;

import Game.Container;
import Game.Movers.Player;

/**
 * Created by Emil on 8/27/2017.
 */

public class World {

    public final static int MAP_WIDTH = 2000;
    public final static int MAP_HEIGHT = 1000;
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 480;

    private Player player;
    private Container blocks;
    private Container hazards;
    private Container interactives;
    private Container enemies;
    private CollisionHandler ch;

    private GameActivity gameActivity;

    //private Container enemies;

    public World(GameActivity ga) {
        gameActivity = ga;
        player = LevelCreator.getPlayer();
        player.setWorld(this);
        blocks = LevelCreator.getBlocks();
        hazards = LevelCreator.getHazards();
        interactives = LevelCreator.getInteractives();
        enemies = LevelCreator.getEnemies();

        //enemies = LevelCreator.getEnemies();

        ch = new CollisionHandler();
    }

    public void update() {
        player.update();
        enemies.update();

        ch.handleAllCollisions(player, blocks, hazards, interactives, enemies);
    }

    public void draw(Canvas canvas) {
        player.draw(canvas);
        enemies.draw(canvas, player.getRect()); //rect param redundant atm
        blocks.draw(canvas, player.getRect());
        hazards.draw(canvas, player.getRect());
        interactives.draw(canvas, player.getRect());
    }

    public void gameOver() {
        gameActivity.gameOver();
    }

    public void nextLevel() {
        gameActivity.nextLevel();
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
