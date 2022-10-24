package texture;

import math.Color;
import math.Point;

import java.util.stream.IntStream;

import static java.lang.Math.sin;

public class CheckerTexture implements Texture{
    private final Texture odd;
    private final Texture even;
    private final double scale;

    public CheckerTexture(Texture odd, Texture even) {
        this(1, odd, even);
    }

    public CheckerTexture(Color odd, Color even) {
        this(1, odd, even);
    }

    public CheckerTexture(double scale, Texture odd, Texture even) {
        this.scale = scale;
        this.odd = odd;
        this.even = even;
    }

    public CheckerTexture(double scale, Color odd, Color even) {
        this.scale = scale;
        this.odd = new SolidColorTexture(odd);
        this.even = new SolidColorTexture(even);
    }

    @Override
    public Color value(double u, double v, Point p) {
        int x = (int) (scale * p.getX());
        int y = (int) (scale * p.getY());
        int z = (int) (scale * p.getZ());
        boolean isSumEven = (x + y + z) % 2 == 0;

        return isSumEven ? even.value(u, v, p) : odd.value(u, v, p);
    }

}
