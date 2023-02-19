package util;

import math.Point;
import math.Points;
import math.Vector;
import math.Vectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.floor;

public class PerlinNoise {
    private final static int pointCount = 256;
    private final Vector[] randomVectors = new Vector[pointCount];
    private final List<Integer> permX = new ArrayList<>();
    private final List<Integer> permY = new ArrayList<>();
    private final List<Integer> permZ = new ArrayList<>();

    public PerlinNoise() {
        for (int i = 0; i < pointCount; i++) {
            randomVectors[i] = Vectors.getRandom(-1, 1).unit();
            permX.add(i);
            permY.add(i);
            permZ.add(i);
        }
        Collections.shuffle(permX);
        Collections.shuffle(permY);
        Collections.shuffle(permZ);
    }

    public double turbulence(Point p) {
        return turbulence(p, 4);
    }

    public double turbulence(Point p, int depth) {
        double accum = 0.0;
        Point tmp = new Point(p);
        double weight = 1.0;

        for (int i = 0; i < depth; i++) {
            accum += (weight * noise(tmp));
            weight *= 0.5;
            tmp = Points.scale(tmp, 2);
        }
        return Math.abs(accum);
    }

    public double noise(Point p) {
        double u = p.getX() - floor(p.getX());
        double v = p.getY() - floor(p.getY());
        double w = p.getZ() - floor(p.getZ());

        int i = (int) floor(p.getX());
        int j = (int) floor(p.getY());
        int k = (int) floor(p.getZ());
        Vector[][][] c = new Vector[2][2][2];
        for (int di = 0; di < 2; di++)
            for (int dj = 0; dj < 2; dj++)
                for (int dk = 0; dk < 2; dk++)
                    c[di][dj][dk] = randomVectors[
                            permX.get((i + di) & 255) ^
                                    permY.get((j + dj) & 255) ^
                                    permZ.get((k + dk) & 255)
                            ];

        return interpolation(c, u, v, w);
    }

    private double interpolation(Vector[][][] c, double u, double v, double w) {
        double accum = 0d;
        double uu = u * u * (3 - 2 * u);
        double vv = v * v * (3 - 2 * v);
        double ww = w * w * (3 - 2 * w);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++) {
                    Vector weight = new Vector(u - i, v - j, w - k);
                    accum += (i * uu + (1 - i) * (1 - uu))
                            * (j * vv + (1 - j) * (1 - vv))
                            * (k * ww + (1 - k) * (1 - ww))
                            * Vectors.dot(c[i][j][k], weight);
                }
        return accum;
    }
}
