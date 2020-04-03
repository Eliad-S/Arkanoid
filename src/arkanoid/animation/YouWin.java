package arkanoid.animation;

import arkanoid.game.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import static arkanoid.game.GameLevel.FRAME_HEIGHT;
import static arkanoid.game.GameLevel.FRAME_WIDTH;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class YouWin implements Animation {
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private Counter currentScore;

    /**
     * constructor.
     * <p>
     * running the animation loop in accordance with the animation given.
     *
     * @param keyboardSensor the num of second we want to display the count down.
     * @param currentScore   the sprite collection of the game.
     */
    public YouWin(KeyboardSensor keyboardSensor, Counter currentScore) {
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
        this.currentScore = currentScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.yellow);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(Color.red);
        d.fillRectangle(15, 15, FRAME_WIDTH - 30, FRAME_HEIGHT - 30);
        d.setColor(Color.green);
        d.fillRectangle(30, 30, FRAME_WIDTH - 60, FRAME_HEIGHT - 60);

        d.setColor(Color.lightGray);
        d.fillRectangle(45, 45, FRAME_WIDTH - 90, FRAME_HEIGHT - 90);

        d.setColor(Color.BLACK);
        d.drawRectangle(15, 15, FRAME_WIDTH - 30, FRAME_HEIGHT - 30);
        d.drawRectangle(30, 30, FRAME_WIDTH - 60, FRAME_HEIGHT - 60);
        d.drawRectangle(45, 45, FRAME_WIDTH - 90, FRAME_HEIGHT - 90);


        d.drawText(200, 200, "You Win!", 100);

        d.drawText(100, 400, "Your score is: " + (this.currentScore.getValue()), 65);


    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}