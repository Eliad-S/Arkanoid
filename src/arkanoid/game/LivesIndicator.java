package arkanoid.game;

import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class LivesIndicator implements Sprite {
    private Counter livesreminders;
    /**
     * constructor.
     * <p>
     * @param livesreminders liveRemainders.
     */
    public LivesIndicator(Counter livesreminders) {
        this.livesreminders = livesreminders;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(50, 20, "Lives: " + (livesreminders.getValue()), 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    /**
     * set the lives counter to to number input.
     * @param num .
     */
    public void setLivesreminders(int num) {
        this.livesreminders.setCounter(num);
    }
}
