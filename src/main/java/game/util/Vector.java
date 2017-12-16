package game.util;

/**
 * Created by Emil on 16/12/2017.
 */

public class Vector {
    public float X;
    public float Y;

    public Vector(float x, float y) {
        this.X = x;
        this.Y = y;
    }

    public Vector() {
        X = 0;
        Y = 0;
    }

    public void set (Vector newVector) {
        X = newVector.X;
        Y = newVector.Y;
    }

    public void set (float x, float y) {
        this.X = x;
        this.Y = y;
    }

    public void addVector(Vector addedVector) {
        X += addedVector.X;
        Y += addedVector.Y;
    }
}
