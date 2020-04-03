package arkanoid.background;

import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class ImageS implements Sprite {
    private String s;
    private int xPos;
    private int yPos;
    private BufferedImage img;

    /**
     * constructor.
     * <p>
     *
     * @param s    string contain the img path.
     * @param xPos the x value ew want to draw the img from.
     * @param yPos the y value ew want to draw the img from.
     */
    public ImageS(String s, int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.s = s;
        int index = s.indexOf(")");
        String nameImg = s.substring(6, index);
        // load the image data into an java.awt.Image object
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(nameImg);
            this.img = ImageIO.read(is);
        } catch (IOException ex) {
            System.err.println("Failed reading");
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {

        d.drawImage(xPos, yPos, this.img); // draw the image at location 10, 20.

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * set the xPos val.
     *
     * @param x the new val.
     */
    public void setXPos(int x) {
        this.xPos = x;
    }

    /**
     * set the yPos val.
     *
     * @param y the new val.
     */
    public void setYPos(int y) {
        this.yPos = y;
    }

}
