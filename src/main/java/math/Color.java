package math;

import java.util.Objects;

public class Color {
    //[0;1]
    private double red = 0d;
    private double green = 0d;
    private double blue = 0d;

    public Color() {
    }

    public Color(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color(Color color){
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }

    public void set(Color color){
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }

    public static Color getByRGB(int rgb){
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;
        return getByRGB(r, g, b);
    }



    public static Color getByRGB(int r, int g, int b){
        return new Color((float)r/255, (float)g/255, (float)b/255);
    }

    public Color scale(double t){
        return new Color(red * t, green * t, blue * t);
    }

    public double getRed() {
        return red;
    }

    public Color setRed(double red) {
        this.red = red;
        return this;
    }

    public double getGreen() {
        return green;
    }

    public Color setGreen(double green) {
        this.green = green;
        return this;
    }

    public double getBlue() {
        return blue;
    }

    public Color setBlue(double blue) {
        this.blue = blue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Color color)) return false;
        return Double.compare(color.red, red) == 0 && Double.compare(color.green, green) == 0 && Double.compare(color.blue, blue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);}

    private boolean inRange(double d){
        return d <= 1d && d >= 0;
    }

    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
