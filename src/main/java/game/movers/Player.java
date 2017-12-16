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

/**
 * Created by Emil on 8/27/2017.
 */

public class Player extends Collider {

    private final int X_FORCE = 60;
    private final int Y_FORCE = 350;
    private final double WALLJUMP_FORCE = 400;
    private final int WALLJUMP_FRAMES = 27;
    private TouchEventDecoder ted;
    private Point clickPos;
    private World world;
    private int wallJumpCounter;

    public Player(Point p) {
        super(p);
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
        move(horizontalSpeed, verticalSpeed);
    }

    private void performAction() {
        if (grounded || wallJumpDirection != 0) {
            wallJumpCounter = 0;
        } else if (wallJumpCounter > 0) {
            wallJumpCounter--;
        }
        int fingers = ted.getNbrFingersDown();
        if (fingers == 0) {
            animationType = AnimationInfo.DEFAULT;
        } else {
            double temp = clickPos.x - World.WINDOW_WIDTH/2;
            double force = X_FORCE * temp / Math.abs(temp);
            if (wallJumpCounter > 0) {
                if (force * horizontalSpeed > 0) {
                    applyForce(force, 0);
                }
            } else {
                applyForce(force, 0);
            }
            if (grounded) {
                animationType = AnimationInfo.RUNNING;
            } else {
                if (horizontalSpeed > 0) {
                    animationType = AnimationInfo.JUMPING_RIGHT;
                }
                else
                    animationType = AnimationInfo.JUMPING_LEFT;
            }
        }
        if (fingers > 1) {
            if (grounded) {
                jump(Y_FORCE);
                Particles.createParticles(new Point(this.rect.centerX(), this.rect.centerY()), ID.JUMP);     //!!!!!!!!!!!!!!!!!!!!!
                grounded = false;
            } else if (wallJumpDirection != 0) {
                wallJumpCounter = WALLJUMP_FRAMES;
                applyForce(WALLJUMP_FORCE * wallJumpDirection, -Y_FORCE * 2);
                Particles.createParticles(new Point(this.rect.centerX(), this.rect.centerY()), ID.JUMP);      //!!!!!!!!!!!!!!!!!!!!!
                ted.switchPositions();
                clickPos = ted.getFirstClickPos();
            }
        }
        wallJumpDirection = 0;
    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {
        if (g instanceof Fire) {
            if (collisionType == Collider.COLLISION_TOP) { //There is a small strip of "will_remove_block" at the bottom of the fire
                verticalSpeed = 0;
                moveTo(rect.left, g.getRect().bottom);
            } else {
                Particles.createParticles(new Point(this.rect.centerX(), this.rect.centerY()),ID.EXPLOSION);
                world.gameOver(); //Possible to change to lose lives or something instead of dying outright
            }
        } else if (g instanceof Goal) {
            ((Goal) g).affectPlayer(world);
        } else if (g.getID() == ID.CAT) {
            Rect cRect = g.getRect();
            if (collisionType == Collider.COLLISION_BOTTOM) {
                Particles.createParticles(new Point(cRect.left, cRect.top), ID.OBJECTDEATH);
                Particles.createParticles(new Point(cRect.centerX(), cRect.centerY()), ID.EXPLOSION);
                ((Cat) g).deactivate();
            }
            else
                world.gameOver();
        }
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
