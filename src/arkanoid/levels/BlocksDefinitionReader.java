package arkanoid.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class BlocksDefinitionReader {

    /**
     * read from the text anf return a BlocksFromSymbolsFactory type withe information.
     *
     * @param reader reader from file.
     * @return BlocksFromSymbolsFactory type.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.InputStream reader) {

        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        BlockCreatorClass defaultBlock = new BlockCreatorClass();
        BufferedReader bufferedReader = null;
        try {

            String s;
            bufferedReader = new BufferedReader(new InputStreamReader(reader));

            while ((s = bufferedReader.readLine()) != null) {
                if (s.isEmpty() || s.charAt(0) == '#') {
                    continue;
                }

                if (s.startsWith("default")) {
                    defaultBlock.createFromText(s);
                    continue;
                }

                if (s.startsWith("bdef")) {
                    BlockCreatorClass b = new BlockCreatorClass();
                    b.createFromText(s);
                    b.completeArgs(defaultBlock);
                    blockCreators.put(b.getSymbol(), b);
                    continue;
                }

                if (s.startsWith("sdef")) {
                    String[] def = (s).split(" ");
                    String[] symbol = def[1].split(":");
                    String[] width = def[2].split(":");
                    spacerWidths.put(symbol[1], Integer.parseInt(width[1]));
                }
            }

        } catch (IOException ex) {
            System.err.println("Failed reading from file block definition");
            ex.printStackTrace(System.err);
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}