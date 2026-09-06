package lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** A generic list whose items can be printed in insertion order. */
public class PrintableList<T> {
    private final List<T> items;

    public PrintableList(T[] items) {
        this.items = new ArrayList<>(Arrays.asList(items));
    }

    public void printAll() {
        for (T item : items) {
            System.out.println(item);
        }
    }
}
