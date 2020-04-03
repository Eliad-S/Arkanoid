package arkanoid.graphics;
import java.util.List;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-11
 */
public class Line {
    private Point point1;
    private Point point2;

    /**
     * constructor.
     * <p>
     * create two point type parameters and insert there values in Line's object fields.
     *
     * @param x1 double type.
     * @param y1 double type.
     * @param x2 double type.
     * @param y2 double type.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.point1 = new Point(x1, y1);
        this.point2 = new Point(x2, y2);
    }

    /**
     * constructor.
     * <p>
     * insert the 2 Point type given in Line's object fields.
     *
     * @param start Point type.
     * @param end   Point type.
     */
    public Line(Point start, Point end) {
        this.point1 = start;
        this.point2 = end;
    }

    /**
     * return the end point of the line.
     *
     * @return point2.
     */
    public Point getPoint1() {
        return this.point1;
    }

    /**
     * return the end point of the line.
     *
     * @return point2.
     */
    public Point getPoint2() {
        return this.point2;
    }

    /**
     * Return the length of the line.
     * calculate the length value by use the two point value in line.
     *
     * @return length double type.
     */
    public double length() {
        double length = this.point1.distance(this.point2);
        return length;
    }

    /**
     * Returns the middle point of the line.
     *
     * @return middle - point type, the middle of the line.
     */
    public Point middle() {
        double xMid = (this.point1.getX() + this.point2.getX()) / (double) 2;
        double yMid = (this.point1.getY() + this.point2.getY()) / (double) 2;
        Point middle = new Point(xMid, yMid);
        return middle;
    }

    /**
     * Returns the start point of the line.
     *
     * @return start point.
     */
    public Point start() {
        return this.point1;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end point.
     */
    public Point end() {
        return this.point2;
    }

    /**
     * return the incline of the line (m).
     *
     * @return m the incline value of the line.
     */
    public double getM() {
        double m = (this.point1.getY() - this.point2.getY())
                / (this.point1.getX() - this.point2.getX());
        return m;
    }

    /**
     * return the value of y when the the line cut its axis.
     *
     * @return lineCutY the value of y when the line cut its axis.
     */
    public double getCutY() {
        double lineCutY = this.point1.getY() - this.getM() * this.point1.getX();
        return lineCutY;
    }

    // Returns true if the lines intersect, false otherwise

    /**
     * return true if our line and the other line are intersected.
     *
     * @param other Line type.
     * @return boolean true or false.
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.

    /**
     * return the point value of the intersection point between both lines.
     * <p>
     * this method analyze all sort of lines Appearance and return the intersection point
     * value if is existing else return null.
     *
     * @param other Line type.
     * @return Point value (can return null).
     */
    public Point intersectionWith(Line other) {
        if (this.point1.getX() == this.point2.getX()) {
            if (other.point1.getX() == other.point2.getX()) {
                if (this.point1.equals(other.point1) || this.point1.equals(other.point2)) {
                    return this.point1;
                }
                if (this.point2.equals(other.point1) || this.point1.equals(other.point2)) {
                    return this.point2;
                }
                return null;
            } else {
                double mOther = other.getM();
                double otherCutY = other.getCutY();
                double xIntersect = this.point1.getX();
                double yIntersect = mOther * this.point1.getX() + otherCutY;
                if (isLinesInRange(other, xIntersect, yIntersect)) {
                    return new Point(xIntersect, yIntersect);
                } else {
                    return null;
                }
            }
        } else {
            if (other.point1.getX() == other.point2.getX()) {
                double mLine = this.getM();
                double lineCutY = this.getCutY();
                double xIntersect = other.point1.getX();
                double yIntersect = mLine * this.point1.getX() + lineCutY;
                if (isLinesInRange(other, xIntersect, yIntersect)) {
                    return new Point(xIntersect, yIntersect);
                } else {
                    return null;
                }
            }
        }


        double mLine = this.getM();
        double mOther = other.getM();
        double lineCutY = this.getCutY();
        double otherCutY = other.getCutY();
        //The lines are united.
        if (mLine == mOther) {
            //the lines are parallel.
            if (lineCutY != otherCutY) {
                return null;
            } else {
                //check if the line our merge in some point.
                if (this.point1.equals(other.point1) || this.point1.equals(other.point2)) {
                    return this.point1;
                }
                if (this.point2.equals(other.point1) || this.point1.equals(other.point2)) {
                    return this.point2;
                }
                return null;
            }

        } else {
            //the line has'nt the same m.
            double xIntersect = (lineCutY - otherCutY) / (mOther - mLine);
            double yIntersect = mLine * xIntersect + lineCutY;
            //check if the intersect point is in the range of our line.
            if (isLinesInRange(other, xIntersect, yIntersect)) {
                return new Point(xIntersect, yIntersect);
            } else {
                return null;
            }
        }
    }

    /**
     * check if the point is in the rang of both lines.
     * the method get a potential x and y value of an intersection point.
     * check each line and check if the point can be found on the line,
     * by checking if the x value and the y value of the point is between both x value and both y value
     * of the line's points.
     *
     * @param other      Line type.
     * @param xIntersect the x value of the potential intersect point.
     * @param yIntersect the y value of the potential intersect point.
     * @return boolean true if in range, false if is'nt.
     */
    public boolean isLinesInRange(Line other, double xIntersect, double yIntersect) {
        double deviation = 0.000001;
        return (((this.point1.getX() + deviation >= xIntersect && this.point2.getX() - deviation <= xIntersect)
                || (this.point1.getX() - deviation <= xIntersect && this.point2.getX() + deviation >= xIntersect))
                && ((this.point1.getY() + deviation >= yIntersect && this.point2.getY() - deviation <= yIntersect)
                || (this.point1.getY() - deviation <= yIntersect && this.point2.getY() + deviation >= yIntersect))
                && ((other.point1.getX() + deviation >= xIntersect && other.point2.getX() - deviation <= xIntersect)
                || (other.point1.getX() <= xIntersect && other.point2.getX() + deviation  >= xIntersect))
                && ((other.point1.getY() + deviation >= yIntersect && other.point2.getY() - deviation  <= yIntersect)
                || (other.point1.getY() - deviation <= yIntersect && other.point2.getY() + deviation >= yIntersect)));
    }

    // equals -- return true is the lines are equal, false otherwise

    /**
     * return true if both point our equals else true.
     *
     * @param other Line type.
     * @return boolean true or false.
     */
    public boolean equals(Line other) {
        if ((this.point1.equals(other.point1) && this.point2.equals(other.point2))
                || (this.point1.equals(other.point2) && this.point2.equals(other.point1))) {
            return true;
        }
        return false;
    }

    /**
     * find the intersection point with the rectangle.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect a rectangle.
     * @return a list with the intersection point withe the line and the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectP = rect.intersectionPoints(this);
        this.filterPoints(intersectP);
        return closestIntersectOfPointsList(intersectP);

    }

    /**
     * find the relevant of the point to the line direction.
     * the method is checking if the points in the array given are in the line direction,
     * from the start point to the end point.
     *
     * @param intersectP an array list with points.
     */
    public void filterPoints(List<Point> intersectP) {
        double startPX = this.point1.getX();
        double startPY = this.point1.getY();
        double endPX = this.point2.getX();
        double endPY = this.point2.getY();
        for (Point p : intersectP) {
            if (p == null) {
                continue;
            }
            double xP = p.getX();
            double yP = p.getY();
            if (((xP > startPX) && ((startPX - endPX) < 0)) || ((xP < startPX) && ((startPX - endPX) > 0))
                    || ((yP > startPY) && ((startPY - endPY) < 0)) || ((yP < startPY) && ((startPY - endPY) > 0))) {
                continue;
            } else {
                intersectP.set(intersectP.indexOf(p), null);
            }

        }
    }

    /**
     * the method receive a list with intersection point with the line,
     * and return the point that is the most closest to the start of the line.
     * if there are no points in the array return null.
     *
     * @param intersectP an array list with points that in the line direction.
     * @return the point that is most closer to the start point of the line.
     */
    public Point closestIntersectOfPointsList(List<Point> intersectP) {
        Point startP = this.getPoint1();
        Point closest = null;
        double distance = 0;
        int flag = 0;

        for (Point p : intersectP) {
            if (p == null) {
                continue;
            }
            if (closest == null) {
                closest = p;
                distance = startP.distance(p);
                flag = 1;
            } else {
                if (p.distance(startP) < distance) {
                    closest = p;
                    distance = p.distance(startP);
                }
            }
        }
        if (flag == 0) {
            return null;
        } else {
            return closest;
        }
    }
}