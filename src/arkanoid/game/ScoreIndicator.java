package arkanoid.game;

import arkanoid.graphics.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class ScoreIndicator implements Sprite {
    private Rectangle blockScore;
    private Counter currentScore;

    /**
     * constructor.
     * <p>
     * Create a new rectangle for a blockScore with a location and width/height.
     * and a filed of counter for the score.
     * @param x      the x value of the point located in the upperLeft place of the blockScore.
     * @param y      the y value of the point located in the upperLeft place of the blockScore.
     * @param width  the width of the blockScore.
     * @param height the height of the blockScore.
     * @param currentScore currentScore.
     * */
    public ScoreIndicator(double x, double y, double width, double height, Counter currentScore) {
        this.blockScore = new Rectangle(x, y, width, height);
        this.currentScore = currentScore;

    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.getBlockScore().getUpperLeft().getX();
        int y = (int) this.getBlockScore().getUpperLeft().getY();
        int width = (int) this.getBlockScore().getWidth();
        int height = (int) this.getBlockScore().getHeight();
        int middleX = x + (width / 2);
        int middleY = height;
        d.setColor(Color.lightGray);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
        d.drawText(middleX - 50, 20, "Score: " + (currentScore.getValue()), 20);
}

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * @return the blockScore.
     */
    public Rectangle getBlockScore() {
        return this.blockScore;
    }
}
