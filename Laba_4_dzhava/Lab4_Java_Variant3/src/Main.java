import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("ЛАБОРАТОРНАЯ РАБОТА №4. ВАРИАНТ 3");
        System.out.println("Обобщённые типы, параметризация и обобщённые методы");

        // Задание 1.1 — обобщённая коробка.
        System.out.println("\\n--- Задание 1.1. Обобщённая коробка ---");
        Box<Integer> integerBox = new Box<>();
        integerBox.put(3);
        System.out.println(integerBox);
        Task21BoxAndStorage.showBox(integerBox);
        System.out.println("После извлечения: " + integerBox);

        // Задание 1.2 — неизменяемое хранилище.
        System.out.println("\\n--- Задание 1.2. Без null ---");
        Task21BoxAndStorage.showStorage(new Storage<Integer>(null), 0,
                "Числовое хранилище null, альтернатива 0: ");
        Task21BoxAndStorage.showStorage(new Storage<Integer>(99), -1,
                "Числовое хранилище 99, альтернатива -1: ");
        Task21BoxAndStorage.showStorage(new Storage<String>(null), "default",
                "Строковое хранилище null, альтернатива default: ");
        Task21BoxAndStorage.showStorage(new Storage<String>("hello"), "hello world",
                "Строковое хранилище hello, альтернатива hello world: ");

        // Задание 2.3 — коробка с точкой.
        System.out.println("\\n--- Задание 2.3. Начало отсчёта ---");
        Box<Object> pointBox = new Box<>();
        Task23PointInBox.putPoint(pointBox, new Point3D(1, 2, 3));
        System.out.println("Коробка с трёхмерной точкой: " + pointBox);

        // Задание 3.1 — преобразование.
        System.out.println("\\n--- Задание 3.1. Функция ---");
        List<String> words = Arrays.asList("qwerty", "asdfg", "zx");
        List<Integer> lengths = Task31Function.map(words, new Transformer<String, Integer>() {
            public Integer apply(String value) { return value.length(); }
        });
        System.out.println("Длины строк: " + lengths);

        List<Integer> numbers = Arrays.asList(1, -3, 7);
        List<Integer> absolute = Task31Function.map(numbers, new Transformer<Integer, Integer>() {
            public Integer apply(Integer value) { return value < 0 ? -value : value; }
        });
        System.out.println("Числа после обработки: " + absolute);

        List<int[]> arrays = new ArrayList<>();
        arrays.add(new int[]{4, 9, 2});
        arrays.add(new int[]{-5, 0, 12});
        arrays.add(new int[]{7, 7, 3});
        List<Integer> maxima = Task31Function.map(arrays, new Transformer<int[], Integer>() {
            public Integer apply(int[] array) {
                int max = array[0];
                for (int i = 1; i < array.length; i++) {
                    if (array[i] > max) max = array[i];
                }
                return max;
            }
        });
        System.out.println("Максимумы массивов: " + maxima);

        // Задание 3.2 — фильтрация.
        System.out.println("\\n--- Задание 3.2. Фильтр ---");
        System.out.println("Строки длиной не менее 3: " +
                Task32Filter.filter(words, new PredicateRule<String>() {
                    public boolean test(String value) { return value.length() >= 3; }
                }));
        System.out.println("Неположительные числа: " +
                Task32Filter.filter(numbers, new PredicateRule<Integer>() {
                    public boolean test(Integer value) { return value <= 0; }
                }));
        List<int[]> arraysWithoutPositive = Task32Filter.filter(arrays, new PredicateRule<int[]>() {
            public boolean test(int[] array) {
                for (int value : array) {
                    if (value > 0) return false;
                }
                return true;
            }
        });
        System.out.println("Количество массивов без положительных элементов: "
                + arraysWithoutPositive.size());

        // Задание 3.3 — сведение к одному значению.
        System.out.println("\\n--- Задание 3.3. Сокращение ---");
        String joined = Task33Reduce.reduce(words, "", new Reducer<String>() {
            public String combine(String first, String second) { return first + second; }
        });
        System.out.println("Объединённая строка: " + joined);

        Integer sum = Task33Reduce.reduce(numbers, 0, new Reducer<Integer>() {
            public Integer combine(Integer first, Integer second) { return first + second; }
        });
        System.out.println("Сумма чисел: " + sum);

        List<List<Integer>> nested = new ArrayList<>();
        nested.add(Arrays.asList(1, 2, 3));
        nested.add(Arrays.asList(4, 5));
        nested.add(Arrays.asList(6));
        List<Integer> sizes = Task31Function.map(nested, new Transformer<List<Integer>, Integer>() {
            public Integer apply(List<Integer> value) { return value.size(); }
        });
        Integer totalCount = Task33Reduce.reduce(sizes, 0, new Reducer<Integer>() {
            public Integer combine(Integer first, Integer second) { return first + second; }
        });
        System.out.println("Общее количество элементов во вложенных списках: " + totalCount);
        System.out.println("Проверка пустого списка: " +
                Task33Reduce.reduce(new ArrayList<Integer>(), 0, new Reducer<Integer>() {
                    public Integer combine(Integer first, Integer second) { return first + second; }
                }));

        // Задание 3.4 — создание результирующих коллекций.
        System.out.println("\\n--- Задание 3.4. Коллекционирование ---");
        List<Integer> signsSource = Arrays.asList(1, -3, 7);
        List<List<Integer>> bySign = Task34Collect.collect(
                signsSource,
                new ResultFactory<List<List<Integer>>>() {
                    public List<List<Integer>> create() {
                        List<List<Integer>> result = new ArrayList<>();
                        result.add(new ArrayList<Integer>());
                        result.add(new ArrayList<Integer>());
                        return result;
                    }
                },
                new ResultWriter<List<List<Integer>>, Integer>() {
                    public void add(List<List<Integer>> result, Integer value) {
                        if (value > 0) result.get(0).add(value);
                        else if (value < 0) result.get(1).add(value);
                    }
                });
        System.out.println("Положительные и отрицательные: " + bySign);

        List<String> wordsForGroups = Arrays.asList("qwerty", "asdfg", "zx", "qw");
        Map<Integer, List<String>> grouped = Task34Collect.collect(
                wordsForGroups,
                new ResultFactory<Map<Integer, List<String>>>() {
                    public Map<Integer, List<String>> create() { return new HashMap<>(); }
                },
                new ResultWriter<Map<Integer, List<String>>, String>() {
                    public void add(Map<Integer, List<String>> result, String value) {
                        int length = value.length();
                        if (!result.containsKey(length)) {
                            result.put(length, new ArrayList<String>());
                        }
                        result.get(length).add(value);
                    }
                });
        System.out.println("Строки по длине: " + grouped);

        List<String> repeatedWords = Arrays.asList("qwerty", "asdfg", "qwerty", "qw");
        Set<String> unique = Task34Collect.collect(
                repeatedWords,
                new ResultFactory<Set<String>>() {
                    public Set<String> create() { return new HashSet<>(); }
                },
                new ResultWriter<Set<String>, String>() {
                    public void add(Set<String> result, String value) { result.add(value); }
                });
        System.out.println("Набор без повторений: " + unique);

        System.out.println("\\nРабота всех заданий завершена.");
    }
}