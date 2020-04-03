package arkanoid.openingMenu;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 * A task is something that needs to happen, or something that we can run() and return a value.
 * @param <T> .
 */
public interface Task<T> {
    /**
     * make some function.
     * @return T type.
     */
    T run();
}