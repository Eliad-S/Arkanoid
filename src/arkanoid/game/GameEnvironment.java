package arkanoid.game;

import arkanoid.collision.Collidable;
import arkanoid.collision.CollisionInfo;
import arkanoid.graphics.Line;
import arkanoid.graphics.Point;
import arkanoid.graphics.Rectangle;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */

public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * constructor.
     * <p>
     * initialize the collidable list array.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment..
     *
     * @param c collidable type
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * we are getting the ball trajectory by a line and return the information
     * about the closest collision that is going to occur, the collision point
     * and the collidable object.
     * if there is no points intersection existing return null
     *
     * @param trajectory the ball movement direction from the start point.
     * @return CollisionInfo about the intersection if it existing.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Point> closestPArray = new ArrayList<Point>();
        Rectangle r;
        for (Collidable c : this.getCollidables()) {
            r = c.getCollisionRectangle();
            closestPArray.add(trajectory.closestIntersectionToStartOfLine(r));
        }
        Point closestIntersect = trajectory.closestIntersectOfPointsList(closestPArray);
        if (closestIntersect == null) {
            return null;
        } else {
            int index = closestPArray.indexOf(closestIntersect);
            CollisionInfo c = new CollisionInfo(closestIntersect, this.getCollidables().get(index));
            return c;
        }

    }

    /**
     * return the collidable list.
     *
     * @return the collidable list.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}