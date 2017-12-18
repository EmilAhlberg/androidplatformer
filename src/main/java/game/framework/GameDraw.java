package game.framework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Window;

import com.example.emil.app.R;

import game.android.GameActivity;
import game.draw.Background;
import game.movers.Player;
import game.util.GameTime;

/**
 * Created by Emil on 12/17/2017.
 */

public class GameDraw {

    private int height;
    private Background bkg;
    private Player player;
    private World world;
    private Bitmap bMap;
    private volatile Bitmap returnMap;
    private Canvas canvas, returnCanvas;

    public GameDraw(GameActivity ga, Player player, World world) {
        this.player = player;
        this.world = world;
        Bitmap temp = BitmapFactory.decodeResource(ga.getResources(), R.drawable.bkg_game);
        bkg = new Background(temp);
        bMap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.RGB_565);
        returnMap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.RGB_565);
        canvas = new Canvas(bMap);
        returnCanvas = new Canvas(returnMap);
        height = bMap.getHeight();
    }

    public Bitmap drawGame(GameTime gt) {
        bkg.draw(canvas, gt);
        drawWorld(canvas, gt);
        //returnCanvas.drawBitmap(bMap, -(0+Math.max(0, player.getRect().centerX() - 400)), (-1000+480+Math.max(0, (World.MAP_HEIGHT-player.getRect().centerY()) - 240)), null);
        returnCanvas.drawBitmap(bMap, getSrc(), new Rect(0, 0, World.WINDOW_WIDTH, World.WINDOW_HEIGHT), null);
        return returnMap;
    }

    private Rect getSrc() {
        Rect r = player.getRect();
        int x = r.centerX() - World.WINDOW_WIDTH / 2;
        int y = r.centerY() - World.WINDOW_HEIGHT / 2;
        int xt = (x < 0 ? 0 : (x > World.MAP_WIDTH - World.WINDOW_WIDTH ? World.MAP_WIDTH - World.WINDOW_WIDTH : x));
        int yt = (y > World.MAP_HEIGHT - World.WINDOW_HEIGHT ? World.MAP_HEIGHT - World.WINDOW_HEIGHT : (y < 0 ? 0 : y));
        return new Rect(xt, yt, World.WINDOW_WIDTH + xt, World.WINDOW_HEIGHT + yt);
    }

    private void drawWorld(Canvas canvas, GameTime gameTime) {
        Rect r = player.getRect();
        //centerPlayer(r.left, r.top, canvas);
        world.draw(canvas, gameTime);
    }

    /*private void centerPlayer(double x, double y, Canvas canvas) {
        Rect r = canvas.getClipBounds();
        double dx = calculateDx(r, x);
        double dy = calculateDy(r, y);

        //förhindrar 'flimmer' vid stillastående
        if (Math.abs(dx) > 2 || Math.abs(dy) > 2) {
            canvas.translate((float) dx, (float) dy);
        }
    }

    private double calculateDx(Rect r, double x) {
        double dx = 0;
        if (x >= World.WINDOW_WIDTH/2 && x <= World.MAP_WIDTH - World.WINDOW_WIDTH/2) {
            dx = r.centerX() - x;
        } else if (x <= World.WINDOW_WIDTH/2) {
            dx = r.left;
        } else if (x >=  World.MAP_WIDTH - World.WINDOW_WIDTH/2) {
            dx = r.right - World.MAP_WIDTH;
        }
        return dx;
    }

    private double calculateDy(Rect r, double y) {
        double dy = 0;
        if (y >= World.WINDOW_HEIGHT/2 && y <= World.MAP_HEIGHT - World.WINDOW_HEIGHT/2) {
            dy = r.centerY() - y;
        } else if (y < World.WINDOW_HEIGHT / 2) {
            dy = r.top;  //icke testad
        } else if (y >= World.MAP_HEIGHT - World.WINDOW_HEIGHT/2) {
            dy = r.bottom - World.MAP_HEIGHT;
        }
        return dy;
    }*/
}
