package Game;

        import android.graphics.Canvas;
        import android.graphics.Point;
        import android.graphics.Rect;

        import Game.Draw.NormalSprite;
        import Game.Draw.AnimationInfo;
        import Game.Draw.IDHandler;
        import Game.Draw.ID;
        import Game.Draw.Sprite;
        import Game.Draw.BigSprite;


/**
 * Created by Emil on 2016-11-20.
 */

public abstract class GameObject {

    protected Rect rect;
    protected Sprite sprite;
    protected ID id;
    protected int animationType = AnimationInfo.DEFAULT;

    public GameObject(Point p) {
        this.id = IDHandler.getID(this.getClass());
        this.rect = new Rect(p.x, p.y, p.x + Stats.width(id), p.y + Stats.height(id));
        sprite = new NormalSprite(id);
    }

    public GameObject(Point p, int currentSize, boolean horizontal) {
        this.id = IDHandler.getID(this.getClass());
        if (horizontal)
            this.rect = new Rect(p.x, p.y, p.x + Stats.width(id)*currentSize, p.y+ Stats.height(id));      //must be changed to minimum grid size (block) to make things even
        else
            this.rect = new Rect(p.x, p.y, p.x + Stats.width(id), p.y+ Stats.height(id)*currentSize);      //must be changed to minimum grid size (block) to make things even
        sprite = new BigSprite(id,currentSize, horizontal, rect);

    }

    /**
     * Moves the gameObject to the coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void moveTo(double x, double y) {
        rect.offset((int)(x - rect.left), (int)(y - rect.top));
    }

    /**
     * Moves the gameObject a certain distance
     * @param x horizontal distance
     * @param y vertical distance
     */
    public void move(double x, double y) {                              // typisk movermetod?
        rect.offset((int)x, (int)y);
    }

    public abstract void update(); //this is required, but doesn't have to be implemented / called on blocks etc.

    public ID getID() {
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
