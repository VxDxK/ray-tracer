package objects;

import math.Point;
import math.Points;
import math.Ray;
import math.Vectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AABB {
    private Point min;
    private Point max;

    public AABB() {
        this(new Point(), new Point());
    }

    public AABB(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public boolean hit(Ray r, double tMin, double tMax){
        double t_min = tMin;
        double t_max = tMax;
        double[] minPointArray = Points.toArray(min);
        double[] maxPointArray = Points.toArray(max);
        double[] directionVectorArray = Vectors.toArray(r.getDirection());
        double[] originPointArray = Points.toArray(r.getOrigin());
        for (int a = 0; a < 3; a++){
            double invD = 1f/directionVectorArray[a];
            double t0 = (minPointArray[a] - originPointArray[a]) * invD;
            double t1 = (maxPointArray[a] - originPointArray[a]) * invD;

            if(invD < 0f){
                double tmp = t1;
                t1 = t0;
                t0 = tmp;
            }

            t_min = max(t0, t_min);
            t_max = min(t1, t_max);

            if(t_max <= t_min){
                return false;
            }
        }
        return true;
    }

    public AABB setMin(Point min) {
        this.min = min;
        return this;
    }

    public AABB setMax(Point max) {
        this.max = max;
        return this;
    }

    public AABB set(AABB aabb){
        this.min = aabb.getMin();
        this.max = aabb.getMax();
        return this;
    }

    public Point getMin() {
        return min;
    }

    public Point getMax() {
        return max;
    }

    public static AABB surroundingBox(AABB box0, AABB box1){
        Point small = new Point(min(box0.getMin().getX(), box1.getMin().getX()),
                min(box0.getMin().getY(), box1.getMin().getY()),
                min(box0.getMin().getZ(), box1.getMin().getZ()));

        Point big = new Point(max(box0.getMax().getX(), box1.getMax().getX()),
                max(box0.getMax().getY(), box1.getMax().getY()),
                max(box0.getMax().getZ(), box1.getMax().getZ()));

        return new AABB(small, big);
    }

    @Override
    public String toString() {
        return "AABB{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
