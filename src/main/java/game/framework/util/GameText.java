package game.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.example.emil.app.R;

import game.activities.GameActivity;

/**
 * Created by Emil on 2017-12-23.
 */

public class GameText {
    private final long displayTime = 3000;
    private final int frameConstant = 10;
    private Rect boxSource = new Rect(0,0,200,100);
    private Rect boxDest = new Rect();
    private long timeDisplayed = 0;
    private int textSize = 40;
    private int level;
    private Bitmap bmap;

    private String currentText;
    private Paint textPaint = new Paint();
    private Paint boxPaint = new Paint();

    public GameText(GameActivity ga) {
        this.level = ga.getIntent().getExtras().getInt("level");
        bmap = BitmapFactory.decodeResource(ga.getResources(), R.drawable.text_square);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(Typeface.create("Arial",Typeface.ITALIC));
        boxPaint.setAlpha(90);
        initText();
    }

    private void initText() {
        switch (level) {
            case 1:
                currentText = "A Hero's Call";
                break;
            case 2:
                currentText = "An old foe";
        }
    }

    public void draw(Canvas canvas, GameTime gameTime, Rect screenPosition) {
        if (timeDisplayed < displayTime && currentText != null) {
            timeDisplayed += gameTime.elapsedTime();
            int boxWidth = (int) textPaint.measureText(currentText)/2 + frameConstant;
            boxDest.set(screenPosition.centerX()-boxWidth, screenPosition.centerY()-70,
                    screenPosition.centerX()+boxWidth, screenPosition.centerY()+50);
            canvas.drawBitmap(bmap, boxSource, boxDest, boxPaint);
            textPaint.setColor(Color.BLACK);
            canvas.drawText(currentText, screenPosition.centerX(), screenPosition.centerY(), textPaint);
        }
    }


}
