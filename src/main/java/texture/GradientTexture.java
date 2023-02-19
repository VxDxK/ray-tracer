package texture;

import math.Color;
import math.Colors;
import math.Point;

public class GradientTexture implements Texture {
    private final Color begin;
    private final Color end;

    public GradientTexture(Color begin, Color end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Color value(double u, double v, Point p) {
        Color first = begin.scale(u);
        Color second = end.scale((1 - u));

        return Colors.add(first, second);
    }
}
