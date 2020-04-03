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
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(Color.blue);
        d.drawCircle(FRAME_WIDTH / 2, 190, 135);
        d.drawCircle(FRAME_WIDTH / 2, 190, 100);
        d.drawCircle(FRAME_WIDTH / 2, 190, 65);
        d.drawLine(FRAME_WIDTH / 2, 45, FRAME_WIDTH / 2, 345);
        d.drawLine(FRAME_WIDTH / 2 - 150, 190, FRAME_WIDTH / 2 + 150, 190);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
