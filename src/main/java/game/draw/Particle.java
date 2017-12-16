package game.draw;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import game.movers.Mover;
import game.objectinformation.ID;
import game.objectinformation.Stats;
import game.util.GameTime;
import game.util.Vector;

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
        this(new Vector(0,0),0,0,0,0);  //default stuff!
    }


    public Particle(Vector v, float dx, float dy, int size, double life) {
        super(v);
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        color = new Paint();
        color.setColor(Color.RED);
    }



//    public void activate(Point p, ID id, int partType) {
//        this.moveTo(p.x, p.y);
//        //sprite = new ParticleSprite(id, partType);
//        sprite = new ParticleSprite(ID.BLOCK, partType);
//        rect.set(p.x, p.y, p.x + Stats.width(ID.BLOCK), p.y + Stats.height(ID.BLOCK));
//        life = 400;
//        //jump(500);
//        isActive = true;
//    }
    /**
     * Activates an inactive particle.
     * @param p The location where it will be activated
     * @param dx A speed factor along the x-axis.
     * @param dy A speed factor along the y-axis.
     * @param id The type of particle desired.
     * @param currentAngle the angular orientation of the sprite.
     */
    public void activate(Vector v, float dx, float dy, ID id, double currentAngle, int partType) {
        sprite = new ParticleSprite(id, partType, rect, v);
        this.dx = dx * Stats.particleSpeed(id);
        this.dy = dy * Stats.particleSpeed(id);
        this.life = Stats.particleLife(id);
        sprite.setAngle(currentAngle);
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

    /**
     * The particle will be inactivated.
     */
    public void reset() {
        life = 0;
        isActive = false;
    }


}


