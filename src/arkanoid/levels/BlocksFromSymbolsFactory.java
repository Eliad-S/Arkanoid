package arkanoid.levels;

import arkanoid.collision.Block;

import java.util.Map;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     *
     * @param spacerWidths  map that contain space names as keys and there length of space as value.
     * @param blockCreators map that contain blockCreator names as keys and blockCreator as a value.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s .
     * @return true/false.
     */
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s .
     * @return true/false.
     */
    public boolean isBlockSymbol(String s) {
        if (blockCreators.containsKey(s)) {
            return true;
        }
        return false;
    }
    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xPos, yPos).
     *
     * @param s .
     * @param xPos .
     * @param yPos .
     * @return a new block.
     */
    public Block getBlock(String s, int xPos, int yPos) {
        return blockCreators.get(s).create(xPos, yPos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s .
     * @return space width.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);

    }
}
