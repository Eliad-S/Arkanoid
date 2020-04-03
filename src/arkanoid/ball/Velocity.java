package arkanoid.ball;

import java.util.Random;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Velocity {
    private double dx;
    private double dy;
    private double speed;

    /**
     * constructor.
     * <p>
     * insert values in our filed.
     *
     * @param dx change in the x's axis.
     * @param dy change in the y's axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * static constructor.
     * <p>
     * get the change movement we want by angle and speed,
     * convert it to dx and dy value and create a new velocity type.
     * <p>
     *
     * @param angle the angle of the movement we want.
     * @param speed the speed of the ball's movement.
     * @return the new velocity value.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle - 90);
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * random and create a velocity value.
     * <p>
     * the method random an angle and create a velocity type with that angle
     * and a speed that are measured by the ball size.
     * <p>
     *
     * @param ball the ball we want to define a velocity.
     * @return the random velocity value.
     */
    public static Velocity randVelocityByLarge(Ball ball) {
        Random rand = new Random(); // create a random-number generator
        int angle = rand.nextInt(360);
        if (ball.getSize() >= 50) {
            return fromAngleAndSpeed(angle, 1);
        } else {
            return fromAngleAndSpeed(angle, (50 - ball.getSize()) / 5);
        }

    }

    /**
     * return the dx value.
     *
     * @return the dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * return the dy value.
     *
     * @return the dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * set the dx value.
     *
     * @param dX the dx value we want to change to.
     */
    public void setDx(double dX) {
        this.dx = dX;
    }

    /**
     * set the dy value.
     *
     * @param dY the dy value we want to change to.
     */
    public void setDy(double dY) {
        this.dy = dY;
    }

    /**
     * calculate ant return the spped of the velocity..
     *
     * @return the speed.
     */
    public double getSpeed() {

        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}