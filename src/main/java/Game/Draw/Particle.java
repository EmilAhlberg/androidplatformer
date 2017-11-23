package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Emil on 23/11/2017.
 */

public class Particle {

    private float x;
    private float y;
    private float dx;
    private float dy;
    private int size;
    private int life;
    private Paint color;


    public Particle(float x, float y, float dx, float dy, int size, int life) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        color = new Paint();
        color.setColor(Color.RED);
    }


    public boolean update(){
        x += dx;
        y += dy;
        life--;                     //make life time-based
        if(life <= 0)
            return true;
        return false;
    }

    public void draw(Canvas c){
        c.drawCircle(x,  y, size, color); //probably needs update
    }

    public boolean isFinished() {
        return life <= 0;
    }



//


}


