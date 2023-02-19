package texture;

import math.Color;
import math.Point;
import math.Points;
import util.PerlinNoise;

public class PerlinTexture implements Texture {
    protected final PerlinNoise noise = new PerlinNoise();
    protected final double scale;
    protected final int depth;
    protected final Color color;

    public PerlinTexture() {
        this(new Color(1, 1, 1), 4, 1);
    }

    public PerlinTexture(double scale, int depth) {
        this(new Color(1, 1, 1), scale, depth);
    }

    public PerlinTexture(Color color, double scale, int depth) {
        this.color = color;
        this.scale = scale;
        this.depth = depth;
    }

    @Override
    public Color value(double u, double v, Point p) {
        return color.scale(noise.turbulence(Points.scale(p, scale), depth));
    }

}
