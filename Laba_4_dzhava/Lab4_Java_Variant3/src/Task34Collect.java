import java.util.List;

public class Task34Collect {
    public static <T, R> R collect(
            List<T> source,
            ResultFactory<R> factory,
            ResultWriter<R, T> writer) {
        R result = factory.create();
        for (T item : source) {
            writer.add(result, item);
        }
        return result;
    }
}