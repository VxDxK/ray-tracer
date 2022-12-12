package math;

public class Points {
    public static double[] toArray(Point point){
        return new double[]{point.getX(), point.getY(), point.getZ()};
    }
    public static Point scale(Point point, double t){
        return new Point(point.getX() * t, point.getY() * t, point.getZ() * t);
    }
}
