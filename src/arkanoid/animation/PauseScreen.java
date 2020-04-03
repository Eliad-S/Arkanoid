package arkanoid.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * <p>
     * initialize our filed.
     * @param k keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}