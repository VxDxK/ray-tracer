package texture;

import math.Color;
import math.Point;

public interface Texture {
    Color value(double u, double v, Point p);
}
