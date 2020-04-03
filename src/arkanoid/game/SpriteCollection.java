package arkanoid.game;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor.
     * <p>
     * initialized the the sprite list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add a new sprite type to the list.
     *
     * @param s a sprite type.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * notify All that the Time Passed and making the next movement.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.getSprites().size(); i++) {
            this.getSprites().get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the surface we want to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * return the sprite list.
     *
     * @return the sprite list.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}