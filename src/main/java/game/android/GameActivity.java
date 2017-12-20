package game.android;

import android.content.Intent;
import android.graphics.Bitmap;
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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import game.framework.GameLoop;
import game.framework.GameMonitor;
import game.framework.TouchEventHandler;
import game.framework.World;
import game.objectinformation.IDHandler;
import game.util.MotionEventInfo;

//NEW VERSION
public class GameActivity extends AppActivity implements SurfaceHolder.Callback {

    private Handler gameLoopHandler;
    private SurfaceView surfaceView;
    private Thread gameThread;
    private Thread touchEventThread; //needed as field for proper shutdown?
    private LinkedBlockingQueue<MotionEventInfo> blockingQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);
        surfaceView.getHolder().addCallback(this);
        loadDrawables();
        setFullscreen();
        handlerSetup();
        initThreads();
    }

    private void initThreads() {
        blockingQueue = new LinkedBlockingQueue<MotionEventInfo>();
        GameMonitor monitor = new GameMonitor(this, gameLoopHandler);
        gameThread = new Thread(new GameLoop(monitor));
        touchEventThread = new Thread(new TouchEventHandler(blockingQueue, monitor));
        gameThread.start();
        touchEventThread.start();
    }

    private void loadDrawables() {
        if (IDHandler.sheets[0] == null)  //makes sure images are only loaded once (during first gameActivity onCreate)
            IDHandler.initialize(this);
    }

    private void handlerSetup() {
        gameLoopHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message inputMessage) {
                if (inputMessage.what == 1) {
                    gameOver();
                } else if (inputMessage.what == 2) {
                    nextLevel();
                } else if (inputMessage.what == 0) {
                    tryDraw(surfaceView.getHolder(), (Bitmap) inputMessage.obj);
                }
            }
        };
    }

    public boolean onTouchEvent(MotionEvent event) {
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        blockingQueue.add(new MotionEventInfo(event, p));
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!gameThread.isInterrupted())
            gameThread.interrupt();
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handleAllMessages();
    }

    private void nextLevel() {
        gameThread.interrupt();
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent (getApplicationContext(), ActivityHandler.class);
        intent.putExtra("ActivityConstant", ActivityConstants.LEVELCLEARED);
        intent.putExtra("level", getIntent().getExtras().getInt("level"));
        startActivity(intent);
        handleAllMessages();
        finish();
    }

    private void gameOver() {
        gameThread.interrupt();
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent (getApplicationContext(), ActivityHandler.class);
        intent.putExtra("ActivityConstant", ActivityConstants.GAMEOVER);
        intent.putExtra("level", getIntent().getExtras().getInt("level"));
        startActivity(intent);
        handleAllMessages();
        finish();
    }

    private void handleAllMessages() {
        gameLoopHandler.removeMessages(0);
        gameLoopHandler.removeMessages(1);
        gameLoopHandler.removeMessages(2);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        tryDraw(surfaceHolder, Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565));
        surfaceHolder.setFixedSize(World.WINDOW_WIDTH, World.WINDOW_HEIGHT);  //sätta storlek här
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        tryDraw(surfaceHolder, Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565));
    }

    private void tryDraw(SurfaceHolder surfaceHolder, Bitmap currentFrame) {

        Canvas canvas = surfaceHolder.lockCanvas();      //OBS: kolla upp; ___lockHardwareCanvas()___
        if (canvas == null) {
            Log.e("", "Cannot draw onto the canvas as it's null");
        } else {
            canvas.drawBitmap(currentFrame, new Rect(0, 0, World.WINDOW_WIDTH, World.WINDOW_HEIGHT), new Rect(0, 0, World.WINDOW_WIDTH, World.WINDOW_HEIGHT), null);
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
//    private Handler gameLoopHandler;
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
//        gameThread = new GameLoop(this, gameLoopHandler);
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
//        gameLoopHandler = new Handler(Looper.getMainLooper()) {
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
//        //handle gameTime here? if syncing with clock --> send timeParam to world.nextGameCycle
//        world.nextGameCycle();
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
