package arkanoid.openingMenu;

import arkanoid.animation.Animation;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 * @param <T> .
 */
public interface Menu<T> extends Animation {
    /**
     * add a selection option to the menu with a relevant task.
     *
     * @param key       .
     * @param message   .
     * @param returnVal .
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get the task.
     *
     * @return T .
     */
    T getStatus();

    /**
     * set the stop condition of the loop  .
     *
     * @param b true/false.
     */
    void setStop(boolean b);

    /**
     * add a selection option to the menu with a relevant task.
     *
     * @param key .
     * @param message .
     * @param subMenu .
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}