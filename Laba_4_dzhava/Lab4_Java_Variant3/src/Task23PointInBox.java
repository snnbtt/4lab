public class Task23PointInBox {
    public static void putPoint(Box<? super Point3D> box, Point3D point) {
        if (!box.isEmpty()) {
            throw new IllegalStateException("Для точки нужна пустая коробка");
        }
        box.put(point);
    }
}