package arkanoid.highScore;

import java.io.Serializable;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * constructor.
     * <p>
     * create two point type parameters and insert there values in Line's object fields.
     *
     * @param name name of the player.
     * @param score the score he got.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @return score
     */
    public int getScore() {
        return score;
    }
}