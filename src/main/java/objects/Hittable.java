package objects;

import math.Ray;
import math.HitRecord;

public interface Hittable {
    boolean hit(Ray r, double tMin, double tMax, HitRecord rec);
}
