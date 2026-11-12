public class Storage<T> {
    private final T value;

    public Storage(T value) {
        this.value = value;
    }

    public T getOrElse(T alternative) {
        return value == null ? alternative : value;
    }

    @Override
    public String toString() {
        return "Хранилище{" + value + "}";
    }
}