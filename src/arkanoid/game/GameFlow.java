package arkanoid.game;

import arkanoid.animation.Animation;
import arkanoid.animation.AnimationRunner;
import arkanoid.animation.GameOver;
import arkanoid.animation.YouWin;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.highScore.HighScoresTable;
import arkanoid.highScore.ScoreInfo;
import arkanoid.levels.LevelInformation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Animation highScoresAnimation;
    private GUI gui;
    private Counter livesReminder;
    private Counter currentScore;
    private HighScoresTable highScoresTable;

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     *
     * @param ar                  AnimationRunner.
     * @param gui                 Gui object.
     * @param ks                  KeyboardSensor.
     * @param highScoresAnimation .
     * @param highScoresTable     HighScoresTable.
     */
    public GameFlow(AnimationRunner ar, GUI gui, KeyboardSensor ks, HighScoresTable highScoresTable,
                    Animation highScoresAnimation) {
        this.animationRunner = ar;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.livesReminder = new Counter();
        this.currentScore = new Counter();
        this.highScoresTable = highScoresTable;
    }

    /**
     * initialize the levels given and run them one by one.
     * run an End screen animation and close the gui object.
     *
     * @param levels an array list that contain levels information.
     */
    public void runLevels(List<LevelInformation> levels) {
        this.livesReminder.setCounter(7);
        this.currentScore.setCounter(0);

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.livesReminder, this.currentScore);

            level.initialize();
            if (level.getRemainingBlocksCounter().getValue() == 0) {
                return;
            }

            while (level.getLivesReminders().getValue() != 0 && level.getRemainingBlocksCounter().getValue() != 0) {
                level.playOneTurn();
                if (level.getRemainingBallsCounter().getValue() == 0) {
                    this.livesReminder.decrease(1);
                }
            }

            if (level.getLivesReminders().getValue() == 0) {
                break;
            }

        }
        Animation endScreen;
        if (livesReminder.getValue() == 0) {
            endScreen = new GameOver(this.keyboardSensor, this.currentScore);

        } else {
            endScreen = new YouWin(this.keyboardSensor, this.currentScore);
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, endScreen));

        if (highScoresTable.getHighScores().size() < highScoresTable.size()
                || currentScore.getValue()
                > highScoresTable.getHighScores().get(highScoresTable.size() - 1).getScore()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo newScore = new ScoreInfo(name, currentScore.getValue());
            highScoresTable.add(newScore);
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(
                keyboardSensor, KeyboardSensor.SPACE_KEY, highScoresAnimation));
    }
}