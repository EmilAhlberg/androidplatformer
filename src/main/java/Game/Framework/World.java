package Game.Framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;

import Game.Android.GameActivity;

import Game.Container;
import Game.Draw.Particle;
import Game.Draw.Particles;
import Game.Movers.Player;
import Game.Util.GameTime;

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
        gameActivity.gameOver();
    }

    public void nextLevel() {
        gameActivity.nextLevel();
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
