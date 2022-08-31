package math;

import java.util.Objects;
import java.util.Random;

import static java.lang.Math.abs;

public class Vector {
    private double x = 0d;
    private double y = 0d;
    private double z = 0d;

    public Vector() {
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point point1, Point point2){
        this.x = -point1.getX() + point2.getX();
        this.y = -point1.getY() + point2.getY();
        this.z = -point1.getZ() + point2.getZ();
    }

    public Vector(Vector vector){
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
    }

    public boolean nearZero(){
        double s = 1e-8;
        return abs(x) < s && abs(y) < s && abs(z) < s;
    }

    public Vector multiply(double t){
        return new Vector(x*t, y*t, z*t);
    }

    public Vector divide(double t){
        return multiply(1/t);
    }

    public Vector add(Vector vector){
        return new Vector(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    public Vector subtract(Vector vector){
        return this.add(vector.negate());
    }

    public Vector unit(){
        return new Vector(this).divide(length());
    }

    public Vector negate(){
        return new Vector(-x, -y, -z);
    }

    public double length(){
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared(){
        return x*x + y*y + z*z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return Double.compare(vector.x, x) == 0 && Double.compare(vector.y, y) == 0 && Double.compare(vector.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
