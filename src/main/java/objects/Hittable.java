package objects;

import math.Interval;
import math.Ray;
import math.HitRecord;

public interface Hittable {
    boolean hit(Ray r, Interval tInterval, HitRecord rec);
}
