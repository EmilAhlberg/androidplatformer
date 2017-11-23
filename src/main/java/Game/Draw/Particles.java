package Game.Draw;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Emil on 23/11/2017.
 */

public class Particles {


    private static ArrayList<Particle> activeParticles = new ArrayList<Particle>();     //defragmented list probability!

    public static void update() {
        for (Iterator<Particle> it = activeParticles.iterator(); it.hasNext();) {
            Particle p = it.next();
            p.update();
            if (p.isFinished())
               it.remove();                                               //defragmented list probability!
        }
    }

    public static void draw(Canvas canvas) {
        for (Iterator<Particle> it = activeParticles.iterator(); it.hasNext();) {
            Particle p = it.next();
            p.draw(canvas);
        }
    }

    public static void createParticles(int x, int y) {
        int number = 10;
        double angle = 2* Math.PI / number;
        double currentAngle = -Math.PI;
        for (int i = 0; i<number; i++) {
            float dx = (float) Math.cos(currentAngle);
            float dy = (float) Math.sin(currentAngle);
            activeParticles.add(new Particle(x,y,dx,dy,5, 100));                //concurrency-fel pga av surface-view? när kallas draw i förhållande till update -> kan inte ske samtidigt!!
            currentAngle += angle;

        }
    }

//    private static float calculateDy(double currentAngle) {
//        return ;
//    }
//    private static float calculateDy(double currentAngle) {
//        return;
//    }
}
