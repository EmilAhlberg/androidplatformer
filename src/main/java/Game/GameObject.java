package Game;

        import android.graphics.Canvas;
        import android.graphics.Rect;


/**
 * Created by Emil on 2016-11-20.
 */

public abstract class GameObject {

    protected Rect rect;
    private Picture picture;

    public GameObject(Rect rect) {
        this.rect = rect;
    }

    /**
     * Moves the gameObject to the coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public void move(double x, double y) {
        rect.offset((int)(x - rect.left), (int)(y - rect.top));
    }

    /**
     * Draws the object on the canvas
     * @param c Canvas to be drawn on
     */
    public void draw(Canvas c) {
        picture.draw(c);
    }
}
