public class Task21BoxAndStorage {
    public static void showBox(Box<Integer> box) {
        Integer value = box.take();
        System.out.println("Извлечено из коробки: " + value);
    }

    public static <T> void showStorage(Storage<T> storage, T alternative, String label) {
        System.out.println(label + storage.getOrElse(alternative));
    }
}