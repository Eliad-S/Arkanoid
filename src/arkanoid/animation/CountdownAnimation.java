package arkanoid.animation;
import arkanoid.game.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private int numOfSeconds;
    private int countFrom;
    private int currentNum;
    private boolean stop;
    private Sleeper sleeper;
    /**
     * constructor.
     * <p>
     * running the animation loop in accordance with the animation given.
     *
     * @param numOfSeconds the num of second we want to display the count down.
     * @param countFrom the number we want to count from.
     * @param gameScreen the sprite collection of the game.
     * */
    public CountdownAnimation(int numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.countFrom = countFrom;
        this.currentNum = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.sleeper = new biuoop.Sleeper();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (currentNum != countFrom) {
            this.sleeper.sleepFor((1000 * numOfSeconds) / (countFrom + 1));
        }
        this.drawOnGame(d);
        d.setColor(Color.white);

        if (currentNum == 0) {
            d.drawText(400, d.getHeight() / 2, "Go", 60);
        } else {
            if (this.currentNum == -1) {
                this.stop = true;
            } else {
                d.drawText(400, d.getHeight() / 2, Integer.toString(this.currentNum), 60);
            }
        }
        this.currentNum -= 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * draw the game.
     * @param d the surface.
     * */
    public void drawOnGame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
    }
}
