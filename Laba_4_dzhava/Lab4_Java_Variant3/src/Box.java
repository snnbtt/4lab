public class Box<T> {
    private T value;

    public Box() {
        this.value = null;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public void put(T value) {
        if (!isEmpty()) {
            throw new IllegalStateException("Коробка уже занята");
        }
        this.value = value;
    }

    public T take() {
        T result = value;
        value = null;
        return result;
    }

    @Override
    public String toString() {
        return isEmpty() ? "Коробка пуста" : "Коробка содержит: " + value;
    }
}