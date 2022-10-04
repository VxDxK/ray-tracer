package material;

import math.Ray;
import math.Color;
import math.HitRecord;

public interface Material {
    boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered);
}
