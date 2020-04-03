package arkanoid.game;

import arkanoid.animation.Animation;
import arkanoid.animation.AnimationRunner;
import arkanoid.animation.HighScoresAnimation;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.highScore.HighScoresTable;
import arkanoid.levels.ReadLevels;
import arkanoid.openingMenu.Menu;
import arkanoid.openingMenu.MenuAnimation;
import arkanoid.openingMenu.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.LineNumberReader;
import java.util.List;

import static arkanoid.game.Ass7Game.FRAME_HEIGHT;
import static arkanoid.game.GameLevel.FRAME_WIDTH;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class RunGame {
    /**
     * run the game.
     *
     * @param inputStream .
     */
    public static void run(InputStream inputStream) {
        File scoreFile = null;
        //load scores and create file if necessary.
        try {
            scoreFile = new File("highscores.txt");
            scoreFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException ex) {
            System.err.println("Failed reading file");
            ex.printStackTrace(System.err);
        }
        HighScoresTable highScoresTable = new HighScoresTable(4);
        highScoresTable.load(scoreFile);
        List<String> levelSetS = new ArrayList<>();


        //gui animation and keyboard sensor.
        GUI gui = new GUI("Game", FRAME_WIDTH, FRAME_HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        Animation highScoresAnimation = new HighScoresAnimation(highScoresTable, keyboardSensor);
        Menu<Task<Void>> subMenu = new MenuAnimation<>(keyboardSensor, ar, "level Set");
        GameFlow gameFlow = new GameFlow(ar, gui, keyboardSensor, highScoresTable, highScoresAnimation);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        LineNumberReader lineNumberReader =
                new LineNumberReader(inputStreamReader);

        try {
            lineNumberReader.mark(50000);
            String s;
            while ((s = lineNumberReader.readLine()) != null) {
                levelSetS.add(s);
                lineNumberReader.readLine();
            }
        } catch (IOException ex) {
            System.err.println("Failed reading Input Stream");
            ex.printStackTrace(System.err);
        }


        for (String st : levelSetS) {
            Task<Void> task1 = new Task<Void>() {
                @Override
                public Void run() {
                    try {
                        String path = null;
                        lineNumberReader.reset();
                        while (lineNumberReader.getLineNumber() != (levelSetS.indexOf(st) + 1) * 2) {
                            path = lineNumberReader.readLine();
                        }
                        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                        gameFlow.runLevels(ReadLevels.readFileLevels(inputStream));
                    } catch (IOException ex) {
                        System.err.println("Failed reading Input Stream");
                        ex.printStackTrace(System.err);
                    }

                    return null;
                }
            };
            String[] split = st.split(":");
            subMenu.addSelection(split[0], split[1], task1);
        }
        //create menu bar.
        Menu<Task<Void>> menu = new MenuAnimation<>(keyboardSensor, ar, "Arknoid");


        Task<Void> highScore = new Task<Void>() {
            @Override
            public Void run() {
                ar.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, highScoresAnimation));
                return null;
            }
        };
        menu.addSelection("h", "High Scores", highScore);

        File finalScoreFile = scoreFile;
        Task<Void> exit = new Task<Void>() {
            @Override
            public Void run() {
                highScoresTable.save(finalScoreFile);
                System.exit(1);
                return null;
            }
        };
        menu.addSelection("q", "Exit", exit);
        menu.addSubMenu("s", "start", subMenu);

        while (true) {
            menu.setStop(false);
            subMenu.setStop(false);
            ar.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}
