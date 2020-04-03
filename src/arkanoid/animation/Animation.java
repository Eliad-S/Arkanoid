package arkanoid.animation;

import biuoop.DrawSurface;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public interface Animation {
    /**
     * The game-specific logic .
     *
     * @param d surface.
     */
     void doOneFrame(DrawSurface d);

    /**
     * stop condition of the game loop .
     * @return boolean true/false.
     */
     boolean shouldStop();
}