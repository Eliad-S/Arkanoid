package arkanoid.collision;

import arkanoid.background.ImageS;
import arkanoid.ball.Ball;
import arkanoid.ball.Velocity;
import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import arkanoid.graphics.Point;
import arkanoid.graphics.Rectangle;
import arkanoid.listeners.HitListener;
import arkanoid.listeners.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private Color color;
    private Color stroke;
    private int targetHit;
    private List<HitListener> hitListeners;
    private Map<Integer, ImageS> imgBackground;
    private Map<Integer, Color> colorBackground;


    /**
     * constructor.
     * <p>
     * insert values in our filed.
     * Create a new block - rectangle with location and width/height, color and target hits.
     *
     * @param upperLeft the point located in the upperLeft place of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     * @param targetHit the level of the block.
     * @param color     .
     */
    public Block(Point upperLeft, double width, double height, Color color, int targetHit) {
        this.block = new Rectangle(upperLeft, width, height);
        this.targetHit = targetHit;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorBackground = null;
        this.imgBackground = null;
    }

    /**
     * constructor.
     * <p>
     * Create a new block - rectangle with location and width/height, color and target hits.
     *
     * @param x         the x value of the point located in the upperLeft place of the rectangle.
     * @param y         the y value of the point located in the upperLeft place of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     * @param images    images background.
     * @param targetHit the level of the block.
     * @param stroke    color for stroke.
     */
    public Block(double x, double y, double width, double height, int targetHit,
                 Map<Integer, ImageS> images, Color stroke) {
        this.block = new Rectangle(x, y, width, height);
        this.imgBackground = images;
        this.targetHit = targetHit;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorBackground = null;
        this.color = Color.black;
        this.stroke = stroke;
    }

    /**
     * constructor.
     * <p>
     * Create a new block - rectangle with location and width/height, color and target hits.
     *
     * @param x         the x value of the point located in the upperLeft place of the rectangle.
     * @param y         the y value of the point located in the upperLeft place of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     * @param colors    colors background.
     * @param targetHit the level of the block.
     * @param stroke    color for stroke.
     */
    public Block(double x, double y, double width, double height, Map<Integer,
            Color> colors, Color stroke, int targetHit) {
        this.block = new Rectangle(x, y, width, height);
        this.colorBackground = colors;
        this.targetHit = targetHit;
        this.hitListeners = new ArrayList<HitListener>();
        this.imgBackground = null;
        this.color = Color.black;
        this.stroke = stroke;
    }


    /**
     * constructor.
     * <p>
     * Create a new block - rectangle with location and width/height, color and target hits.
     *
     * @param x         the x value of the point located in the upperLeft place of the rectangle.
     * @param y         the y value of the point located in the upperLeft place of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     * @param color     color for the block.
     * @param targetHit the level of the block.
     */
    public Block(double x, double y, double width, double height, Color color, int targetHit) {
        this.block = new Rectangle(x, y, width, height);
        this.color = color;
        this.targetHit = targetHit;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorBackground = null;
        this.imgBackground = null;
    }


    /**
     * constructor.
     * <p>
     * insert values in our filed.
     * Create a new block - rectangle with location and width/height, color and target hits.
     *
     * @param upperLeft the point located in the upperLeft place of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     * @param color     .
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.targetHit = 1;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorBackground = null;
        this.imgBackground = null;
    }

    /**
     * constructor.
     * <p>
     * Create a new block - rectangle with location and width/height, color and target hits.
     *
     * @param x      the x value of the point located in the upperLeft place of the rectangle.
     * @param y      the y value of the point located in the upperLeft place of the rectangle.
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color  color for the block.
     */
    public Block(double x, double y, double width, double height, Color color) {
        this.block = new Rectangle(x, y, width, height);
        this.color = color;
        this.targetHit = 1;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorBackground = null;
        this.imgBackground = null;
    }

    /**
     * return the rectangle(block).
     *
     * @return the rectangle type in our class (block).
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * check the place that have benn collide with our block and return a new velocity
     * by the results.
     *
     * @param collisionPoint  the point that have been collide with the block.
     * @param currentVelocity a velocity given.
     * @param hitter          the ball that hit the block.
     * @return the new velocity value.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.targetHit != 0) {
            this.targetHit -= 1;
        }
        this.notifyHit(hitter);

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double upperLeftX = this.block.getUpperLeft().getX();
        double upperLeftY = this.block.getUpperLeft().getY();
        if (x > upperLeftX && x < upperLeftX + this.block.getWidth()) {
            dy = -dy;
        }
        if (y > upperLeftY && y < upperLeftY + this.block.getHeight()) {
            dx = -dx;
        }
        if (((x == upperLeftX || x == upperLeftX + this.block.getWidth()) && y == upperLeftY)
                || (x == upperLeftX || x == upperLeftX + this.block.getWidth())
                && y == upperLeftY + this.block.getHeight()) {
            dx = -dx;
            dy = -dy;
        }

        return new Velocity(dx, dy);

    }

    /**
     * draw the block type on the surface given.
     *
     * @param d a DrawSurface type.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.block.getUpperLeft().getX();
        int y = (int) this.block.getUpperLeft().getY();
        int width = (int) this.block.getWidth();
        int height = (int) this.block.getHeight();
        if (this.imgBackground != null) {
            ImageS imageS;
            if (this.imgBackground.containsKey(this.targetHit)) {
                imageS = this.imgBackground.get(this.targetHit);
            } else {
                imageS = this.imgBackground.get(1);
            }
            imageS.setXPos(x);
            imageS.setYPos(y);
            imageS.drawOn(d);
        } else {
            if (this.colorBackground != null) {
                if (this.colorBackground.containsKey(this.targetHit)) {
                    d.setColor(this.colorBackground.get(this.targetHit));
                    d.fillRectangle(x, y, width, height);
                } else {
                    d.setColor(this.colorBackground.get(1));
                    d.fillRectangle(x, y, width, height);
                }
            } else {
                //fill default color.
                d.setColor(this.color);
                d.fillRectangle(x, y, width, height);
            }
        }

        if (this.stroke != null) {
            d.setColor(this.stroke);
            d.drawRectangle(x, y, width, height);
        }
    }

    @Override
    public void timePassed() {

    }

    /**
     * Add this block to the game.
     *
     * @param game Game type.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * remove this block to the game.
     *
     * @param game Game type.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.getHitListeners().add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.getHitListeners().remove(hl);
    }

    /**
     * return the HitListeners list of the block.
     *
     * @return HitListeners list of listeners.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * return the TargetHit of the ball.
     *
     * @return TargetHit .
     */
    public int getTargetHit() {
        return this.targetHit;
    }

    /**
     * inform all the listener about the hitting and the ball that hit it.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Return the targetHit casting to string.
     *
     * @return the targetHit.
     */
    public String getHitPoints() {
        return Integer.toString(this.getTargetHit());
    }

    /**
     * return the width of the block.
     *
     * @return the width.
     */
    public double getWidth() {
        return this.block.getWidth();
    }


}
