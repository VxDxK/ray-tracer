package texture;

import math.Color;
import math.Point;

public class SinCheckerTexture implements Texture{
    private final Texture odd;
    private final Texture even;

    public SinCheckerTexture(Texture odd, Texture even) {
        this.odd = odd;
        this.even = even;
    }

    public SinCheckerTexture(Color odd, Color even){
        this.odd = new SolidColorTexture(odd);
        this.even = new SolidColorTexture(even);
    }

    @Override
    public Color value(double u, double v, Point p) {
        double sin = Math.sin(10 * p.getX()) * Math.sin(10 * p.getY()) * Math.sin(10 * p.getZ());
        return sin < 0 ? odd.value(u, v, p) : even.value(u, v, p);
    }
}
