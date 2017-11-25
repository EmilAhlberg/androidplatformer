package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import Game.Movers.Mover;
import Game.Stats;

/**
 * Created by Emil on 23/11/2017.
 */

public class Particle extends Mover {

    private float x;
    private float y;
    private float dx;
    private float dy;
    private int size;
    private int life;
    private Paint color;

    public Particle() {
        this(new Point(0,0),0,0,0,0);  //default stuff!
    }


    public Particle(Point p, float dx, float dy, int size, int life) {
        super(p);
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        color = new Paint();
        color.setColor(Color.RED);
    }

    public void activate(Point p, float dx, float dy, ID id) {
        this.moveTo(p.x, p.y);
        sprite = new NormalSprite(id);
        rect.set(p.x, p.y, p.x + Stats.width(id), p.y + Stats.height(id));
        this.dx = dx * Stats.particleSpeed(id);
        this.dy = dy * Stats.particleSpeed(id);
        this.life = Stats.particleLife(id);
        color = new Paint();
        color.setColor(Color.RED);
    }

    public void update(){
        move(dx, dy);
        life--;                     //make life time-based
    }

//    public void draw(Canvas c) {
//        c.drawCircle(x,  y, size, color); //probably needs update
//    }

    public boolean isFinished() {
        return life <= 0;
    }

    public void reset() {
        life = 0;
    }
}


