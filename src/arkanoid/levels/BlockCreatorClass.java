package arkanoid.levels;

import arkanoid.background.ColorsParser;
import arkanoid.background.ImageS;
import arkanoid.collision.Block;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class BlockCreatorClass implements BlockCreator {
    private String symbol;
    private int width;
    private int height;
    private int hitPoint;
    private Map<Integer, ImageS> imgBackground;
    private Map<Integer, Color> colorBackground;
    private Color stroke;

    /**
     * constructor.
     * <p>
     * initialized all the filed.
     */
    public BlockCreatorClass() {
        this.imgBackground = new TreeMap<>();
        this.colorBackground = new TreeMap<>();
        this.symbol = null;
        this.height = 0;
        this.width = 0;
        this.hitPoint = 0;
        this.stroke = null;
    }

    @Override
    public Block create(int xPos, int yPos) {
        if (this.colorBackground.size() != 0) {
            return new Block(xPos, yPos, this.width, this.height, colorBackground, this.stroke, this.hitPoint);
        }
        return new Block(xPos, yPos, width, height, hitPoint, imgBackground, this.stroke);
        ////check if both are null.
    }

    /**
     * enter values to filed from the text.
     *
     * @param s a string with information for the filed.
     */
    public void createFromText(String s) {

        String[] def = s.split(" ");
        for (int i = 1; i < def.length; i++) {
            String[] value = def[i].split(":");
            String key = value[0];
            String val = value[1];

            if (key.equals("symbol")) {
                symbol = val;
            }
            if (key.equals("width")) {
                width = Integer.parseInt(val);
            }
            if (key.equals("height")) {
                height = Integer.parseInt(val);
            }
            if (key.equals("hit_points")) {
                hitPoint = Integer.parseInt(val);
            }
            if (key.startsWith("fill-")) {
                addFactoryFillMapK(key, val);
            }
            if (key.startsWith("fill")) {
                addFactoryFillMap(val);
            }
            if (key.startsWith("stroke")) {
                this.setStroke(ColorsParser.colorFromString(val));
            }
        }
    }

    /**
     * enter to the relevant map the color or image.
     * the key will be the k at fill-k string and the value will be the color/image.
     *
     * @param key fill-k
     * @param val the image or color as string
     */
    private void addFactoryFillMapK(String key, String val) {
        if (val.startsWith("image")) {
            this.imgBackground.put(Integer.parseInt(key.substring(5, 6)), new ImageS(val, 0, 0));
        } else {
            this.colorBackground.put(Integer.parseInt(key.substring(5, 6)), ColorsParser.colorFromString(val));
        }
    }

    /**
     * set the stroke to a new stroke.
     *
     * @param colorFromString new stroke val.
     */
    private void setStroke(Color colorFromString) {
        this.stroke = colorFromString;
    }

    /**
     * create a relevant background and add it to the right map as key 1 if it doesn't exist.
     *
     * @param s string contain a background img/color.
     */
    private void addFactoryFillMap(String s) {
        if (s.startsWith("image")) {
            if (!this.imgBackground.containsKey(1)) {
                this.imgBackground.put(1, new ImageS(s, 0, 0));
            }
        } else {
            if (!this.colorBackground.containsKey(1)) {
                this.colorBackground.put(1, ColorsParser.colorFromString(s));
            }
        }
    }

    /**
     * complete the missing filed of the block from the default block.
     *
     * @param defaultBlock BlockCreatorClass with default filed.
     */
    public void completeArgs(BlockCreatorClass defaultBlock) {
        if (this.width == 0) {
            this.width = defaultBlock.getWidth();
        }

        if (this.height == 0) {
            this.height = defaultBlock.getHeight();
        }

        if (this.hitPoint == 0) {
            this.hitPoint = defaultBlock.getHitPoint();
        }

        if (this.width == 0) {
            this.width = defaultBlock.getWidth();
        }

        if (this.colorBackground == null && this.imgBackground == null) {
            this.colorBackground = defaultBlock.getColorBackground();
            this.imgBackground = defaultBlock.getImgBackground();
        }

        if (this.stroke == null && defaultBlock.getStroke() != null) {
            this.stroke = defaultBlock.getStroke();
        }

    }

    /**
     * @return the stroke.
     */
    private Color getStroke() {
        return this.stroke;
    }

    /**
     * @return the ImgBackground list.
     */
    private Map<Integer, ImageS> getImgBackground() {
        return this.imgBackground;
    }

    /**
     * @return the ColorBackground list.
     */
    private Map<Integer, Color> getColorBackground() {
        return this.colorBackground;
    }

    /**
     * @return the height of the block.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return the width of the block.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return the hitPoint of the block.
     */
    public int getHitPoint() {
        return this.hitPoint;
    }

    /**
     * @return the symbol of the block.
     */
    public String getSymbol() {
        return this.symbol;
    }

}
