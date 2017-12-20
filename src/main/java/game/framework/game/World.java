package game.framework.game;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import game.gameObjects.GameObject;

import game.gameObjects.Container;
import game.visuals.Particles;
import game.gameObjects.movers.Player;
import game.util.ID;
import game.framework.util.GameState;
import game.framework.util.GameTime;
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

    private Player player;
    private Container blocks;
    private Container hazards;
    private Container interactives;
    private Container enemies;
    private CollisionHandler ch;

    public World(HashMap<ID, ArrayList<GameObject>> levelInfo) {
        initWorld(levelInfo);
        ch = new CollisionHandler();
    }

    private void initWorld(HashMap<ID, ArrayList<GameObject>> levelInfo) {
        player = (Player) levelInfo.get(ID.LEVELPLAYER).get(0);
        blocks = new Container(levelInfo.get(ID.LEVELBLOCKS));
        hazards = new Container(levelInfo.get(ID.LEVELHAZARDS));
        interactives = new Container(levelInfo.get(ID.LEVELINTERACTIVES));
        enemies = new Container(levelInfo.get(ID.LEVELENEMIES));
    }

    public void update(GameTime gameTime, GameState gameState) {
        player.update(gameTime);
        enemies.update(gameTime);
        Particles.update(gameTime);
        ch.handleAllCollisions(player, blocks, hazards, interactives, enemies);
        checkGameState(gameState);
    }

    public Player getPlayer() {
        return player;
    }

    private void checkGameState(GameState gameState) {
        if (NEXT_LEVEL) {
            gameState.setGameState(GameState.NEXT_LEVEL_STATE);
            NEXT_LEVEL = false;
        }
        if (!player.isActive())
            gameState.setGameState(GameState.GAME_OVER_STATE);
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
