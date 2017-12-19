package game.movers;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import game.draw.AnimationInfo;
import game.objectinformation.ID;
import game.draw.Particles;
import game.framework.World;
import game.GameObject;
//import game.inanimates.Hazard;
import game.inanimates.Fire;
//import game.inanimates.Interactive;
import game.inanimates.Goal;
import game.util.GameTime;
import game.util.TouchEventDecoder;
import game.util.Vector;

/**
 * Created by Emil on 8/27/2017.
 */

public class Player extends Collider {

    private static final int WALLJUMP_LEFT = 1; //!!
    private static final int WALLJUMP_RIGHT = -1; //!!
    private static int wallJumpDirection = 0; //!
    private final Vector FORCE = new Vector(38, 270); //!!
    private final float WALLJUMP_FORCE = 300; //!!
    private final int WALLJUMP_FRAMES = 27; //!!
    private TouchEventDecoder ted;
    private Point clickPos;
    private int wallJumpCounter;

    public Player(Vector v) {
        super(v);
        ted = new TouchEventDecoder(new Point(0, 0), new Point(0, 0));
        wallJumpCounter = 0;
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        ted.decodeTouchEvent(event, p);
        clickPos = ted.getFirstClickPos();
    }

    @Override
    public void update(GameTime gameTime) {
        performAction();
        move(friction, true);
    }

    private void performAction() {
        updateWallJumpCounter();
        handleControls();
        wallJumpDirection = 0;
    }

    private void updateAnimationInfo(int fingers) {
        if (fingers == 0)
            animationType = AnimationInfo.DEFAULT;
        else {
            if (grounded) {
                animationType = AnimationInfo.RUNNING;
            } else {
                if (speed.x > 0) {
                    animationType = AnimationInfo.JUMPING_RIGHT;
                } else
                    animationType = AnimationInfo.JUMPING_LEFT;
            }
        }
    }

    private void handleControls() {
        int fingers = ted.getNbrFingersDown();
        updateAnimationInfo(fingers);
        updateDirection(fingers);
        checkJump(fingers);
    }

    private void checkJump(int fingers) {
        if (fingers > 1) {
            if (grounded) {
                jump(FORCE.y);
                Particles.createParticles(new Vector(rect.centerX(), rect.bottom), ID.JUMP);
                grounded = false;
            } else if (wallJumpDirection != 0) {
                wallJumpCounter = WALLJUMP_FRAMES;
                applyForce(WALLJUMP_FORCE * wallJumpDirection, (float) (-FORCE.y));
                wallJumpParticles();
                ted.switchPositions();
                clickPos = ted.getFirstClickPos();
            }
        }
    }

    private void wallJumpParticles() {
        ID id;
        Vector v;
        if (wallJumpDirection == WALLJUMP_LEFT) {
            id = ID.WALLJUMP_LEFT;
            v = new Vector(rect.left, rect.centerY());
        } else {
            id = ID.WALLJUMP_RIGHT;
            v = new Vector(rect.right, rect.centerY());
        }
        Particles.createParticles(v, id);
    }

    private void updateDirection(int fingers) {
        if (fingers != 0) {
            float temp = clickPos.x - World.WINDOW_WIDTH / 2;
            float force = FORCE.x * temp / Math.abs(temp);
            if (wallJumpCounter > 0) {
                if (force * speed.x > 0) {
                    applyForce(force, 0);
                }
            } else {
                applyForce(force, 0);
            }
        }
    }

    private void updateWallJumpCounter() {
        if (grounded || wallJumpDirection != 0) {
            wallJumpCounter = 0;
        } else if (wallJumpCounter > 0) {
            wallJumpCounter--;
        }
    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {
        if (g instanceof Fire) {
            if (collisionType == Collider.COLLISION_TOP) { //There is a small strip of "will_remove_block" at the bottom of the fire
                speed.y = 0;
                moveTo(rect.left, g.getRect().bottom);
            } else {
                isActive = false;
            }
        } else if (g instanceof Goal) {
            ((Goal) g).affectPlayer();
        } else if (g.getID() == ID.CAT) {
            Rect cRect = g.getRect();
            if (collisionType == Collider.COLLISION_BOTTOM) {
                Particles.createParticles(new Vector(cRect.left, cRect.top), ID.OBJECTDEATH, ID.CAT);
                Particles.createParticles(new Vector(cRect.centerX(), cRect.centerY()), ID.EXPLOSION);
                ((Cat) g).deactivateMover();
                jump(FORCE.y);
            } else
                isActive = false;
        } else if (g.getID() == ID.BLOCK) {
            if (collisionType == COLLISION_LEFT)
                wallJumpDirection = WALLJUMP_LEFT;
            else if (collisionType == COLLISION_RIGHT) {
                wallJumpDirection = WALLJUMP_RIGHT;
            }
        }
    }
}
