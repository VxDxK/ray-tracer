package util;

import math.Ray;

public interface Hittable {
    boolean hit(Ray r, double tMin, double tMax, HitRecord rec);
}
