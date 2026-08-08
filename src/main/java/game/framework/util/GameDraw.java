package game.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.emil.app.R;

import game.activities.GameActivity;
import game.visuals.Background;
import game.framework.game.World;
import game.gameObjects.movers.Player;

/**
 * Created by Emil on 12/17/2017.
 */

public class GameDraw {

    private int height;
    private Background bkg;
    private Bitmap bMap;
    private volatile Bitmap returnMap;
    private Canvas canvas, returnCanvas;
    private Rect destination = new Rect(0, 0, World.WINDOW_WIDTH, World.WINDOW_HEIGHT);
    private GameText gameText;

    public GameDraw(GameActivity ga) {
        Bitmap temp = BitmapFactory.decodeResource(ga.getResources(), R.drawable.bkg_game);
        bkg = new Background(temp);
        bMap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.RGB_565);
        gameText = new GameText(ga);
        returnMap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.RGB_565);
        canvas = new Canvas(bMap);
        returnCanvas = new Canvas(returnMap);
        height = bMap.getHeight();
    }

    public Bitmap drawGame(GameTime gt, World world) {
        bkg.draw(canvas, destination, gt);
        world.draw(canvas, gt);
        gameText.draw(canvas, gt, getSrc(world.getPlayer()));
        returnCanvas.drawBitmap(bMap, getSrc(world.getPlayer()), destination, null);
        return returnMap;
    }

    private Rect getSrc(Player player) {
        Rect r = player.getRect();
        int x = r.centerX() - World.WINDOW_WIDTH / 2;
        int y = r.centerY() - World.WINDOW_HEIGHT / 2;
        int xt = (x < 0 ? 0 : (x > World.MAP_WIDTH - World.WINDOW_WIDTH ? World.MAP_WIDTH - World.WINDOW_WIDTH : x));
        int yt = (y > World.MAP_HEIGHT - World.WINDOW_HEIGHT ? World.MAP_HEIGHT - World.WINDOW_HEIGHT : (y < 0 ? 0 : y));
        return new Rect(xt, yt, World.WINDOW_WIDTH + xt, World.WINDOW_HEIGHT + yt);
    }

}
