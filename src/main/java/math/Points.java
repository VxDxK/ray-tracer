package math;

public class Points {
    public static double[] toArray(Point point){
        return new double[]{point.getX(), point.getY(), point.getZ()};
    }
}
