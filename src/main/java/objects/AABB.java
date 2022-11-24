package objects;

import math.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AABB {
    private final Interval xInterval;
    private final Interval yInterval;
    private final Interval zInterval;


    public AABB() {
        this(new Point(), new Point());
    }



    public AABB(Point min, Point max) {
        xInterval = new Interval(Math.min(min.getX(), max.getX()), Math.max(min.getX(), max.getX()));
        yInterval = new Interval(Math.min(min.getY(), max.getY()), Math.max(min.getY(), max.getY()));
        zInterval = new Interval(Math.min(min.getZ(), max.getZ()), Math.max(min.getZ(), max.getZ()));
    }

    public AABB(Interval xInterval, Interval yInterval, Interval zInterval) {
        this.xInterval = xInterval;
        this.yInterval = yInterval;
        this.zInterval = zInterval;
    }

    public boolean hit(Ray r, Interval tInterval){
        Interval[] axis = {xInterval, yInterval, zInterval};
        double[] vecAxis = Vectors.toArray(r.getDirection());
        double[] pointCo = Points.toArray(r.getOrigin());
        Interval tmpInterval = new Interval(tInterval);
        for (int i = 0; i < 3; i++){
            double t0 = Math.min((axis[i].getMin() - pointCo[i])/ vecAxis[i], (axis[i].getMax() - pointCo[i])/vecAxis[i]);
            double t1 = Math.max((axis[i].getMin() - pointCo[i])/vecAxis[i], (axis[i].getMax() - pointCo[i])/vecAxis[i]);

            tmpInterval.setMin(Math.max(t0, tmpInterval.getMin()));
            tmpInterval.setMax(Math.min(t1, tmpInterval.getMax()));

            if (tmpInterval.getMax() <= tmpInterval.getMin())
                return false;
        }
        return true;
    }

    public Interval getX() {
        return xInterval;
    }

    public Interval getY() {
        return yInterval;
    }

    public Interval getZ() {
        return zInterval;
    }

    public static AABB surroundingBox(AABB box0, AABB box1){
        Interval x = new Interval(box0.xInterval, box1.xInterval);
        Interval y = new Interval(box0.yInterval, box1.yInterval);
        Interval z = new Interval(box0.zInterval, box1.zInterval);
        return new AABB(x, y, z);
    }

    public AABB pad(){
        double delta = 0.1;
        Interval newX = (xInterval.size() >= delta) ? xInterval : xInterval.expand(delta);
        Interval newY = (yInterval.size() >= delta) ? yInterval : yInterval.expand(delta);
        Interval newZ = (zInterval.size() >= delta) ? zInterval : zInterval.expand(delta);
        return new AABB(newX, newY, newZ);
    }

    @Override
    public String toString() {
        return "AABB{" +
                "xInterval=" + xInterval +
                ", yInterval=" + yInterval +
                ", zInterval=" + zInterval +
                '}';
    }
}
