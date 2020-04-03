package arkanoid.listeners;

import arkanoid.ball.Ball;
import arkanoid.collision.Block;
import arkanoid.game.Counter;
import arkanoid.game.GameLevel;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-05-2
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     * <p>
     * give values to the object fields.
     *
     * @param game         game.
     * @param removedBalls the num of balls in the game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * .
     * <p>
     * ball that are hitting this block need to be delete.
     * remove the ball from the game and update the ballsCounter.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the ball that hit it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.getRemainingBalls().decrease(1);
    }

    /**
     * Return RemainingBalls filed.
     *
     * @return the RemainingBalls.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }
}
