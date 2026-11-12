import java.util.ArrayList;
import java.util.List;

public class Task32Filter {
    public static <T> List<T> filter(List<T> source, PredicateRule<T> rule) {
        List<T> result = new ArrayList<>();
        for (T item : source) {
            if (rule.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}