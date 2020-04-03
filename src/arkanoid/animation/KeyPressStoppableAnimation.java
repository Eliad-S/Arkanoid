package arkanoid.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;
    /**
     * constructor.
     * <p>
     * initialize our filed.
     * @param  sensor Keyboard.
     * @param key the string that will stop tje animation.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (isAlreadyPressed && !keyboardSensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }

        if (!isAlreadyPressed && keyboardSensor.isPressed(key)) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}