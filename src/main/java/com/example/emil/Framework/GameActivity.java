package com.example.emil.Framework;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.emil.app.R;

import Game.Framework.GameDisplay;
import Game.Framework.GameLoop;
import Game.Framework.LevelCreator;
import Game.Framework.World;
import Game.Draw.IDHandler;

public class GameActivity extends AppActivity {

    private World world;
    private Handler gameLoopThread;
    private LinearLayout ll;
    GameLoop gameThread;
    private GameDisplay display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ll = (LinearLayout) findViewById(R.id.gameActivity);
        loadDrawables();
        setFullscreen();
        handlerSetup();
        display = new GameDisplay(this, ll);

        LevelCreator.createLevel(this, getIntent().getExtras().getInt("level"));
        gameThread = new GameLoop(this, gameLoopThread);
        world = new World(this);
        gameThread.start();
    }

    private void loadDrawables() {
        if (IDHandler.bitmaps[0] == null) { //makes sure images are only loaded once (during first gameActivity onCreate)
            IDHandler.initialize(this);
        }
    }

    private void handlerSetup() {
        gameLoopThread = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message inputMessage) {
                ll.setBackground(new BitmapDrawable(getResources(), display.getBitmap()));
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

    public void updateWorld() {
        //handle gameTime here? if syncing with clock --> send timeParam to world.update
        world.update();
    }

    public void drawWorld() {
        Rect r = LevelCreator.getPlayer().getRect();
        display.beginDraw(new Point(r.left, r.top));
        Canvas c = display.getCanvas();

        world.draw(c);

        display.endDraw();
    }
}
