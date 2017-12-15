package game.framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import game.GameObject;
import game.android.GameActivity;

import game.Container;
import game.draw.Particles;
import game.movers.Player;
import game.objectinformation.ID;
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

    //private Container enemies;

    public World(GameActivity ga, HashMap<ID, ArrayList<GameObject>> levelInfo) {
        gameActivity = ga;
        initWorld(levelInfo);
        player.setWorld(this);
        ch = new CollisionHandler();
    }

    private void initWorld(HashMap<ID, ArrayList<GameObject>> levelInfo) {
        player = (Player) levelInfo.get(ID.LEVELPLAYER).get(0);
        blocks = new Container(levelInfo.get(ID.LEVELBLOCKS));
        hazards = new Container(levelInfo.get(ID.LEVELHAZARDS));
        interactives = new Container(levelInfo.get(ID.LEVELINTERACTIVES));
        enemies = new Container(levelInfo.get(ID.LEVELENEMIES));
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
