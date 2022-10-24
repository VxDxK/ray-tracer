package texture;

import math.Color;
import math.Point;

import java.util.function.Function;

public class MarbleTexture extends PerlinTexture {
    private final Function<Point, Double> coordinate;
    public MarbleTexture() {
        super();
        coordinate = Point::getZ;
    }

    public MarbleTexture(Function<Point, Double> coordinate, double scale, int depth) {
        super(scale, depth);
        this.coordinate = coordinate;
    }

    public MarbleTexture(Color color, Function<Point, Double> coordinate, double scale, int depth) {
        super(color, scale, depth);
        this.coordinate = coordinate;
    }

    @Override
    public Color value(double u, double v, Point p) {
        return color.scale(0.5 * (1 + Math.sin(scale * coordinate.apply(p) +  10 * noise.turbulence(p))));
    }
}
