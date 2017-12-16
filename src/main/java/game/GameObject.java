package game;

        import android.graphics.Canvas;
        import android.graphics.Point;
        import android.graphics.Rect;

        import game.draw.NormalSprite;
        import game.draw.AnimationInfo;
        import game.movers.Mover;
        import game.objectinformation.IDHandler;
        import game.objectinformation.ID;
        import game.draw.Sprite;
        import game.draw.BigSprite;
        import game.objectinformation.Stats;
        import game.util.GameTime;


/**
 * Created by Emil on 2016-11-20.
 */

public abstract class GameObject {

    protected Rect rect;
    protected Sprite sprite;
    protected ID id;
    protected int animationType = AnimationInfo.DEFAULT;
    protected boolean isActive;
    protected float angle;

    /**
     * Creates a gameObject, represented by a NormalSprite.
     * @param p The point where the object will be located.
     */
    public GameObject(Point p) {
        this.id = IDHandler.getID(this.getClass());
        this.rect = new Rect(p.x, p.y, p.x + Stats.width(id), p.y + Stats.height(id));
        sprite = new NormalSprite(id);
    }

    /**
     * Creates a gameObject, represented by a BigSprite.
     * @param p The point where the object will be located.
     * @param currentSize The size of the BigSprite
     * @param horizontal The orientation of the BigSprite. If horizontal is false, the orientation is vertical.
     */
    public GameObject(Point p, int currentSize, boolean horizontal) {
        this.id = IDHandler.getID(this.getClass());
        if (horizontal)
            this.rect = new Rect(p.x, p.y, p.x + Stats.width(id)*currentSize, p.y+ Stats.height(id));      //must be changed to minimum grid size (block) to make things even
        else
            this.rect = new Rect(p.x, p.y, p.x + Stats.width(id), p.y+ Stats.height(id)*currentSize);      //must be changed to minimum grid size (block) to make things even
        sprite = new BigSprite(id,currentSize, horizontal, rect);

    }

    public abstract void update(GameTime gameTime); //this is required, but doesn't have to be implemented / called on blocks etc.

    public ID getID() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns the hitbox/bounds of the object
     * @return Hitbox of the object
     */
    public Rect getRect() {
        return rect;
    }

    public void activate(double x, double y) {
        if (this instanceof Mover)
            ((Mover)this).moveTo(x, y);
        isActive = true;
    }

    /**
     * Draws the object on the canvas
     * @param c Canvas to be drawn on
     */
    public void draw(Canvas c, GameTime gameTime) {
        sprite.draw(c, gameTime, rect, animationType);
    }
}
