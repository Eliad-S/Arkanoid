package arkanoid.levels;

import arkanoid.ball.Velocity;
import arkanoid.collision.Block;
import arkanoid.game.Sprite;

import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public interface LevelInformation {
    /**
     * return the num of balls that will be initialise in the game.
     * @return num of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list with velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * determined the speed of the paddle required.
     * @return the paddle speed.
     */
    int paddleSpeed();
    /**
     * determined the width of the paddle required.
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * return the name of the level.
     * @return the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return the background as a sprite object.
     */
    Sprite getBackground();
    /**
     * create the Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list with blocks of the level.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number is exactly blocks.size();
     * @return num of blocks in the game.
     */
    int numberOfBlocksToRemove();
}