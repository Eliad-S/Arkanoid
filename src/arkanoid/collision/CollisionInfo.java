package arkanoid.collision;
import arkanoid.graphics.Point;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     * <p>
     * give values to the object fields.
     *
     * @param collisionPoint the point at which the collidable get intersect with.
     * @param collisionObject a collidable type that get intersected.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}