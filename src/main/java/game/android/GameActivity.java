package game.android;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.emil.app.R;

import java.util.ArrayList;
import java.util.HashMap;

import game.GameObject;
import game.draw.Background;
import game.framework.GameLoop;
import game.framework.LevelCreator;
import game.framework.World;
import game.movers.Player;
import game.objectinformation.ID;
import game.objectinformation.IDHandler;
import game.util.GameTime;

//NEW VERSION
public class GameActivity extends AppActivity implements SurfaceHolder.Callback {

    private World world;
    private Handler gameLoopThread;
    private SurfaceView surfaceView;
    private Player player;          //remove if possible when framework is finished
    Background bkg;
    GameLoop gameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);
        surfaceView.getHolder().addCallback(this);
        bkg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bkg_game));
        loadDrawables();
        setFullscreen();
        handlerSetup();


        HashMap<ID,ArrayList<GameObject>> levelInfo = LevelCreator.createLevel(this, getIntent().getExtras().getInt("level"));
        player = (Player)levelInfo.get(ID.LEVELPLAYER).get(0);
        gameThread = new GameLoop(this, gameLoopThread);
        world = new World(this, levelInfo);
        gameThread.start();
    }

    private void loadDrawables() {
        if (IDHandler.sheets[0] == null)  //makes sure images are only loaded once (during first gameActivity onCreate)
            IDHandler.initialize(this);
    }

    private void handlerSetup() {
        gameLoopThread = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message inputMessage) {
                surfaceChanged(surfaceView.getHolder(),0,0,0);
            }
        };
    }

    public boolean onTouchEvent(MotionEvent event) {
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p); //behvös denna?
        world.decodeTouchEvent(event, p);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameThread.interrupt();
    }

    public void nextLevel() {
        Log.d("nextLevel: ", "OK");
        gameThread.pause();
        Intent intent = new Intent (getApplicationContext(), ActivityHandler.class);
        intent.putExtra("ActivityConstant", ActivityConstants.LEVELCLEARED);
        intent.putExtra("level", getIntent().getExtras().getInt("level"));
        startActivity(intent);
        finish();
    }

    public void gameOver() {
        gameThread.pause();
        Intent intent = new Intent (getApplicationContext(), ActivityHandler.class);
        intent.putExtra("ActivityConstant", ActivityConstants.GAMEOVER);
        intent.putExtra("level", getIntent().getExtras().getInt("level"));
        startActivity(intent);
        finish();
    }

    public void updateWorld(GameTime gameTime) {
        //handle gameTime here? if syncing with clock --> send timeParam to world.update
        world.update(gameTime);
    }

    public void drawWorld(Canvas canvas, GameTime gameTime) {
        Rect r = player.getRect();
        centerPlayer(r.left, r.top, canvas);
        world.draw(canvas, gameTime);
    }
    private void centerPlayer(double x, double y, Canvas canvas) {
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
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        tryDraw(surfaceHolder);
        surfaceHolder.setFixedSize(World.WINDOW_WIDTH, World.WINDOW_HEIGHT);  //sätta storlek här
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        tryDraw(surfaceHolder);
    }

    private void tryDraw(SurfaceHolder surfaceHolder) {

        Canvas canvas = surfaceHolder.lockCanvas();      //OBS: kolla upp; ___lockHardwareCanvas()___
        if (canvas == null) {
            Log.e("", "Cannot draw onto the canvas as it's null");
        } else {
            GameTime gameTime = gameThread.getGameTime();
            bkg.draw(canvas, gameTime);
            drawWorld(canvas, gameTime);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}



// OLD VERSION
//public class GameActivity extends AppActivity {
//
//    private World world;
//    private Handler gameLoopThread;
//    private LinearLayout ll;
//    GameLoop gameThread;
//    private GameDisplay display;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//        ll = (LinearLayout) findViewById(R.id.gameActivity);
//        loadDrawables();
//        setFullscreen();
//        handlerSetup();
//        display = new GameDisplay(this, ll);
//
//        LevelCreator.createLevel(this, getIntent().getExtras().getInt("level"));
//        gameThread = new GameLoop(this, gameLoopThread);
//        world = new World(this);
//        gameThread.start();
//    }
//
//    private void loadDrawables() {
//        if (IDHandler.sheets[0] == null) { //makes sure images are only loaded once (during first gameActivity onCreate)
//            IDHandler.initialize(this);
//        }
//    }
//
//    private void handlerSetup() {
//        gameLoopThread = new Handler(Looper.getMainLooper()) {
//            public void handleMessage(Message inputMessage) {
//                ll.setBackground(new BitmapDrawable(getResources(), display.getBitmap()));
//            }
//        };
//    }
//
//    public boolean onTouchEvent(MotionEvent event) {
//        Point p = new Point();
//        getWindowManager().getDefaultDisplay().getSize(p); //behvös denna?
//        world.decodeTouchEvent(event, p);
//        return true;
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        gameThread.interrupt();
//    }
//
//    public void nextLevel() {
//        Log.d("nextLevel: ", "OK");
//        gameThread.pause();
//        Intent intent = new Intent (getApplicationContext(), ActivityHandler.class);
//        intent.putExtra("ActivityConstant", ActivityConstants.LEVELCLEARED);
//        intent.putExtra("level", getIntent().getExtras().getInt("level"));
//        startActivity(intent);
//        finish();
//    }
//
//    public void gameOver() {
//        gameThread.pause();
//        Intent intent = new Intent (getApplicationContext(), ActivityHandler.class);
//        intent.putExtra("ActivityConstant", ActivityConstants.GAMEOVER);
//        intent.putExtra("level", getIntent().getExtras().getInt("level"));
//        startActivity(intent);
//        finish();
//    }
//
//    public void updateWorld() {
//        //handle gameTime here? if syncing with clock --> send timeParam to world.update
//        world.update();
//    }
//
//    public void drawWorld() {
//        Rect r = LevelCreator.getPlayer().getRect();
//        display.beginDraw(new Point(r.left, r.top));
//        Canvas c = display.getCanvas();
//
//        world.draw(c);
//
//        display.endDraw();
//    }
//}
