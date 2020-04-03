package arkanoid.levels;

import arkanoid.background.SunshineBackground;
import arkanoid.ball.Velocity;
import arkanoid.collision.Block;
import arkanoid.game.Counter;
import arkanoid.game.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static arkanoid.game.GameLevel.SPEED;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class WideEasyLevel implements LevelInformation {
    private Counter blocksCounter;

    /**
     * constructor.
     * <p>
     * initialize blockCounter.
     */
    public WideEasyLevel() {
        this.blocksCounter = new Counter();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();

        for (int i = 1, j = 285; i <= 5; i++, j += 15) {
            velocityList.add(Velocity.fromAngleAndSpeed(j, SPEED));
        }

        for (int i = 1, j = 15; i <= 5; i++, j += 15) {
            velocityList.add(Velocity.fromAngleAndSpeed(j, SPEED));
        }
        return velocityList;
    }

//    @Override
//    public List<Ball> initialBalls() {
//        arkanoid.graphics.Point startBorder = new arkanoid.graphics.Point(0, 0);
//        arkanoid.graphics.Point endBorder = new Point(FRAME_WIDTH, FRAME_HEIGHT);
//        List<Ball> ballList = new ArrayList<>();
//
//        ballList.add(new Ball(FRAME_WIDTH / 2 - 5 * 50, FRAME_HEIGHT - 200, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 - 4 * 50, FRAME_HEIGHT - 220, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 - 3 * 50, FRAME_HEIGHT - 240, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 - 2 * 50, FRAME_HEIGHT - 260, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 - 1 * 50, FRAME_HEIGHT - 280, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 + 1 * 50, FRAME_HEIGHT - 280, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 + 2 * 50, FRAME_HEIGHT - 260, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 + 3 * 50, FRAME_HEIGHT - 240, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 + 4 * 50, FRAME_HEIGHT - 220, 8, Color.yellow, startBorder, endBorder));
//        ballList.add(new Ball(FRAME_WIDTH / 2 + 5 * 50, FRAME_HEIGHT - 200, 8, Color.yellow, startBorder, endBorder));
//
//        return ballList;
//    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        SunshineBackground sunBackground = new SunshineBackground();
        return sunBackground;
    }

    @Override
    public List<Block> blocks() {
        this.blocksCounter.setCounter(0);
        List<Block> blockList = new ArrayList<>();
        int x = 20;
        for (int i = 1; i <= 2; i++) {
            blockList.add(new Block(x, 275, 50, 30, Color.red, 1));
            blocksCounter.increase(1);
            x += 50;
        }
        for (int i = 1; i <= 2; i++) {
            blockList.add(new Block(x, 275, 50, 30, Color.orange, 1));
            blocksCounter.increase(1);
            x += 50;
        }
        for (int i = 1; i <= 2; i++) {
            blockList.add(new Block(x, 275, 50, 30, Color.yellow, 1));
            blocksCounter.increase(1);
            x += 50;
        }
        for (int i = 1; i <= 2; i++) {
            blockList.add(new Block(x, 275, 50, 30, Color.GREEN, 1));
            blocksCounter.increase(1);
            x += 50;
        }

        blockList.add(new Block(x, 275, 60, 30, Color.GREEN, 1));
        blocksCounter.increase(1);
        x += 60;

        for (int i = 1; i <= 2; i++) {
            blockList.add(new Block(x, 275, 50, 30, Color.blue, 1));
            blocksCounter.increase(1);
            x += 50;
        }
        for (int i = 1; i <= 2; i++) {
            blockList.add(new Block(x, 275, 50, 30, Color.pink, 1));
            blocksCounter.increase(1);
            x += 50;
        }
        for (int i = 1; i <= 2; i++) {
            blockList.add(new Block(x, 275, 50, 30, Color.cyan, 1));
            blocksCounter.increase(1);
            x += 50;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocksCounter.getValue();
    }
}
