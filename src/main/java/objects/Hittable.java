package objects;

import math.Ray;
import util.HitRecord;

public interface Hittable {
    boolean hit(Ray r, double tMin, double tMax, HitRecord rec);
    boolean boundingBox(double time0, double time1, AABB aabb);
}
