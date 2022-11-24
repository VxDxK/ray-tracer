package material;

import math.Point;
import math.Ray;
import math.Color;
import math.HitRecord;

public interface Material {
    boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered);

    default Color emitted(double u, double v, Point p){
        return new Color(0, 0, 0);
    }
}
