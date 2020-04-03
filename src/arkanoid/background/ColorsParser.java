package arkanoid.background;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class ColorsParser {

    /**
     * parse color definition and return the specified color.
     * @param s a string that describe a color.
     * @return a color type.
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.startsWith("color(RGB")) {
            int index = s.indexOf(")");
            String args = s.substring(10, index);
            String[] splitArgs = args.split(",");
            int r = Integer.parseInt(splitArgs[0]);
            int q = Integer.parseInt(splitArgs[1]);
            int b = Integer.parseInt(splitArgs[2]);
            return new Color(r, q, b);
        } else {
            int index = s.indexOf(")");
            String col = s.substring(6, index);
            Color color;
            try {
                Field field = Color.class.getField(col);
                color = (Color) field.get(null);
            } catch (Exception e) {
                color = null; // Not defined
            }
            return color;
        }
    }
}
