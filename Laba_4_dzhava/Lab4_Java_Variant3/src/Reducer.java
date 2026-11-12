public interface Reducer<T> {
    T combine(T first, T second);
}