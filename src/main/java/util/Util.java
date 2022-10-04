package util;

import math.Color;
import math.Point;
import math.Ray;
import math.Vector;
import util.collections.HittableList;
import material.Dielectric;
import material.Lambertian;
import material.Material;
import material.Metal;
import objects.Hittable;
import objects.Sphere;

import static java.lang.Math.*;

public class Util {
    public static double clamp(double x, double min, double max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }

    /**
     * Represents normal of hit point, as a color
     */
    public static Color normalColor(Ray r, Hittable world) {
        HitRecord record = new HitRecord();

        if (world.hit(r, 0, Double.POSITIVE_INFINITY, record)) {
            Vector normal = record.getNormal();
            return new Color(0.5 * (normal.getX() + 1), 0.5 * (normal.getY() + 1), 0.5 * (normal.getZ() + 1));
        }

        Vector unitDirection = r.getDirection().unit();

        double t = 0.5 * (unitDirection.getY() + 1.0);
        return new Color((1.0 - t) + (t * 0.5), (1.0 - t) + (t * 0.7), (1.0 - t) + (t));
    }

    public static void fillScene(HittableList world) {
        for (int x = -11; x < 11; x++) {
            for (int z = -11; z < 11; z++) {
                Point location = new Point(x + random() * 0.9, -0.8, z + random() * 0.9);
                if (new Vector(new Point(0, 0.2, 0), location).length() > 3) {
                    double rand = random();
                    if (rand < 0.50) {
                        Material material = new Lambertian(new Color(random(), random(), random()));
                        world.add(new Sphere(location, 0.2, material));
                    } else if (rand < 0.75) {
                        Material material = new Metal(new Color(random(), random(), random()), random() * 0.3);
                        world.add(new Sphere(location, 0.2, material));
                    } else {
                        Material material = new Dielectric((Math.random() * (1.5)) + 1.5);
                        world.add(new Sphere(location, 0.2, material));
                    }
                }
            }
        }
    }

}
