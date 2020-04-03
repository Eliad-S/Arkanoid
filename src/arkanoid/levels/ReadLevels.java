package arkanoid.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class ReadLevels {

    /**
     * the function get a file path and interpret the level information file to
     * a list of level information type.
     *
     * @param inputStream the start of the level information file.
     * @return a list of levelInformation type.
     * @throws IOException .
     */
    public static List<LevelInformation> readFileLevels(InputStream inputStream) throws IOException {
        List<LevelInformation> levelInformationList = null;
        //FileReader fileReader = null;
        //File file = new File(filepath);
        BufferedReader bufferedReader = null;
        try {
            LevelSpecificationReader ls = new LevelSpecificationReader();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            levelInformationList = ls.fromReader(bufferedReader);
        } catch (IOException ex) {
            System.err.println("Failed reading");
            ex.printStackTrace(System.err);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return levelInformationList;
    }
}
