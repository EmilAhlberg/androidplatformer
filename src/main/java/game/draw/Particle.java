package game.draw;



import game.movers.Mover;
import game.objectinformation.ID;
import game.objectinformation.Stats;
import game.util.GameTime;
import game.util.Vector;

/**
 * Created by Emil on 23/11/2017.
 */

public class Particle extends Mover {

    private static final int PARTICLE_FORCE = 10;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private int size;
    private double life;
    private boolean affectedByGravity;

    public Particle() {
        this(new Vector(0,0),0,0,0,0);  //default stuff!
    }


    public Particle(Vector v, float dx, float dy, int size, double life) {
        super(v);
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
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
     * @param v The location where it will be activated
     * @param dx A speed factor along the x-axis.
     * @param dy A speed factor along the y-axis.
     * @param id The type of particle desired.
     * @param currentAngle the angular orientation of the sprite.
     * @param partType used by "objectDeath".
     */
    public void activate(Vector v, float dx, float dy, ID id, double currentAngle, int partType) {
        sprite = new ParticleSprite(id, partType, rect, v);
        this.dx = dx;// * Stats.particleSpeed(id);
        this.dy = dy;// * Stats.particleSpeed(id);
        this.life = Stats.particleLife(id);
        affectedByGravity = Stats.affectedByGravity(id);
        sprite.setAngle(currentAngle);
        isActive = true;
    }

    /**
     * Activate overload, used by "objectDeath".
     * @param v The location where it will be activated
     * @param dx A speed factor along the x-axis.
     * @param dy A speed factor along the y-axis.
     * @param objectID The type of sprite used.
     * @param particleID The type of particle desired.
     * @param currentAngle the angular orientation of the sprite.
     * @param partType used by "objectDeath".
     */

    public void activate(Vector v, float dx, float dy, ID objectID, ID particleID, double currentAngle, int partType) {
        activate(v,dx,dy,objectID,currentAngle,partType);
        this.life = Stats.particleLife(particleID);
        affectedByGravity = Stats.affectedByGravity(particleID);
    }

    private void activateParticle() {

    }

    /**
     * Updates the particle.
     */
    public void update(GameTime gameTime){
        applyForce(dx * PARTICLE_FORCE, dy * PARTICLE_FORCE);
        move(affectedByGravity);
        life -= gameTime.elapsedTime();
        isActive = life >= 0;
        if (!isActive)
            reset();
    }

    /**
     * The particle will be inactivated.
     */
    public void reset() {
        life = 0;
        isActive = false;
        deactivateMover();
    }




}


