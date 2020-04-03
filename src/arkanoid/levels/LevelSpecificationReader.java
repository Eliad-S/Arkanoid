package arkanoid.levels;

import arkanoid.background.ColorBackground;
import arkanoid.background.ColorsParser;
import arkanoid.background.ImageS;
import arkanoid.ball.Velocity;
import arkanoid.collision.Block;
import arkanoid.game.Counter;
import arkanoid.game.Sprite;


import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class LevelSpecificationReader {

    /**
     * read from a file an execute the level information into a list.
     * the function read level level.
     *
     * @param reader .
     * @return a list of levelInformation type.
     * @throws IOException .
     */
    public List<LevelInformation> fromReader(java.io.BufferedReader reader) throws IOException {
        List<LevelInformation> levelInformationList = new ArrayList<>();
        LevelInformation levelInformation;

        while ((levelInformation = getLevelInformationString(reader)) != null) {
            levelInformationList.add(levelInformation);
        }

        if (reader != null) {
            reader.close();
        }

        return levelInformationList;
    }

    /**
     * reading a single level specification block from file
     * (from START_LEVEL to END_LEVEL).
     * This task will get a java.io.Reader and return a list of strings.
     *
     * @param bufferedReader .
     * @return a levelInformation.
     * @throws IOException .
     */
    public LevelInformation getLevelInformationString(BufferedReader bufferedReader) throws IOException {
        List<String> levelS = new ArrayList<>();
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
        int counter = 1;
        try {
            String s;
            s = bufferedReader.readLine();
            if (s == null) {
                return null;
            }
            while (s != null) {
                if (counter == 1 && !s.equals("START_LEVEL")) {
                    s = bufferedReader.readLine();
                    continue;
                }

//                if (s.equals("START_BLOCKS")) {
//                    blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(bufferedReader);
//                }
                if (counter != 1 && !s.isEmpty()) {
                    levelS.add(s);
                }

                if (s.equals("END_LEVEL")) {
                    break;
                }
                counter += 1;
                s = bufferedReader.readLine();
            }
        } catch (EOFException ex) {
            return null;
        } catch (IOException ex) {
            System.err.println("Failed reading Input Stream");
            ex.printStackTrace(System.err);
        } finally {
            if (counter == 1) {
                return null;
            }
        }

        return interpretToObject(levelS);
    }
//

    /***
     * this function get a list of string that have the information for the levelInformation object.
     * it Understanding the content of the level specification of a single level: this will go over the strings,
     * split and parse them, and map them to java objects, resulting in a LevelInformation object.
     * @param levelInS .
     * @return a levelInformation object.
     * @throws IOException .
     */
    public LevelInformation interpretToObject(List<String> levelInS) throws IOException {
        String levelName = null;
        List<Velocity> velocityList = new ArrayList<>();
        List<String> info = new ArrayList<>();
        int paddleSpeed = 0, paddleWidth = 0, blocksStartX = 0, blocksStartY = 0, rowHeight = 0, numBlocks = 0;
        String blockDefinition = null;
        List<Block> blockList = null;
        Sprite background = null;
        for (int i = 0; i < 10; i++) {
            if (levelInS.get(i).contains(":")) {
                int indexSplit = levelInS.get(i).indexOf(':');
                info.add(levelInS.get(i).substring(indexSplit + 1));
            }
        }
        if (info.size() < 10) {
            System.out.println("level information missing filed");
            System.exit(1);
            return null;
        }
        for (String s : levelInS) {
            if (s.startsWith("level_name")) {
                levelName = info.get(levelInS.indexOf(s));
                continue;
            }
            if (s.startsWith("ball_velocities")) {
                String[] ballVelocityInfo = info.get(1).split(" ");
                for (int i = 0; i < ballVelocityInfo.length; i++) {
                    String[] velocity = ballVelocityInfo[i].split(",");
                    double angle = Integer.parseInt(velocity[0]);
                    double speed = Integer.parseInt(velocity[1]);
                    velocityList.add(Velocity.fromAngleAndSpeed(angle, speed));
                    continue;
                }
            }
            if (s.startsWith("background")) {
                String backInfo = info.get(levelInS.indexOf(s));
                if (backInfo.startsWith("image")) {
                    background = new ImageS(backInfo, 0, 0);
                } else {
                    background = new ColorBackground(ColorsParser.colorFromString(info.get(2)),
                            0, 0, 800, 600);
                }
                continue;
            }
            if (s.startsWith("paddle_speed")) {
                paddleSpeed = Integer.parseInt(info.get(levelInS.indexOf(s)));
                continue;
            }
            if (s.startsWith("paddle_width")) {
                paddleWidth = Integer.parseInt(info.get(levelInS.indexOf(s)));
                continue;
            }
            if (s.startsWith("block_definitions")) {
                blockDefinition = info.get(levelInS.indexOf(s));
                continue;
            }
            if (s.startsWith("blocks_start_x")) {
                blocksStartX = Integer.parseInt(info.get(levelInS.indexOf(s)));
                continue;
            }
            if (s.startsWith("blocks_start_y")) {
                blocksStartY = Integer.parseInt(info.get(levelInS.indexOf(s)));
                continue;
            }
            if (s.startsWith("row_height")) {
                rowHeight = Integer.parseInt(info.get(levelInS.indexOf(s)));
                continue;

            }
            if (s.startsWith("num_blocks")) {
                numBlocks = Integer.parseInt(info.get(levelInS.indexOf(s)));
                continue;
            }
        }

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefinition);
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(is);
        blockList = interpretBlocks(levelInS, blocksFromSymbolsFactory, blocksStartX, blocksStartY, rowHeight);

        List<Block> finalBlockList = blockList;
        int finalPaddleWidth = paddleWidth;
        int finalPaddleSpeed = paddleSpeed;
        String finalLevelName = levelName;
        Sprite finalBackground = background;
        int finalNumBlocks = numBlocks;
        LevelInformation levelInformation = new LevelInformation() {
            private Counter blocksCounter = new Counter();

            @Override
            public int numberOfBalls() {
                return velocityList.size();
            }

            @Override
            public List<Velocity> initialBallVelocities() {
                return velocityList;
            }

            @Override
            public int paddleSpeed() {
                return finalPaddleSpeed;
            }

            @Override
            public int paddleWidth() {
                return finalPaddleWidth;
            }

            @Override
            public String levelName() {
                return finalLevelName;
            }

            @Override
            public Sprite getBackground() {
                return finalBackground;
            }

            @Override
            public List<Block> blocks() {
                return finalBlockList;
            }

            @Override
            public int numberOfBlocksToRemove() {
                return finalBlockList.size();
            }

        };

        return levelInformation;
    }

    /**
     * this function interpret the block and create them by reading the blocksFromSymbolsFactory' information
     * about the block properties and create them at the right place described int the levels specification files.
     *
     * @param levelInS                 .
     * @param blocksFromSymbolsFactory .
     * @param xPos                     .
     * @param yPos                     .
     * @param rowHeight                .
     * @return list of blocks.
     */
    public List<Block> interpretBlocks(List<String> levelInS, BlocksFromSymbolsFactory blocksFromSymbolsFactory,
                                       int xPos, int yPos, int rowHeight) {
        List<Block> blockList = new ArrayList<>();

        int currentx = xPos;
        int flag = 0;
        for (String s : levelInS) {

            if (flag == 1) {
                for (int i = 0; i < s.length(); i++) {
                    if (blocksFromSymbolsFactory.isSpaceSymbol(s.substring(i, i + 1))) {
                        currentx += blocksFromSymbolsFactory.getSpaceWidth(s.substring(i, i + 1));
                        continue;
                    }
                    if (blocksFromSymbolsFactory.isBlockSymbol(s.substring(i, i + 1))) {
                        Block block = blocksFromSymbolsFactory.getBlock(s.substring(i, i + 1), currentx, yPos);
                        blockList.add(block);
                        currentx += block.getWidth();
                        continue;
                    }
                }
                yPos = yPos + rowHeight;
                currentx = xPos;
            }

            if (s.equals("START_BLOCKS")) {
                flag = 1;
            }
            if (s.equals("END_BLOCKS")) {
                break;
            }
        }
        return blockList;
    }
}
