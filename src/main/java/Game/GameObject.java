package Game;

        import android.graphics.Canvas;
        import android.graphics.Rect;

        import Game.Util.IDHandler;
        import Game.Util.IDs;


/**
 * Created by Emil on 2016-11-20.
 */

public abstract class GameObject {

    protected Rect rect;
    private Picture picture;
    protected IDs id;

    public GameObject(Rect rect) {
        this.rect = rect;
        this.id = IDHandler.getID(this.getClass());
        picture = new Picture(id, rect);
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
     * Returns the hitbox/bounds of the object
     * @return Hitbox of the object
     */

    public abstract void update(); //this is required, but doesnt have to be implemented / called on blocks etc.

    public IDs getID() {
        return id;
    }



    public Rect getRect() {
        return rect;
    }

    /**
     * Draws the object on the canvas
     * @param c Canvas to be drawn on
     */
    public void draw(Canvas c) {
        picture.draw(c);
    }


}
