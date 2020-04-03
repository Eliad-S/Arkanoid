package arkanoid.listeners;
/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-05-2
 */
public interface HitNotifier {
    /**
     *
     * Add hl as a listener to hit events.
     * @param hl a listener.
     */
    void addHitListener(HitListener hl);
    /**
     *
     * Remove hl from the list of listeners to hit events.
     * @param hl a listener.
     */
    void removeHitListener(HitListener hl);
}