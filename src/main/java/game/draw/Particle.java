package game.draw;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import game.movers.Mover;
import game.objectinformation.ID;
import game.objectinformation.Stats;
import game.util.GameTime;

/**
 * Created by Emil on 23/11/2017.
 */

public class Particle extends Mover {

    private float x;
    private float y;
    private float dx;
    private float dy;
    private int size;
    private double life;
    private Paint color;

    public Particle() {
        this(new Point(0,0),0,0,0,0);  //default stuff!
    }


    public Particle(Point p, float dx, float dy, int size, double life) {
        super(p);
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        color = new Paint();
        color.setColor(Color.RED);
    }

    /**
     * Activates an inactive particle.
     * @param p The location where it will be activated
     * @param dx A speed factor along the x-axis.
     * @param dy A speed factor along the y-axis.
     * @param id The type of particle desired.
     * @param currentAngle the angular orientation of the sprite.
     */

    public void activate(Point p, float dx, float dy, ID id, double currentAngle) {
        this.moveTo(p.x, p.y);
        sprite = new NormalSprite(id);
        rect.set(p.x, p.y, p.x + Stats.width(id), p.y + Stats.height(id));
        this.dx = dx * Stats.particleSpeed(id);
        this.dy = dy * Stats.particleSpeed(id);
        this.life = Stats.particleLife(id);
        sprite.setAngle(currentAngle);
        color = new Paint();
        color.setColor(Color.RED);
        isActive = true;
    }

    /**
     * Updates the particle.
     */
    public void update(GameTime gameTime){
        move(dx, dy);
        life -= gameTime.elapsedTime();
        isActive = life >= 0;
    }

//    public void draw(Canvas c) {
//        c.drawCircle(x,  y, size, color); //probably needs update
//    }

//    /**
//     * Check whether the particle is active or not.
//     * @return True if active, false otherwise.
//     */
//    public boolean isActive() {
//        return life >= 0;
//    }

    /**
     * The particle will be inactivated.
     */
    public void reset() {
        life = 0;
    }
}


