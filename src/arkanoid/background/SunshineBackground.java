package arkanoid.background;

import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

import static arkanoid.game.GameLevel.FRAME_HEIGHT;
import static arkanoid.game.GameLevel.FRAME_WIDTH;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class SunshineBackground implements Sprite {
    public static final Color KHAKI = new Color(240, 230, 140);
    public static final Color GOLD = new Color(255, 215, 0);


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(GOLD);
        for (int i = 1; i <= 700; i += 5) {
            d.drawLine(150, 110, i, 290);
        }

        d.setColor(KHAKI);
        d.fillCircle(150, 110, 60);
        d.setColor(GOLD);
        d.fillCircle(150, 110, 50);
        d.setColor(Color.yellow);
        d.fillCircle(150, 110, 40);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
