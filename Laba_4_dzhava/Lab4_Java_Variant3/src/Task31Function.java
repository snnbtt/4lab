import java.util.ArrayList;
import java.util.List;

public class Task31Function {
    public static <T, P> List<P> map(List<T> source, Transformer<T, P> transformer) {
        List<P> result = new ArrayList<>();
        for (T item : source) {
            result.add(transformer.apply(item));
        }
        return result;
    }
}