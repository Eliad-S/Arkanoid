package arkanoid.levels;

import arkanoid.background.GreenBackground;
import arkanoid.ball.Velocity;
import arkanoid.collision.Block;
import arkanoid.game.Counter;
import arkanoid.game.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static arkanoid.game.GameLevel.FRAME_WIDTH;
import static arkanoid.game.GameLevel.SPEED;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class Green3Level implements LevelInformation {
    private Counter blocksCounter;

    /**
     * constructor.
     * <p>
     * initialize blockCounter.
     */
    public Green3Level() {
        this.blocksCounter = new Counter();

    }


    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = Velocity.fromAngleAndSpeed(30, SPEED);
        Velocity v2 = Velocity.fromAngleAndSpeed(330, SPEED);
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(v1);
        velocityList.add(v2);
        return velocityList;
    }

//    @Override
//    public List<Ball> initialBalls() {
//        Point startBorder = new Point(0, 0);
//        Point endBorder = new Point(FRAME_WIDTH, FRAME_HEIGHT);
//        List<Ball> ballList = new ArrayList<>();
//        for (int i = 0; i < this.numberOfBalls(); i++) {
//            ballList.add(new Ball(FRAME_WIDTH / 2, 565, 8, Color.yellow, startBorder, endBorder));
//        }
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite colorBackground = new GreenBackground();
        return colorBackground;
    }

    @Override
    public List<Block> blocks() {
        this.blocksCounter.setCounter(0);
        List<Block> blockList = new ArrayList<>();
        for (int x = 80; x <= FRAME_WIDTH - 90; x += 70) {
            Block block = new Block(x, 100, 70, 30, Color.GRAY, 2);
            blockList.add(block);
            blocksCounter.increase(1);
        }
        for (int x = 150; x <= FRAME_WIDTH - 90; x += 70) {
            Block block = new Block(x, 130, 70, 30, Color.red, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }
        for (int x = 220; x <= FRAME_WIDTH - 90; x += 70) {
            Block block = new Block(x, 160, 70, 30, Color.yellow, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }

        for (int x = 290; x <= FRAME_WIDTH - 90; x += 70) {
            Block block = new Block(x, 190, 70, 30, Color.GREEN, 1);
            blockList.add(block);
            blocksCounter.increase(1);
        }

        for (int x = 360; x <= FRAME_WIDTH - 90; x += 70) {
            Block block = new Block(x, 220, 70, 30, Color.lightGray, 1);
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
