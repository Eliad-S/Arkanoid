package arkanoid.highScore;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 * Create an empty high-scores table with the specified size.
 * The size means that the table holds up to size top scores.
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> scoreList;

    /**
     * constructor.
     *
     * @param size max size of score we want to save.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreList = new ArrayList<>();
    }

    /**
     * Add a high-score.
     *
     * @param score new scoreInfo.
     */
    public void add(ScoreInfo score) {
        this.scoreList.add(score);
        this.sortScore();
        int listSize = this.scoreList.size();
        if (listSize > this.size) {
            this.scoreList.remove(listSize - 1);
        }
    }

    /**
     * @return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * set table size.
     *
     * @param num return the size of the tableScore.
     */
    public void setSize(int num) {
        this.size = num;
    }

    /**
     * Return the 'size' top score list by a list.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return top score list.
     */
    public List<ScoreInfo> getHighScores() {
        List<ScoreInfo> highTen = new ArrayList<>();

        for (int i = 0; i < this.size && i < this.scoreList.size(); i++) {
            highTen.add(this.scoreList.get(i));
        }
        return highTen;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
//    public int getRank(int score) {
//        for (ScoreInfo s : scoreList) {
//            if (s.getScore() == score) {
//                return scoreList.indexOf(s);
//            }
//        }
//        return -1;
//    }

    /**
     * Clears the table.
     */
    public void clear() {
        scoreList.clear();
    }

    /**
     * Load table data from file.
     * we are loading the file by serialization.
     * we are loading scoreInfo object
     *
     * @param filename a file to load from.
     */
    // Current table data is cleared.
    public void load(File filename) {
        ObjectInputStream objectInputStream = null;
        try {

            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));

            // unsafe down casting, we better be sure that the stream really contains a Person!\
            Object s;
            while ((s = objectInputStream.readObject()) != null) {
                scoreList.add((ScoreInfo) s);
            }
        } catch (EOFException ex) {
            System.err.println("");
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }


    }

    /**
     * Save table data to the specified file.
     *
     * @param filename file to save at.
     */
    public void save(File filename) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            for (ScoreInfo s : scoreList) {
                objectOutputStream.writeObject(s);
            }
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {

                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }

    }

    /**
     * sort the list of score form the highest to the lowest.
     */
    public void sortScore() {
        Comparator<ScoreInfo> c = new Comparator<ScoreInfo>() {
            @Override
            public int compare(ScoreInfo o1, ScoreInfo o2) {
                if (o1.getScore() > o2.getScore()) {
                    return 1;
                } else {
                    if (o1.getScore() < o2.getScore()) {
                        return -1;
                    }
                }
                return 0;
            }

        };
        Collections.sort(this.scoreList, c);
        Collections.reverse(this.scoreList);
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * don't find use for this function.
     *
     * @param filename file.
     * @return HighScoresTable.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(0);
        highScoresTable.load(filename);
        return highScoresTable;
    }

}