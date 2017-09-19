package Game.Movers;

import android.graphics.Point;

import Game.GameObject;

/**
 * Created by Emil on 19/09/2017.
 */

public class Cat extends Collider {


    public Cat(Point p) {
        super(p);
    }

    @Override
    public void update() {
        mh.updateSpeed(friction, grounded);
        move(mh.horizontalSpeed, mh.verticalSpeed);
    }

    @Override
    public void handleCollision(int collisionType, GameObject g) {

    }
}
