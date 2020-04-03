package arkanoid.background;

import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

import static arkanoid.background.SunshineBackground.GOLD;
import static arkanoid.background.SunshineBackground.KHAKI;
import static arkanoid.game.GameLevel.FRAME_HEIGHT;
import static arkanoid.game.GameLevel.FRAME_WIDTH;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class GreenBackground implements Sprite {
    public static final Color GREEN = new Color(0, 100, 0);
    public static final Color GREY = new Color(0, 0, 128);

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(GREEN);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(Color.black);
        d.fillRectangle(75, 450, 100, 300);
        d.setColor(Color.white);
        d.fillRectangle(85, 460, 80, 280);
        d.setColor(Color.BLACK);
        for (int i = 93; i <= 150; i += 18) {
            d.fillRectangle(i, 460, 10, 280);
        }
        for (int i = 482; i <= 600; i += 32) {
            d.fillRectangle(85, i, 80, 10);
        }
        d.fillRectangle(105, 380, 40, 70);
        d.fillRectangle(120, 230, 10, 150);
        d.setColor(KHAKI);
        d.fillCircle(125, 220, 20);
        d.setColor(GOLD);
        d.fillCircle(125, 220, 15);
        d.setColor(Color.yellow);
        d.fillCircle(125, 220, 7);


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
