package arkanoid.game;


import java.io.InputStream;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Ass7Game {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    /**
     * run the game.
     *
     * @param args an input from the user(does'nt need to get a value).
     */
    public static void main(String[] args) {
        InputStream is;
        String filePath = null;
        if (args.length == 1) {
            filePath = args[0];
        }
        if (filePath != null) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            //levelSet = new File(filePath);
        } else {
            //levelSet = new File("level_sets.txt");
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");

        }

        RunGame.run(is);
    }
}