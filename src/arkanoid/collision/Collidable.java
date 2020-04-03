package arkanoid.collision;

import arkanoid.ball.Ball;
import arkanoid.ball.Velocity;
import arkanoid.graphics.Point;
import arkanoid.graphics.Rectangle;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the point the intersection occurs.
     * @param currentVelocity the current velocity.
     * @param hitter the ball that hit the collidable object.
     * @return the new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}