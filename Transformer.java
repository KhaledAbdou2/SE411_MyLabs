package lab02;

/** Converts a value of type T into a value of type R. */
@FunctionalInterface
public interface Transformer<T, R> {
    R transform(T input);
}
