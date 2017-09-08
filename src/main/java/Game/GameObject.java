package Game;

        import android.graphics.Canvas;
        import android.graphics.Point;
        import android.graphics.Rect;

        import Game.Draw.AnimatedSprite;
        import Game.Draw.AnimationInfo;
        import Game.Draw.IDHandler;
        import Game.Draw.IDs;
        import Game.Draw.Sprite;
        import Game.Draw.BigSprite;
        import Game.InAnimates.StandardBlock;


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
        sprite = new AnimatedSprite(id);
    }

    public GameObject(Point p, int nbrBlocks, boolean horizontal) {
        if (horizontal)
            this.rect = new Rect(p.x, p.y, p.x + StandardBlock.BLOCK_WIDTH*nbrBlocks, p.y+StandardBlock.BLOCK_HEIGHT);
        else
            this.rect = new Rect(p.x, p.y, p.x + StandardBlock.BLOCK_WIDTH, p.y+StandardBlock.BLOCK_HEIGHT*nbrBlocks);
        this.id = IDHandler.getID(this.getClass());
        sprite = new BigSprite(id,nbrBlocks, horizontal, rect);

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
