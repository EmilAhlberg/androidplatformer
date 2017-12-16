package game.util;

/**
 * Created by Emil on 16/12/2017.
 */

public class Vector {
    public float x;
    public float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        x = 0;
        y = 0;
    }

    public void set (Vector newVector) {
        x = newVector.x;
        y = newVector.y;
    }

    public void set (float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void addVector(Vector addedVector) {
        x += addedVector.x;
        y += addedVector.y;
    }
}
