package arkanoid.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * constructor.
     * <p>
     * give values to the object fields.
     *
     * @param gui             gui.
     * @param framesPerSecond num of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * constructor.
     * <p>
     * running the animation loop in accordance with the animation given.
     *
     * @param animation an animation type.
     */
    public void run(Animation animation)  {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}