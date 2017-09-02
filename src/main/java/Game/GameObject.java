package Game;

        import android.graphics.Canvas;
        import android.graphics.Rect;
        import android.graphics.pdf.PdfDocument;
        import android.util.Log;

        import Game.Draw.AnimatedSprite;
        import Game.Draw.AnimationInfo;
        import Game.Draw.IDHandler;
        import Game.Draw.IDs;
        import Game.Draw.Sprite;
        import Game.Draw.StaticSprite;
        import Game.Movers.Player;


/**
 * Created by Emil on 2016-11-20.
 */

public abstract class GameObject {

    protected Rect rect;
    private Sprite sprite;
    protected IDs id;
    protected int animationType = AnimationInfo.DEFAULT;

    public GameObject(Rect rect) {
        this.rect = rect;
        this.id = IDHandler.getID(this.getClass());
        if (id.ordinal() >= IDs.STANDARDBLOCK.ordinal())
            sprite = new StaticSprite(id);
        else
            sprite = new AnimatedSprite(id);
    }

    /**
     * Moves the gameObject to the coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public void moveTo(double x, double y) {
        rect.offset((int)(x - rect.left), (int)(y - rect.top));
    }

    /**
     * Moves the gameObject a certain distance
     * @param x horizontal distance
     * @param y vertical distance
     */
    public void move(double x, double y) {
        rect.offset((int)x, (int)y);
    }

    public abstract void update(); //this is required, but doesn't have to be implemented / called on blocks etc.

    public IDs getID() {
        return id;
    }

    /**
     * Returns the hitbox/bounds of the object
     * @return Hitbox of the object
     */
    public Rect getRect() {
        return rect;
    }

    /**
     * Draws the object on the canvas
     * @param c Canvas to be drawn on
     */
    public void draw(Canvas c) {
        sprite.draw(c, rect, animationType);
    }
}
