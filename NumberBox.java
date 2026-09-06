package lab02;

import java.util.List;

/** A wrapper restricted to values that are subclasses of Number. */
public class NumberBox<T extends Number> {
    private T item;

    public NumberBox(T item) {
        this.item = item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    /** Adds another number to the wrapped value. */
    public double add(Number other) {
        return item.doubleValue() + other.doubleValue();
    }

    /** Calculates the sum of a list containing any Number subtype. */
    public static double sum(List<? extends Number> numbers) {
        double total = 0.0;
        for (Number number : numbers) {
            total += number.doubleValue();
        }
        return total;
    }
}
