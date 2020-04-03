package arkanoid.game;
import biuoop.DrawSurface;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the surface we want to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add to game.
     * @param game the game we want to add to.
     */
    void addToGame(GameLevel game);
}