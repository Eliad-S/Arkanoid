package arkanoid.animation;

import arkanoid.highScore.HighScoresTable;
import arkanoid.highScore.ScoreInfo;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import static arkanoid.game.GameLevel.FRAME_HEIGHT;
import static arkanoid.game.GameLevel.FRAME_WIDTH;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    private boolean stop;
    private KeyboardSensor keyboardSensor;

    /**
     * constructor.

     * @param scores HighScoresTable type.
     * @param keyboardSensor keyboardSensor.
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor keyboardSensor) {
        this.highScoresTable = scores;
        this.stop = false;
        this.keyboardSensor = keyboardSensor;


    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0, 0, 128));
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(Color.lightGray);
        d.fillRectangle(15, 15, FRAME_WIDTH - 30, FRAME_HEIGHT - 30);

        d.setColor(Color.BLACK);
        d.drawRectangle(15, 15, FRAME_WIDTH - 30, FRAME_HEIGHT - 30);
        d.drawText(50, 58, "High Scores", 35);
        d.drawText(90, 128, "Player Name", 28);
        d.drawText(450, 128, "Score", 28);

        d.setColor(Color.yellow);
        d.drawText(50, 60, "High Scores", 35);

        d.setColor(Color.GREEN);
        d.drawText(90, 130, "Player Name", 28);
        d.drawText(450, 130, "Score", 28);
        d.fillRectangle(90, 140, 650, 3);
        d.setColor(Color.BLACK);
        d.drawRectangle(90, 140, 650, 3);

        d.setColor(Color.blue);
        d.drawText(197, 500, "Press space to continue", 30);
        d.setColor(new Color(0, 191, 255));
        d.drawText(200, 500, "Press space to continue", 30);
        d.setColor(Color.cyan);
        d.drawText(203, 500, "Press space to continue", 30);
//        d.setColor(new Color(0, 100, 0));
        ScoreInfo s = null;
        for (int i = 180, j = 0; j < highScoresTable.getHighScores().size(); i += 50, j++) {
            s = highScoresTable.getHighScores().get(j);
            d.setColor(new Color(255 - 102 - 0));
            d.drawText(90, i, s.getName(), 30);
            d.drawText(450, i, Integer.toString(s.getScore()), 30);
            d.setColor(Color.yellow);
            d.drawText(451, i, Integer.toString(s.getScore()), 30);
            d.drawText(91, i, s.getName(), 30);


        }


//
//        d.setColor(Color.orange);
//        d.drawText(140, 200, "adsf", 100);
//        d.drawText(140, 200, "sdfdfd", 100);


    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
