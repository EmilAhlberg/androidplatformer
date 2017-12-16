package game.draw;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import game.objectinformation.ID;
import game.util.GameTime;

/**
 * Created by Emil on 23/11/2017.
 */
public class Particles {
    private static int maxParticles = 100;
    private static ArrayList<Particle> activeParticles = new ArrayList<Particle>() {
        {
            for (int i = 0; i < maxParticles; i++) {
                add(new Particle());
            }
        }
    };

    /**
     * Updates active particles.
     */
    public static void update(GameTime gameTime) {
        for (Iterator<Particle> it = activeParticles.iterator(); it.hasNext();) {
            Particle p = it.next();
            if (p.isActive())
                p.update(gameTime);
        }
    }

    /**
     * Draws active particles.
     */
    public static void draw(Canvas canvas, GameTime gameTime) {
        for (Iterator<Particle> it = activeParticles.iterator(); it.hasNext();) {
            Particle p = it.next();
            if (p.isActive())
                p.draw(canvas, gameTime);
        }
    }

    /**
     * Creates particles new particles based on location and id.
     * @param p The location of the particles.
     * @param id The type of particle.
     */
    public static void createParticles(Point p, ID id) {
        float startAngle = 0;
        float endAngle = 0;
        int nbrOfParticles = 0;
        switch (id) {
            case JUMP:
                endAngle = (float)Math.PI;
                nbrOfParticles = 6;
                startAngle = (endAngle/nbrOfParticles)/2;
                circularParticles(p,id,nbrOfParticles,startAngle,endAngle);
                break;
            case EXPLOSION:;
                startAngle = 0;
                endAngle = 2*(float)Math.PI;
                nbrOfParticles = 20;
                circularParticles(p,id,nbrOfParticles,startAngle,endAngle);
                break;
            case OBJECTDEATH:
                int rnd = new Random().nextInt(4);
                if(rnd == 0)
                    objectDeathHorizontal(p, id);
                else if (rnd == 1)
                    objectDeathVertical(p, id);
                else
                    objectDeathx4(p,id);
                break;
        }
    }

    private static void circularParticles(Point point, ID id, int nbrOfParticles, float currentAngle, float endAngle) {
        int currentNbr = 0;
        double angle = (endAngle - currentAngle) / nbrOfParticles;
        for (int i = 0; i < maxParticles; i++) {
            Particle p = activeParticles.get(i);
            if (!p.isActive()) {
                float dx = (float) Math.cos(currentAngle);
                float dy = (float) Math.sin(currentAngle);
                p.activate(point, dx, dy, id, currentAngle, ParticleSprite.STANDARD);
                currentAngle += angle;
                currentNbr++;
            }
            if (currentNbr == nbrOfParticles)
                break;
        }
    }

    private static void objectDeathVertical(Point point, ID id) {
        int[] parts = new int[2];
        parts[0] = ParticleSprite.BOTTOM_PART_X2;
        parts[1] = ParticleSprite.TOP_PART_X2;
        double currentAngle = Math.PI/2;
        objectDeath(point, id, parts, currentAngle);
    }

    private static void objectDeathHorizontal(Point point, ID id) {
        int[] parts = new int[2];
        parts[0] = ParticleSprite.LEFT_PART_X2;
        parts[1] = ParticleSprite.RIGHT_PART_X2;
        double currentAngle = 0;
        objectDeath(point, id, parts, currentAngle);
    }

    private static void objectDeathx4(Point point, ID id) {
        int[] parts = new int[4];
        parts[0] = ParticleSprite.BOTTOMRIGHT_PART_X4;
        parts[1] = ParticleSprite.BOTTOMLEFT_PART_X4;
        parts[2] = ParticleSprite.TOPLEFT_PART_X4;
        parts[3] = ParticleSprite.TOPRIGHT_PART_X4;
        double currentAngle = Math.PI/4;
        objectDeath(point, id, parts, currentAngle);
    }

    private static void objectDeath(Point point, ID id, int[] parts, double currentAngle) {
        double angle = 2*Math.PI / parts.length;
        int counter = 0;
        for (int i = 0; i<maxParticles; i++) {
            Particle p = activeParticles.get(i);
            if (!p.isActive()) {
                float dx = (float) Math.cos(currentAngle);
                float dy = (float) Math.sin(currentAngle);
                p.activate(point, dx,dy,id, 0,parts[counter]);
                currentAngle += angle;
                counter++;
            }
            if (counter == parts.length)
                break;
        }
    }

    /**
     * Resets all particles, turning them inactive.
     */
    public static void reset() {
        for (Particle p: activeParticles) {
            p.reset();
        }
    }
}
