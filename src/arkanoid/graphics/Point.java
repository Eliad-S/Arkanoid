package arkanoid.graphics;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * <p>
     * give values to the object fields.
     *
     * @param x double type.
     * @param y double type.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other an other point to compare with our point.
     * @return Description text text text.
     */
    public double distance(Point other) {
        double distance = Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));
        return distance;
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other Point type.
     * @return boolean return true or false.
     */
    public boolean equals(Point other) {
        if ((Math.abs(this.x - other.x) <= 0.00001 && Math.abs(this.y - other.y) <= 0.00001)) {
            return true;
        }
        return false;
    }

    /**
     * Return the x values of this point.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y values of this point.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * set the x value of the point.
     *
     * @param applyX a new x value.
     */
    public void setX(double applyX) {
        this.x = applyX;
    }

    /**
     * set the y value of the point.
     *
     * @param applyY a new y value.
     */
    public void setY(double applyY) {
        this.y = applyY;
    }
}