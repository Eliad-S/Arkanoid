package arkanoid.listeners;

import arkanoid.ball.Ball;
import arkanoid.collision.Block;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-05-2
 * This method is called whenever the beingHit object is hit.
 * The hitter parameter is the Ball that's doing the hitting.
 */
public interface HitListener {

    /**
     * .
     * <p>
     * the method get the block that's being hit and the ball that
     * hit it, and make an individual action about that.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the ball that hit it.
     */
    void hitEvent(Block beingHit, Ball hitter);
}