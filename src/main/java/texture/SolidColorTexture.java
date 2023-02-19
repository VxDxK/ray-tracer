package texture;

import math.Color;
import math.Point;

public class SolidColorTexture implements Texture {
    private final Color color;

    public SolidColorTexture(Color color) {
        this.color = color;
    }

    @Override
    public Color value(double u, double v, Point p) {
        return color;
    }
}
