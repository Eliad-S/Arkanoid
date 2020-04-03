package arkanoid.listeners;

import arkanoid.ball.Ball;
import arkanoid.collision.Block;
import arkanoid.game.Counter;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     * @param scoreCounter .
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * .
     * the method get the block that's being hit and the ball that
     * hit it, int increase the currentScore depend of the targetHit of the block
     *
     * @param beingHit the block that being hit.
     * @param hitter   the ball that hit it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getTargetHit() == 0) {
            this.getCurrentScore().increase(10);
        }

        this.getCurrentScore().increase(5);
    }

    /**
     * .
     * return the currentScore
     *
     * @return the counter of the current score.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
}