package lab02;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        demonstratePrintableList();
        demonstrateNumberBox();
        demonstratePipeline();
        demonstrateWildcards();
        System.out.println("All Lab 02 checks passed.");
    }

    private static void demonstratePrintableList() {
        System.out.println("Exercise 1 - PrintableList:");
        String[] courses = {"SE411", "Java Generics", "Git"};
        PrintableList<String> printableList = new PrintableList<>(courses);
        printableList.printAll();
    }

    private static void demonstrateNumberBox() {
        System.out.println("\nExercise 2 - NumberBox:");

        NumberBox<Integer> integerBox = new NumberBox<>(10);
        integerBox.setItem(15);
        System.out.println("Integer item: " + integerBox.getItem());
        System.out.println("Integer item + 5: " + integerBox.add(5));

        NumberBox<Double> doubleBox = new NumberBox<>(2.5);
        doubleBox.setItem(4.5);
        System.out.println("Double item: " + doubleBox.getItem());
        System.out.println("Double item + 1.5: " + doubleBox.add(1.5));

        double sum = NumberBox.sum(Arrays.asList(1, 2.5, 3L));
        System.out.println("Mixed-number sum: " + sum);

        check(integerBox.getItem() == 15, "Integer NumberBox test failed");
        check(Double.compare(doubleBox.getItem(), 4.5) == 0,
                "Double NumberBox test failed");
        check(Double.compare(sum, 6.5) == 0, "NumberBox sum test failed");
    }

    private static void demonstratePipeline() {
        System.out.println("\nExercise 3 - Pipeline:");

        Pipeline<String, String> base = Pipeline.<String>start()
                .add(String::trim);
        Pipeline<String, String> upperCase = base.add(String::toUpperCase);
        Pipeline<String, Integer> length = upperCase.add(String::length);

        String cleaned = upperCase.execute("  java generics  ");
        int characterCount = length.execute("  java generics  ");

        System.out.println("Same-type result: " + cleaned);
        System.out.println("Type-changing result: " + characterCount);

        check("JAVA GENERICS".equals(cleaned), "Same-type pipeline test failed");
        check(characterCount == 13, "Type-changing pipeline test failed");
    }

    private static void demonstrateWildcards() {
        System.out.println("\nExercise 4 - Wildcards:");
        List<String> words = Arrays.asList("one", "two", "three");
        printList(words);

        double total = sumNumbers(Arrays.asList(10, 20.5, 30L));
        System.out.println("Wildcard sum: " + total);
        check(Double.compare(total, 60.5) == 0, "Wildcard sum test failed");
    }

    /** Prints a list containing values of any type. */
    public static void printList(List<?> items) {
        for (Object item : items) {
            System.out.println(item);
        }
    }

    /** Sums a list containing Number or any subclass of Number. */
    public static double sumNumbers(List<? extends Number> numbers) {
        double total = 0.0;
        for (Number number : numbers) {
            total += number.doubleValue();
        }
        return total;
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
