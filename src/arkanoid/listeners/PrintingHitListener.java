package arkanoid.listeners;

import arkanoid.ball.Ball;
import arkanoid.collision.Block;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-05-2
 */
public class PrintingHitListener implements HitListener {
    /**.
     * the method get the block that's being hit and the ball that
     * hit it, print a massage about them.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the ball that hit it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}
