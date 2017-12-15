package game.framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;

import game.android.GameActivity;

import game.Container;
import game.draw.Particles;
import game.movers.Player;
import game.util.GameTime;

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
    private Handler signal;

    //private Container enemies;

    public World(GameActivity ga, Handler signal) {
        gameActivity = ga;
        player = LevelCreator.getPlayer();
        player.setWorld(this);
        blocks = LevelCreator.getBlocks();
        hazards = LevelCreator.getHazards();
        interactives = LevelCreator.getInteractives();
        enemies = LevelCreator.getEnemies();
        this.signal = signal;

        //enemies = LevelCreator.getEnemies();

        //!!

        //!!
        ch = new CollisionHandler();
    }

    public void update(GameTime gameTime) {
        player.update(gameTime);
        enemies.update(gameTime);

        Particles.update(gameTime);
        ch.handleAllCollisions(player, blocks, hazards, interactives, enemies);
    }

    public void draw(Canvas canvas, GameTime gameTime) {
        player.draw(canvas, gameTime);
        enemies.draw(canvas, gameTime, player.getRect() ); //rect param redundant atm
        blocks.draw(canvas, gameTime, player.getRect());
        hazards.draw(canvas, gameTime, player.getRect());
        interactives.draw(canvas, gameTime, player.getRect());
        Particles.draw(canvas, gameTime);


    }

    public void gameOver() {
        Message m = signal.obtainMessage();
        m.what = 1;
        m.sendToTarget();
    }

    public void nextLevel() {
        Message m = signal.obtainMessage();
        m.what = 2;
        m.sendToTarget();
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
