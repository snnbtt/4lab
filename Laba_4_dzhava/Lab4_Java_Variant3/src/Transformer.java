public interface Transformer<T, P> {
    P apply(T value);
}