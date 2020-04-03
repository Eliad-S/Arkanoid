package arkanoid.collision;

import arkanoid.ball.Ball;
import arkanoid.graphics.Point;
import arkanoid.graphics.Rectangle;
import arkanoid.ball.Velocity;
import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Paddle implements Sprite, Collidable {
    public static final int DX = 22;
    public static final double LEFTLIMIT = 20;
    public static final double RIGHTLIMIT = 780;


    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private int speed;

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     *
     * @param upperLeft      the point located in the upperLeft place of the rectangle.
     * @param width          the width of the rectangle.
     * @param height         the height of the rectangle.
     * @param keyboardSensor keyboardSensor object.
     * @param color          the color we want to display it.
     */
    public Paddle(Point upperLeft, double width, double height, KeyboardSensor keyboardSensor, Color color) {
        this.paddle = new Rectangle(upperLeft, width, height);
        this.keyboard = keyboardSensor;
        this.color = color;
        this.speed = DX;
    }

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     *
     * @param x              the x value of the point located in the upperLeft place of the rectangle.
     * @param y              the y value of the point located in the upperLeft place of the rectangle.
     * @param width          the width of the rectangle.
     * @param height         the height of the rectangle.
     * @param keyboardSensor keyboardSensor object.
     * @param color          the color of the paddle.
     * @param speed          the speed of the paddle.the color we want to display it.
     */
    public Paddle(double x, double y, double width, double height, KeyboardSensor keyboardSensor,
                  Color color, int speed) {
        this.paddle = new Rectangle(x, y, width, height);
        this.keyboard = keyboardSensor;
        this.color = color;
        this.speed = speed;

    }

    /**
     * if the left key in the keyboard has been pressed, update the
     * upperLeft point of the rectangle left in "dx".
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            double x = this.paddle.getUpperLeft().getX();
            double y = this.paddle.getUpperLeft().getY();
            if ((x - speed) >= LEFTLIMIT) {
                this.paddle.setUpperLeft(x - speed, y);
            } else {
                this.paddle.setUpperLeft(LEFTLIMIT, y);

            }
        }
    }

    /**
     * if the right key in the keyboard has been pressed, update the
     * upperLeft point of the rectangle right in "dx".
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            double x = this.paddle.getUpperLeft().getX();
            double y = this.paddle.getUpperLeft().getY();
            if ((x + this.paddle.getWidth() + speed) <= RIGHTLIMIT) {
                this.paddle.setUpperLeft(x + speed, y);
            } else {
                this.paddle.setUpperLeft(RIGHTLIMIT - paddle.getWidth(), y);
            }
        }
    }

    @Override
    public void timePassed() {
        moveLeft();
        moveRight();
    }

    /**
     * draw the paddle type on the surface given.
     *
     * @param d a DrawSurface type.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.paddle.getUpperLeft().getX();
        int y = (int) this.paddle.getUpperLeft().getY();
        int width = (int) this.paddle.getWidth();
        int height = (int) this.paddle.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * return the rectangle(paddle).
     *
     * @return the rectangle type in our class (paddle).
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * check the place that have benn collide with our paddle and return a new velocity
     * by the results.
     *
     * @param collisionPoint  the point that have been collide with the paddle.
     * @param currentVelocity a velocity given.
     * @param hitter the ball that hit the paddle.
     * @return the new velocity value.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double upperLeftX = this.paddle.getUpperLeft().getX();
        double upperLeftY = this.paddle.getUpperLeft().getY();
        double width = this.paddle.getWidth();
        double height = this.paddle.getHeight();
        double d = (width) / 5;

        if ((x >= upperLeftX) && (x <= (upperLeftX + d))) {
            if ((x == upperLeftX) && (y > upperLeftY) && (y <= y + height)) {
                return new Velocity(-dx, dy);
            } else {
                if (y == upperLeftY) {
                    return Velocity.fromAngleAndSpeed(300, hitter.getVelocity().getSpeed());
                } else {
                    dy = -dy;
                }
            }
        }
        if ((x > upperLeftX + d) && x <= (upperLeftX + (2 * d))) {
            if (y == upperLeftY) {
                return Velocity.fromAngleAndSpeed(330, hitter.getVelocity().getSpeed());
            } else {
                dy = -dy;
            }
        }
        if (x > upperLeftX + (2 * d) && x <= upperLeftX + (3 * d)) {
            return new Velocity(dx, -dy);
        }
        if (x > upperLeftX + (3 * d) && x <= upperLeftX + (4 * d)) {
            if (y == upperLeftY) {
                return Velocity.fromAngleAndSpeed(30, hitter.getVelocity().getSpeed());
            } else {
                dy = -dy;
            }
        }
        if (x > upperLeftX + (4 * d) && x <= upperLeftX + width) {
            if (x == upperLeftX + width && y > upperLeftY) {
                return new Velocity(-dx, dy);
            } else {
                if (y == upperLeftY) {
                    return Velocity.fromAngleAndSpeed(60, hitter.getVelocity().getSpeed());
                } else {
                    dy = -dy;
                }
            }
        }

        return new Velocity(dx, dy);
    }


    /**
     * Add this paddle to the game.
     *
     * @param g Game type.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * set the Paddle UpperLeft value to the x and y values given.
     *
     * @param x the x value.
     * @param y the y value.
     */
    public void setPaddleUpperLeft(double x, double y) {
        this.paddle.setUpperLeft(x, y);

    }

}