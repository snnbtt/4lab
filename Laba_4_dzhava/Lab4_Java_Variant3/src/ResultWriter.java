public interface ResultWriter<R, T> {
    void add(R result, T value);
}