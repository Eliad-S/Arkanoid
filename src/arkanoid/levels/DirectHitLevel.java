package arkanoid.levels;

import arkanoid.background.DirectHitBackground;
import arkanoid.ball.Velocity;
import arkanoid.collision.Block;
import arkanoid.game.Counter;
import arkanoid.game.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static arkanoid.game.Ass7Game.FRAME_WIDTH;
import static arkanoid.game.GameLevel.SPEED;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-5-19
 */
public class DirectHitLevel implements LevelInformation {
    private Counter blocksCounter;

    /**
     * constructor.
     * <p>
     * initialize blockCounter.
     */
    public DirectHitLevel() {
        this.blocksCounter = new Counter();

    }


    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = Velocity.fromAngleAndSpeed(0, SPEED);
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(v);
        return velocityList;
    }

//    @Override
//    public List<Ball> initialBalls() {
//        Point startBorder = new Point(0, 0);
//        Point endBorder = new Point(FRAME_WIDTH, FRAME_HEIGHT);
//        List<Ball> ballList = new ArrayList<>();
//        ballList.add(new Ball(FRAME_WIDTH / 2, 565, 8, Color.yellow, startBorder, endBorder));
//        return ballList;
//    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        DirectHitBackground background = new DirectHitBackground();
        return background;
    }

    @Override
    public List<Block> blocks() {
        this.blocksCounter.setCounter(0);
        List<Block> hitBlock = new ArrayList<>();
        Block block = new Block((FRAME_WIDTH / 2) - 15, 175, 30, 30, Color.red, 1);
        hitBlock.add(block);
        this.blocksCounter.increase(1);
        return hitBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksCounter.getValue();
    }
}
