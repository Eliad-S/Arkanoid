package arkanoid.background;

import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class ColorBackground implements Sprite {
    private Color color;
    private int xpos;
    private int ypos;
    private int endX;
    private int endY;

    /**
     * constructor.
     *
     * @param color color type
     * @param xpos   the x value we want to draw from.
     * @param ypos   the y value we want to draw from.
     * @param endX   the x value we want to draw to.
     * @param endY   the y value we want to draw to.
     */
    public ColorBackground(Color color, int xpos, int ypos, int endX, int endY) {
        this.color = color;
        this.xpos = xpos;
        this.ypos = ypos;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(xpos, ypos, endX, endY);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
