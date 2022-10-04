package objects;

public interface Boundable extends Hittable {
    AABB boundingBox(double time0, double time1);
}
