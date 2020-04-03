package arkanoid.ball;

import arkanoid.collision.Collidable;
import arkanoid.collision.CollisionInfo;
import arkanoid.game.GameLevel;
import arkanoid.game.GameEnvironment;
import arkanoid.game.Sprite;
import arkanoid.graphics.Line;
import arkanoid.graphics.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Ball implements Sprite {
    private Point center;
    private int radios;
    private Color color;
    private Velocity v = Velocity.fromAngleAndSpeed(0, 10);
    private Point startBorder;
    private Point endBorder;
    private GameEnvironment gameEnvironment;

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     *
     * @param center the center of the ball.
     * @param r      radios of the ball.
     * @param color  the color we want to display it.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radios = r;
        this.color = color;
    }

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     *
     * @param x     the x value of the center of the ball.
     * @param y     the y value of the center of the ball.
     * @param r     radios of the ball.
     * @param color the color we want to display it.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radios = r;
        this.color = color;
    }

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     *
     * @param x           the x value of the center of the ball.
     * @param y           the y value of the center of the ball.
     * @param r           radios of the ball.
     * @param color       the color we want to display it.
     * @param startBorder point type, has the up and left boarder.
     * @param endBorder   point type, has the down and right boarder.
     */
    public Ball(double x, double y, int r, java.awt.Color color, Point startBorder, Point endBorder) {
        this.center = new Point(x, y);
        this.radios = r;
        this.color = color;
        this.startBorder = startBorder;
        this.endBorder = endBorder;
    }

    // accessors

    /**
     * return the x value.
     *
     * @return the x value of the center.
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * return the y value.
     *
     * @return the y value of the center.
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * return the radios value.
     *
     * @return the radios value of the ball.
     */
    public int getSize() {
        return this.radios;
    }

    /**
     * return the border of the ball movement from above.
     *
     * @return the border value.
     */
    public double getUp() {
        return this.startBorder.getY();
    }

    /**
     * return the floor border of the ball movement from .
     *
     * @return the border value.
     */
    public double getDown() {
        return this.endBorder.getY();
    }

    /**
     * return the left border of the ball movement.
     *
     * @return the border value.
     */
    public double getLeft() {
        return this.startBorder.getX();
    }

    /**
     * return the left border of the ball movement.
     *
     * @return the border value.
     */
    public double getRight() {
        return this.endBorder.getX();
    }

    /**
     * return the color of the ball.
     *
     * @return color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * return the game environment of the ball.
     *
     * @return GameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }


    /**
     * set the velocity of the ball.
     *
     * @param velocity the velocity of the ball.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * create a velocity type and set the velocity filed of the ball.
     *
     * @param dx the change in the x's axis.
     * @param dy the change in the y's axis.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * set the x value of the center's point.
     *
     * @param xCenter a new x value for the center.
     */
    public void setXCenter(double xCenter) {
        this.center.setX(xCenter);
    }

    /**
     * set the y value of the center's point.
     *
     * @param yCenter a new y value for the center.
     */
    public void setYCenter(double yCenter) {
        this.center.setY(yCenter);
    }

    /**
     * set the game environment of the ball.
     *
     * @param g the game environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * set a new point instead of our ball's center point.
     *
     * @param applyToPoint the point we want to set.
     */
    private void setCenter(Point applyToPoint) {
        this.center = applyToPoint;
    }

    /**
     * draw the ball on the given DrawSurface.
     * <p>
     *
     * @param surface the surface we want to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radios);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radios);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }


    /**
     * get the velocity of the ball.
     *
     * @return v  the velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * update the velocity by checking first the location of our ball.
     * <p>
     * the method reverse the velocity value if it find out that the ball
     * will get over the border in the next movement.
     *
     * @param up    the boarder from above.
     * @param down  the border from beneath.
     * @param left  the left border.
     * @param right the right border.
     */
    public void updateVelocity(int up, int down, int left, int right) {
        Random rand = new Random(); // create a random-number generator
        double x = this.center.getX();
        double y = this.center.getY();
        double dx = this.v.getDx();
        double dy = this.v.getDy();
        float re = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        int r = this.getSize();
        if ((x + r + dx > right && dx > 0) || (x - r + dx < left && dx < 0)) {
            this.v.setDx(-dx);
            this.color = new Color(g, re, b);
        }

        if ((y + r + dy > down && dy > 0) || (y - r + dy < up && dy < 0)) {
            this.v.setDy(-dy);
            this.color = new Color(g, re, b);
        }
    }


    /**
     * creating a line that representing the ball trajectory.
     * use the center of the ball and the velocity to get to point.
     *
     * @return a line .
     */
    public Line ballTrajectory() {
        double x = this.center.getX();
        double y = this.center.getY();
        double dx = this.v.getDx();
        double dy = this.v.getDy();
        return new Line(x, y, x + dx, y + dy);
    }

    /**
     * add to the gameEnvironment another collidable type.
     *
     * @param c collidable type.
     */
    public void addGameEnvironment(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    /**
     * update the location ball by adding to the x an y value of the center
     * the velocity value,dx and dy.
     * the method is checking if there is an intersection with a Collidable object,
     * and if it does, updating the velocity of the ball by the object's terms.
     * else, just updating simply with our current velocity.
     */
    public void moveOneStep() {
        //get the ball trajectory line.
        Line ballTrajectory = this.ballTrajectory();
        //get the closest collision to the ball's center.
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(ballTrajectory);
        // there is not a collision in the ball's trajectory.
        if (collisionInfo == null) {
            //set the center's point our the current velocity.
            this.setCenter(applyToPoint(this.center));
        } else {
            Point nextInsect = collisionInfo.collisionPoint();
            Collidable collideObject = collisionInfo.collisionObject();
            double xNextInsect = nextInsect.getX();
            double yNextInsect = nextInsect.getY();
            double upperLeftX = collideObject.getCollisionRectangle().getUpperLeft().getX();
            double upperLeftY = collideObject.getCollisionRectangle().getUpperLeft().getY();
            double width = collideObject.getCollisionRectangle().getWidth();

            if (xNextInsect > upperLeftX && xNextInsect < upperLeftX + width) {
                if (yNextInsect == upperLeftY) {
                    this.setYCenter(yNextInsect - radios);
                } else {
                    this.setYCenter(yNextInsect + radios);
                }
            } else {
                if (xNextInsect == upperLeftX) {
                    setXCenter(xNextInsect - radios);
                } else {
                    setXCenter(xNextInsect + radios);
                }

            }

            this.setVelocity(collideObject.hit(this, nextInsect, this.getVelocity()));


        }
    }

    /**
     * return new point .
     * <p>
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * <p>
     *
     * @param p the point we want to change with the velocity value.
     * @return newPoint the new point
     */
    public Point applyToPoint(Point p) {
        double dx = this.v.getDx();
        double dy = this.v.getDy();
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }

    /**
     * return new point .
     * <p>
     * Take a point with position (x,y) and return a new point
     * with position (x+dx\10, y+dy\10).
     * <p>
     *
     * @param p the point we want to change with the velocity value.
     * @return newPoint the new point
     */
    public Point applyToCloserPoint(Point p) {
        double dx = this.v.getDx() / 10;
        double dy = this.v.getDy() / 10;
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }
    /**
     * remove ball from the game.
     * @param game game.
     *
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}

