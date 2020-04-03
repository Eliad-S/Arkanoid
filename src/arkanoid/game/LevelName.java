package arkanoid.game;

import arkanoid.levels.LevelInformation;
import biuoop.DrawSurface;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class LevelName implements Sprite {
    private LevelInformation levelInformation;
    /**
     * constructor.
     * <p>
     * give values to the object fields.
     *
     * @param levelInformation LevelInformation.
     */
    public LevelName(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(485, 20, "Level Name: " + this.levelInformation.levelName(), 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
