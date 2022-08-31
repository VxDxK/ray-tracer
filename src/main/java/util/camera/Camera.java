package util.camera;

import math.Ray;

public interface Camera {
    Ray getRay(double s, double t);
}
