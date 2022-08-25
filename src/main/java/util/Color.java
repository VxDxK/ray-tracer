package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
//        if(!(inRange(red) && inRange(green) && inRange(blue))){
//            throw new IllegalArgumentException("Color not in range: " + this);
//        }
    }

    public Color(Color color){
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }


    public double getRed() {
        return red;
    }

    public Color setRed(double red) {
//        if(!inRange(red)){
//            throw new IllegalArgumentException("Color not in range: " + this);
//        }
        this.red = red;
        return this;
    }

    public double getGreen() {
        return green;
    }

    public Color setGreen(double green) {
//        if(!inRange(green)){
//            throw new IllegalArgumentException("Color not in range: " + this);
//        }
        this.green = green;
        return this;
    }

    public double getBlue() {
        return blue;
    }

    public Color setBlue(double blue) {
//        if(!inRange(blue)){
//            throw new IllegalArgumentException("Color not in range: " + this);
//        }
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
