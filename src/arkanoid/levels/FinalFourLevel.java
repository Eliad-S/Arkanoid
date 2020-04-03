package arkanoid.levels;

import arkanoid.background.Final4Background;
import arkanoid.ball.Velocity;
import arkanoid.collision.Block;
import arkanoid.game.Counter;
import arkanoid.game.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static arkanoid.game.GameLevel.SPEED;
import static arkanoid.game.GameLevel.FRAME_WIDTH;


/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class FinalFourLevel implements LevelInformation {
    private Counter blocksCounter;

    /**
     * constructor.
     * <p>
     * initialize blockCounter.
     */
    public FinalFourLevel() {
        this.blocksCounter = new Counter();

    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = Velocity.fromAngleAndSpeed(30, SPEED);
        Velocity v2 = Velocity.fromAngleAndSpeed(0, SPEED);
        Velocity v3 = Velocity.fromAngleAndSpeed(30, SPEED);
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(v1);
        velocityList.add(v2);
        velocityList.add(v3);
        return velocityList;
    }

//    @Override
//    public List<Ball> initialBalls() {
//        Point startBorder = new Point(0, 0);
//        Point endBorder = new Point(FRAME_WIDTH, FRAME_HEIGHT);
//        List<Ball> ballList = new ArrayList<>();
//        ballList.add(new Ball(FRAME_WIDTH / 2 - 100, 500, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2, 500 - 50, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 + 100, 500, 8, Color.yellow, startBorder, endBorder));
//
//        return ballList;
//    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite colorBackground = new Final4Background();
        return colorBackground;
    }

    @Override
    public List<Block> blocks() {
        this.blocksCounter.setCounter(0);
        List<Block> blockList = new ArrayList<>();
        for (int x = 20; x <= FRAME_WIDTH - 40; x += 69) {
            Block block = new Block(x, 100, 69, 30, Color.GRAY, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }
        for (int x = 20; x <= FRAME_WIDTH - 40; x += 69) {
            Block block = new Block(x, 130, 69, 30, Color.red, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }
        for (int x = 20; x <= FRAME_WIDTH - 40; x += 69) {
            Block block = new Block(x, 160, 69, 30, Color.yellow, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }

        for (int x = 20; x <= FRAME_WIDTH - 40; x += 69) {
            Block block = new Block(x, 190, 69, 30, Color.GREEN, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }

        for (int x = 20; x <= FRAME_WIDTH - 40; x += 69) {
            Block block = new Block(x, 220, 69, 30, Color.white, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }

        for (int x = 20; x <= FRAME_WIDTH - 40; x += 69) {
            Block block = new Block(x, 250, 69, 30, Color.pink, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }

        for (int x = 20; x <= FRAME_WIDTH - 40; x += 69) {
            Block block = new Block(x, 280, 69, 30, Color.MAGENTA, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksCounter.getValue();
    }
}

