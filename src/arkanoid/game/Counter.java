package arkanoid.game;

/**
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-03-26
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     * without a number to initialized.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * constructor.
     * with a number to initialized.
     *
     * @param number .
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * add number to current count.
     *
     * @param number .
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number .
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     * @return the current counter.
     */
    public int getValue() {
        return this.counter;
    }
    /**
     * set the counter to the number input.
     * @param num .
     */
    public void setCounter(int num) {
        this.counter = num;
    }

}