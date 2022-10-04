package texture;

import math.Color;
import math.Point;

import java.util.stream.IntStream;

import static java.lang.Math.sin;

public class CheckerTexture implements Texture{
    private final Texture odd;
    private final Texture even;
    private double[] coefficients = new double[]{10d, 10d, 10d};
    public CheckerTexture(Texture odd, Texture even) {
        this.odd = odd;
        this.even = even;
    }

    public CheckerTexture(Color odd, Color even) {
        this.odd = new SolidColorTexture(odd);
        this.even = new SolidColorTexture(even);
    }

    @Override
    public Color value(double u, double v, Point p) {
        double sines = sin(coefficients[0] * p.getX()) * sin(coefficients[1] * p.getY()) * sin(coefficients[2] * p.getZ());
        if(sines <= 0){
            return odd.value(u, v, p);
        }else{
            return even.value(u, v, p);
        }
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public CheckerTexture setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
        return this;
    }
    public CheckerTexture setCoefficient(int index, double value){
        if(index > 2 || index < 0){
            throw new UnsupportedOperationException("index out of range");
        }
        coefficients[index] = value;
        return this;
    }
}
