package com.example.emil.Framework;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.emil.app.R;

import java.util.ArrayList;
import java.util.List;

import Game.Framework.GameDisplay;
import Game.Framework.GameLoop;
import Game.Framework.LevelCreator;
import Game.Framework.World;
import Game.GameObject;
import Game.Util.IDHandler;
import Game.Util.IDs;

public class GameActivity extends AppActivity {

    private World world;
    private Handler gameLoopThread;
    //private Handler levelCreatorThread;
    private LinearLayout ll;
    private GameLoop gameLoop;
    //private LevelCreator levelCreator;
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
        world = new World();
        gameLoop = new GameLoop(this, gameLoopThread);
        gameLoop.startLoop();

    }

    private void loadDrawables() {
        if (IDHandler.drawables[0] == null) { //makes sure images are only loaded once (during first gameActivity onCreate)
            IDHandler.drawables[IDs.DEFAULT.ordinal()] = getResources().getDrawable(R.drawable.startscreen); //default
            IDHandler.drawables[IDs.PLAYER.ordinal()] = getResources().getDrawable(R.drawable.player);
            IDHandler.drawables[IDs.BLOCK.ordinal()] = getResources().getDrawable(R.drawable.block1);
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
    protected void onPause() {
        super.onPause();
        gameLoop.interrupt();
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
