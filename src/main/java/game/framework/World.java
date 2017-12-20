package game.framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import game.util.Vector;

/**
 * Created by Emil on 8/27/2017.
 */

public class World {
    public static final Vector DEFAULT_POSITION = new Vector(-1000, -1000);
    public final static int MAP_WIDTH = 2000;
    public final static int MAP_HEIGHT = 1000;
    public final static int WINDOW_WIDTH = 1000;
    public final static int WINDOW_HEIGHT = 600;
    public static boolean NEXT_LEVEL = false;

    public static int GAME_STATE;
    public static final int RUNNING_STATE = 0;
    public static final int NEXT_LEVEL_STATE = 1;
    public static final int GAME_OVER_STATE = 2;

    private Player player;
    private Container blocks;
    private Container hazards;
    private Container interactives;
    private Container enemies;
    private CollisionHandler ch;

    public World(HashMap<ID, ArrayList<GameObject>> levelInfo) {
        initWorld(levelInfo);
        ch = new CollisionHandler();
        GAME_STATE = RUNNING_STATE;
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
        checkGameState();
    }

    public Player getPlayer() {
        return player;
    }

    private void checkGameState() {
        if (NEXT_LEVEL) {
            GAME_STATE = NEXT_LEVEL_STATE;
            NEXT_LEVEL = false;
        }
        if (!player.isActive())
            GAME_STATE = GAME_OVER_STATE;
    }

    public void draw(Canvas canvas, GameTime gameTime) {
        player.draw(canvas, gameTime);
        enemies.draw(canvas, gameTime, player.getRect() ); //rect param redundant atm
        blocks.draw(canvas, gameTime, player.getRect());
        hazards.draw(canvas, gameTime, player.getRect());
        interactives.draw(canvas, gameTime, player.getRect());
        Particles.draw(canvas, gameTime);
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        player.decodeTouchEvent(event, p);
    }
}
