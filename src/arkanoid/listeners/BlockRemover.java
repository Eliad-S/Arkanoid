package arkanoid.listeners;

import arkanoid.ball.Ball;
import arkanoid.collision.Block;
import arkanoid.game.Counter;
import arkanoid.game.GameLevel;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-05-2
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * <p>
     * give values to the object fields.
     *
     * @param game          game.
     * @param removedBlocks the num of blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * .
     * <p>
     * Blocks that are hit and reach 0 hit-points will be removed
     * from the game as well as the listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the ball that hit it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getTargetHit() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.getHitListeners().remove(this);
            this.getRemainingBlocks().decrease(1);
        }
    }

    /**
     * Return game filed.
     *
     * @return the game.
     */
    public GameLevel getGame() {
        return this.game;
    }

    /**
     * Return RemainingBlocks filed.
     *
     * @return the RemainingBlocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
}