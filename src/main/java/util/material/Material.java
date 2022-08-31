package util.material;

import math.Ray;
import util.Color;
import util.HitRecord;

public interface Material {
    boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered);
}
