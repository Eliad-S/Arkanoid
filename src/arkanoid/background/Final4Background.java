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
public class Final4Background implements Sprite {
    public static final Color DODGER_BLUE = new Color(0, 191, 255);
    public static final Color SILVER = new Color(192, 192, 192);
    public static final Color DARK_GREY = new Color(169, 169, 169);
    public static final Color LIGHT_GREY = new Color(220, 220, 220);
    public static final Color WHITE_SMOKE = new Color(245, 245, 245);


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(DODGER_BLUE);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        // cloud 1.
        d.setColor(Color.white);
        for (int i = 540; i <= 680; i += 10) {
            d.drawLine(i, 440, i - 90, 600);
        }
        d.setColor(WHITE_SMOKE);
        d.fillCircle(540, 452, 27);
        d.setColor(LIGHT_GREY);
        d.fillCircle(565, 430, 25);
        d.fillCircle(580, 470, 30);
        d.setColor(SILVER);
        d.fillCircle(610, 435, 32);
        d.setColor(DARK_GREY);
        d.fillCircle(650, 450, 35);
        d.fillCircle(615, 475, 25);
        //cloud 2.
        d.setColor(Color.white);
        for (int i = 100; i <= 240; i += 10) {
            d.drawLine(i, 345, i - 100, 600);
        }
        d.setColor(WHITE_SMOKE);
        d.fillCircle(100, 362, 27);
        d.setColor(LIGHT_GREY);
        d.fillCircle(125, 340, 25);
        d.fillCircle(140, 380, 30);
        d.setColor(SILVER);
        d.fillCircle(170, 345, 32);
        d.setColor(DARK_GREY);
        d.fillCircle(210, 360, 35);
        d.fillCircle(175, 385, 25);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
