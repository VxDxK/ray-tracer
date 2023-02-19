package math;

import math.Vector;

import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Vectors {
    //Скалярное произведение
    public static double dot(Vector vector1, Vector vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY() + vector1.getZ() * vector2.getZ();
    }

    //Векторное произведение
    public static Vector cross(Vector vector1, Vector vector2) {
        return new Vector(vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
                vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ(),
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX());
    }

    public static Vector getRandom() {
        Random random = new Random();
        return new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    public static Vector getRandom(double min, double max) {
        Random random = new Random();
        return new Vector(random.nextDouble(min, max), random.nextDouble(min, max), random.nextDouble(min, max));
    }

    public static Vector randomInUnitSphere() {
        while (true) {
            Vector vector = getRandom(-1, 1);
            if (vector.lengthSquared() >= 1) {
                continue;
            }
            ;
            return vector;
        }
    }

    public static Vector reflect(Vector vector, Vector normal) {
        return vector.subtract(normal.multiply(dot(vector, normal) * 2));
    }

    public static Vector refract(Vector vector, Vector normal, double etai) {
        double cosTheta = Math.min(dot(vector.negate(), normal), 1d);
        Vector perpendicular = vector.add(normal.multiply(cosTheta)).multiply(etai);
        Vector parallel = normal.multiply(-sqrt(abs(1d - perpendicular.lengthSquared())));
        return perpendicular.add(parallel);
    }

    public static Vector randomInUnitDisk() {
        Random random = new Random();
        while (true) {
            Vector vector = new Vector(random.nextDouble(-1, 1), random.nextDouble(-1, 1), 0);
            if (vector.lengthSquared() >= 1) continue;
            return vector;
        }
    }

    public static double[] toArray(Vector vector) {
        return new double[]{vector.getX(), vector.getY(), vector.getZ()};
    }

    public static Vector randomUnitVector() {
        return randomInUnitSphere().unit();
    }

    private Vectors() {
    }
}
