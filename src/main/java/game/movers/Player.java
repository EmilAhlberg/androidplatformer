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

    private final Vector FORCE = new Vector (60,350); //!!
//    private final int X_FORCE = 60;
//    private final int Y_FORCE = 350;
    private final float WALLJUMP_FORCE = 400; //!!
    private final int WALLJUMP_FRAMES = 27; //!!
    private TouchEventDecoder ted;
    private Point clickPos;
    private World world;
    private int wallJumpCounter;

    public Player(Vector v) {
        super(v);
        ted = new TouchEventDecoder(new Point(0,0), new Point(0, 0));
        wallJumpCounter = 0;
    }

    public void decodeTouchEvent(MotionEvent event, Point p) {
        ted.decodeTouchEvent(event, p);
        clickPos = ted.getFirstClickPos();
    }

    @Override
    public void update(GameTime gameTime) {
        performAction();
        updateSpeed(friction, grounded);
        move();
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
                if (speed.X > 0) {
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
                jump(FORCE.Y);
                Particles.createParticles(new Vector(this.rect.centerX(), this.rect.centerY()), ID.JUMP);
                grounded = false;
            } else if (wallJumpDirection != 0) {
                wallJumpCounter = WALLJUMP_FRAMES;
                applyForce(WALLJUMP_FORCE * wallJumpDirection, -FORCE.Y * 2);
                Particles.createParticles(new Vector(this.rect.centerX(), this.rect.centerY()), ID.JUMP);
                ted.switchPositions();
                clickPos = ted.getFirstClickPos();
            }
        }
    }

    private void updateDirection(int fingers) {
        if (fingers != 0) {
            float temp = clickPos.x - World.WINDOW_WIDTH / 2;
            float force = FORCE.X* temp / Math.abs(temp);
            if (wallJumpCounter > 0) {
                if (force * speed.X > 0) {
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
                speed.Y = 0;
                moveTo(rect.left, g.getRect().bottom);
            } else {
                Particles.createParticles(new Vector(this.rect.centerX(), this.rect.centerY()),ID.EXPLOSION);
                world.gameOver(); //Possible to change to lose lives or something instead of dying outright
            }
        } else if (g instanceof Goal) {
            ((Goal) g).affectPlayer(world);
        } else if (g.getID() == ID.CAT) {
            Rect cRect = g.getRect();
            if (collisionType == Collider.COLLISION_BOTTOM) {
                Particles.createParticles(new Vector(cRect.left, cRect.top), ID.OBJECTDEATH);
                Particles.createParticles(new Vector(cRect.centerX(), cRect.centerY()), ID.EXPLOSION);
                ((Cat) g).deactivateMover();
            }
            else
                world.gameOver();
        }
    }

    //should be removed, better put public static variables in world, which can be set from here?
    //example: World.PLAYER_ALIVE = false; --> world checks this every loop, World.nextLevel = true; etc.
    public void setWorld(World world) {
        this.world = world;
    }
}
