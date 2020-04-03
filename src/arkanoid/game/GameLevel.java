package arkanoid.game;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */

import arkanoid.animation.AnimationRunner;
import arkanoid.animation.Animation;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.animation.PauseScreen;
import arkanoid.animation.CountdownAnimation;
import arkanoid.ball.Ball;
import arkanoid.ball.Velocity;
import arkanoid.collision.Block;
import arkanoid.collision.Collidable;
import arkanoid.collision.Paddle;
import arkanoid.graphics.Point;
import arkanoid.levels.LevelInformation;
import arkanoid.listeners.BallRemover;
import arkanoid.listeners.BlockRemover;
import arkanoid.listeners.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class GameLevel implements Animation {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int SPEED = 8;
    public static final int SCORE_BLOCK_HEIGHT = 25;
    private LevelInformation levelInformation;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private boolean running;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;
    private Counter livesReminders;
    private KeyboardSensor keyboard;
    private Paddle paddle;


    /**
     * constructor.
     * <p>
     * initialize our filed.
     *
     * @param levelInformation LevelInformation.
     * @param keyboardSensor   KeyboardSensor.
     * @param animationRunner  AnimationRunner.
     * @param livesReminders   Counter that its value signify the lives reminder.
     * @param currentScore     the score so far.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter livesReminders, Counter currentScore) {

        this.levelInformation = levelInformation;

        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.running = true;
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.currentScore = currentScore;
        this.livesReminders = livesReminders;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.paddle = new Paddle(335, 580, 150, 20, keyboardSensor, Color.orange, SPEED);

    }

    /**
     * add a Collidable type the the game's environment.
     *
     * @param c Collidable type.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a Sprite type the the game's sprite's list.
     *
     * @param s sprite type.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball
     * and add them to the game.
     */
    public void initialize() {
        levelInformation.getBackground().addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, this.getRemainingBlocksCounter());
        BallRemover ballRemover = new BallRemover(this, this.getRemainingBallsCounter());
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.getCurrentScore());
        ScoreIndicator scoreIndicator = new ScoreIndicator(0, 0, 800, SCORE_BLOCK_HEIGHT, this.currentScore);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.livesReminders);
        livesIndicator.addToGame(this);
        LevelName levelName = new LevelName(this.levelInformation);
        levelName.addToGame(this);
        Block[] border = new Block[3];
        border[0] = new Block(0, 0 + SCORE_BLOCK_HEIGHT, FRAME_WIDTH, 20, Color.GRAY, 0);
        border[1] = new Block(FRAME_WIDTH - 20, 20 + SCORE_BLOCK_HEIGHT, 20, FRAME_HEIGHT - 20, Color.GRAY, 0);
        border[2] = new Block(0, 0 + 20 + SCORE_BLOCK_HEIGHT, 20, FRAME_HEIGHT - 20, Color.GRAY, 0);
        Block deathRegion = new Block(0, FRAME_HEIGHT + 16, FRAME_WIDTH, 20, Color.GRAY, 0);

        for (int i = 0; i <= 2; i++) {
            border[i].addToGame(this);
        }
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        List<Block> blockList = this.levelInformation.blocks();
        for (Block b : blockList) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
        }

        this.remainingBlocks.increase(levelInformation.numberOfBlocksToRemove());
        int paddleWidth = this.levelInformation.paddleWidth();
        this.setPaddle(new Paddle((FRAME_WIDTH / 2) - (paddleWidth / 2), 580, paddleWidth,
                20, this.keyboard, Color.orange, this.levelInformation.paddleSpeed()));
        this.paddle.addToGame(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);

        this.sprites.notifyAllTimePassed();
        if (this.remainingBlocks.getValue() == 0) {
            this.currentScore.increase(100);
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }

        if (keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen(this.keyboard);
            Animation a1k = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pauseScreen);
            this.runner.run(a1k);
        }


    }

    /**
     * run the game animation for one turn, that's mean or until the player
     * loos one life or if there is no more block is the game level.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.

        this.runner.run(this);
    }

    /**
     * set the paddle.
     *
     * @param p new paddle.
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * create the balls if there is no more balls left and place the paddle at the
     * middle of the screen.
     */
    private void createBallsOnTopOfPaddle() {
        placePaddle();
        createBalls();
    }

    /**
     * create the balls and add them to the game.
     */
    public void createBalls() {
        List<Ball> ballList = this.initialBalls();
        this.remainingBalls.increase(ballList.size());
        List<Velocity> ballVelocities = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < ballList.size(); i++) {
            ballList.get(i).setVelocity(ballVelocities.get(i));
            ballList.get(i).addToGame(this);
            ballList.get(i).setGameEnvironment(environment);
        }
    }
    /**
     * initial the ball withe the velocity list from the levelInformation filed.
     * @return return a list of ball with with the given velocities.
     */
    private List<Ball> initialBalls() {
        Point startBorder = new Point(0, 0);
        Point endBorder = new Point(FRAME_WIDTH, FRAME_HEIGHT);
        List<Ball> ballList = new ArrayList<>();
        for (Velocity v : this.levelInformation.initialBallVelocities()) {
            Ball newBall = new Ball(FRAME_WIDTH / 2, 565, 8, Color.yellow, startBorder, endBorder);
            ballList.add(newBall);
            newBall.setVelocity(v);
        }
        return ballList;
    }

    /**
     * place the paddle to the center of the screen.
     */
    public void placePaddle() {

        this.paddle.setPaddleUpperLeft((FRAME_WIDTH / 2) - (this.levelInformation.paddleWidth() / 2), 580);
    }

    /**
     * @return the environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * @return the sprieCollection.
     */
    public SpriteCollection getSpriteCollection() {
        return this.sprites;
    }

    /**
     * @return the Counter of remaining blocks.
     */
    public Counter getRemainingBlocksCounter() {
        return this.remainingBlocks;
    }

    /**
     * @return the Counter of the remaining balls.
     */
    public Counter getRemainingBallsCounter() {
        return this.remainingBalls;
    }

    /**
     * @return the remaining Counter of lives .
     */
    public Counter getLivesReminders() {
        return this.livesReminders;
    }

    /**
     * remove the collidable from the game.
     *
     * @param c a collidable .
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().getCollidables().remove(c);
    }

    /**
     * remove the sprite from the sprites list in sprite collection.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        this.getSpriteCollection().getSprites().remove(s);
    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

}
