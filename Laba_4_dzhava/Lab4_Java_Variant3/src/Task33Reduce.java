import java.util.List;

public class Task33Reduce {
    // Начальное значение позволяет безопасно обработать даже пустой список.
    public static <T> T reduce(List<T> source, T identity, Reducer<T> reducer) {
        T result = identity;
        for (T item : source) {
            result = reducer.combine(result, item);
        }
        return result;
    }
}