package arkanoid.graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor.
     * <p>
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the point located in the upperLeft place of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * constructor.
     * <p>
     * Create a new rectangle with location and width/height.
     *
     * @param x      the x value of the point located in the upperLeft place of the rectangle.
     * @param y      the y value of the point located in the upperLeft place of the rectangle.
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     */
      public Rectangle(double x, double y, double width, double height) {
        Point upperLeftP = new Point(x, y);
        this.upperLeft = upperLeftP;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the rectangle
     * and the specified line.
     *
     * @param line Line type.
     * @return intersectPoint a list with the possible intersection points with the rectangle.
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line./////check return.
    public java.util.List<Point> intersectionPoints(Line line) {
        double xP = this.upperLeft.getX();
        double yP = this.upperLeft.getY();
        double widthh = this.width;
        double heightt = this.height;
        List<Point> intersectPoint = new ArrayList<Point>();
        Line up = new Line(xP, yP, xP + widthh, yP);
        Line down = new Line(xP, yP + heightt, xP + widthh, yP + heightt);
        Line right = new Line(xP + widthh, yP, xP + widthh, yP + heightt);
        Line left = new Line(xP, yP, xP, yP + heightt);
        intersectPoint.add(up.intersectionWith(line));
        intersectPoint.add(down.intersectionWith(line));
        intersectPoint.add(right.intersectionWith(line));
        intersectPoint.add(left.intersectionWith(line));
        return intersectPoint;

    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * return the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * set the upperLeft point of the rectangle to a new value.
     *
     * @param x the upper-left' x value we want to set to the rectangle.
     * @param y the upper-left' y value we want to set to the rectangle.
     */
    public void setUpperLeft(double x, double y) {
        Point newUpperLeft = new Point(x, y);
        this.upperLeft = newUpperLeft;
    }
}