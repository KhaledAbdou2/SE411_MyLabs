package lab02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An immutable sequence of transformations from input type T to output type R.
 * Adding a transformer returns a new pipeline with an updated output type.
 */
public final class Pipeline<T, R> {
    private final List<Transformer<?, ?>> transformers;

    private Pipeline(List<Transformer<?, ?>> transformers) {
        this.transformers = Collections.unmodifiableList(transformers);
    }

    /** Creates an empty pipeline whose output initially has the input type. */
    public static <T> Pipeline<T, T> start() {
        return new Pipeline<>(new ArrayList<>());
    }

    /**
     * Appends a transformation and returns a new pipeline. The original
     * pipeline remains unchanged and can be reused.
     */
    public <V> Pipeline<T, V> add(
            Transformer<? super R, ? extends V> transformer) {
        List<Transformer<?, ?>> updated = new ArrayList<>(transformers);
        updated.add(transformer);
        return new Pipeline<>(updated);
    }

    /** Applies every transformation in the order in which it was added. */
    public R execute(T input) {
        Object current = input;
        for (Transformer<?, ?> transformer : transformers) {
            current = apply(transformer, current);
        }
        return castOutput(current);
    }

    @SuppressWarnings("unchecked")
    private static Object apply(Transformer<?, ?> transformer, Object value) {
        return ((Transformer<Object, Object>) transformer).transform(value);
    }

    @SuppressWarnings("unchecked")
    private R castOutput(Object value) {
        return (R) value;
    }
}
