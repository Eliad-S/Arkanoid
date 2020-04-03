package arkanoid.levels;

import arkanoid.collision.Block;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xPos the upperLeft x position of the block.
     * @param yPos the upperLeft y position of the block.
     * @return the block.
     */
    Block create(int xPos, int yPos);
}
