package game.visuals;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import game.visuals.sprite.ParticleSprite;
import game.util.ID;
import game.framework.util.GameTime;
import game.util.Vector;

/**
 * Created by Emil on 23/11/2017.
 */
public class Particles {
    private static int maxParticles = 100;
    private static ArrayList<Particle> particleList = new ArrayList<Particle>() {
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
        for (Iterator<Particle> it = particleList.iterator(); it.hasNext(); ) {
            Particle p = it.next();
            if (p.isActive())
                p.update(gameTime);
        }
    }

    /**
     * Draws active particles.
     */
    public static void draw(Canvas canvas, GameTime gameTime) {
        for (Iterator<Particle> it = particleList.iterator(); it.hasNext(); ) {
            Particle p = it.next();
            if (p.isActive())
                p.draw(canvas, gameTime);
        }
    }

    /**
     * Overloaded particle creation method, passing default params.
     * @param v  The location of the particles.
     * @param particleID The type of particle.
     */
    public static void createParticles(Vector v, ID particleID) {
        createParticles(v, particleID, ID.DEFAULT);
    }

    /**
     * Creates particles new particles based on location and id.
     *
     * @param v  The location of the particles.
     * @param particleID The type of particle.
     * @param objectID the type of object which the particles are invoked upon.
     */
    public static void createParticles(Vector v, ID particleID, ID objectID) {
        float startAngle = 0;
        float endAngle = 0;
        int nbrOfParticles = 0;
        switch (particleID) {
            case JUMP:
                endAngle = (float) (3*Math.PI/4);
                nbrOfParticles = 3;
                startAngle = (float)Math.PI/4;
                circularParticles(v, particleID, nbrOfParticles, startAngle, endAngle);
                break;
            case WALLJUMP_LEFT:
                endAngle = (float) Math.PI * 5 /4;
                nbrOfParticles = 3;
                startAngle = (float) (3*Math.PI / 4);
                circularParticles(v, ID.JUMP, nbrOfParticles, startAngle, endAngle);
                break;
            case WALLJUMP_RIGHT:
                endAngle = (float) Math.PI * 9 / 4;
                nbrOfParticles = 3;
                startAngle = (float) Math.PI * 5 / 4;
                ;
                circularParticles(v, ID.JUMP, nbrOfParticles, startAngle, endAngle);
                break;
            case EXPLOSION:
                ;
                startAngle = 0;
                endAngle = 2 * (float) Math.PI;
                nbrOfParticles = 20;
                circularParticles(v, particleID, nbrOfParticles, startAngle, endAngle);
                break;
            case OBJECTDEATH:
                int rnd = new Random().nextInt(4);
                if (rnd == 0)
                    objectDeathHorizontal(v, objectID, particleID);
                else if (rnd == 1)
                    objectDeathVertical(v, objectID, particleID);
                else
                    objectDeathx4(v, objectID, particleID);
                break;
        }
    }

    /*
        Spreads particles evenly in the defined circle sector. NbrOfParticles should be > 1.
     */
    private static void circularParticles(Vector v, ID id, int nbrOfParticles, float currentAngle, float endAngle) {
        int currentNbr = 0;
        double angle = (endAngle - currentAngle) / (nbrOfParticles - 1);
        for (int i = 0; i < maxParticles; i++) {
            Particle p = particleList.get(i);
            if (!p.isActive()) {
                float dx = (float) Math.cos(currentAngle);
                float dy = (float) Math.sin(currentAngle);
                p.activate(v, dx, dy, id, currentAngle, ParticleSprite.STANDARD);
                currentAngle += angle;
                currentNbr++;
            }
            if (currentNbr == nbrOfParticles)
                break;
        }
    }

    private static void objectDeathVertical(Vector v, ID objectID, ID particleID) {
        int[] parts = new int[2];
        parts[0] = ParticleSprite.BOTTOM_PART_X2;
        parts[1] = ParticleSprite.TOP_PART_X2;
        double currentAngle = Math.PI / 2;
        objectDeath(v, objectID, particleID, parts, currentAngle);
    }

    private static void objectDeathHorizontal(Vector v, ID objectID, ID particleID) {
        int[] parts = new int[2];
        parts[0] = ParticleSprite.LEFT_PART_X2;
        parts[1] = ParticleSprite.RIGHT_PART_X2;
        double currentAngle = 0;
        objectDeath(v, objectID, particleID, parts, currentAngle);
    }

    private static void objectDeathx4(Vector v, ID objectID, ID particleID) {
        int[] parts = new int[4];
        parts[0] = ParticleSprite.BOTTOMRIGHT_PART_X4;
        parts[1] = ParticleSprite.BOTTOMLEFT_PART_X4;
        parts[2] = ParticleSprite.TOPLEFT_PART_X4;
        parts[3] = ParticleSprite.TOPRIGHT_PART_X4;
        double currentAngle = Math.PI / 4;
        objectDeath(v, objectID, particleID, parts, currentAngle);
    }

    private static void objectDeath(Vector v, ID objectID, ID particleID, int[] parts, double currentAngle) {
        double angle = 2 * Math.PI / parts.length;
        int counter = 0;
        for (int i = 0; i < maxParticles; i++) {
            Particle p = particleList.get(i);
            if (!p.isActive()) {
                float dx = (float) Math.cos(currentAngle);
                float dy = (float) Math.sin(currentAngle);
                p.activate(v, dx, dy, objectID,particleID, 0, parts[counter]);
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
        for (Particle p : particleList) {
            p.reset();
        }
    }
}
