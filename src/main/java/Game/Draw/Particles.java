package Game.Draw;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Iterator;

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

    public static void update() {
        for (Iterator<Particle> it = activeParticles.iterator(); it.hasNext();) {
            Particle p = it.next();
            if (!p.isFinished())
                p.update();
        }
    }

    public static void draw(Canvas canvas) {
        for (Iterator<Particle> it = activeParticles.iterator(); it.hasNext();) {
            Particle p = it.next();
            if (!p.isFinished())
                p.draw(canvas);
        }
    }

    public static void createParticles(Point p, ID id) {
        switch (id) {
            case JUMP:
                createJump(p,id);
                break;
            case EXPLOSION:;
                createExplosion(p,id);
        }
    }

    private static void createExplosion(Point point, ID id) {
        int nbrOfParticles = 10;
        int currentNbr = 0;
        double angle = 2* Math.PI / nbrOfParticles;
        double currentAngle = -Math.PI;
        for (int i = 0; i<maxParticles; i++) {
            Particle p = activeParticles.get(i);
            if (p.isFinished()) {
                float dx = (float) Math.cos(currentAngle);
                float dy = (float) Math.sin(currentAngle);
                p.activate(point,dx,dy,id);
                currentAngle += angle;
                currentNbr++;
            }
            if (currentNbr == nbrOfParticles)
                break;
        }
    }

    private static void createJump(Point point, ID id) {
        int nbrOfParticles = 6;
        int currentNbr = 0;
        double angle = Math.PI / nbrOfParticles;
        double currentAngle = angle/2;
        for (int i = 0; i<maxParticles; i++) {
            Particle p = activeParticles.get(i);
            if (p.isFinished()) {
                float dx = (float) Math.cos(currentAngle);
                float dy = (float) Math.sin(currentAngle);
                p.activate(point, dx,dy,id);
                currentAngle += angle;
                currentNbr++;
            }
            if (currentNbr == nbrOfParticles)
                break;
        }
    }

    public static void reset() {
        for (Particle p: activeParticles) {
            p.reset();
        }
    }
}
