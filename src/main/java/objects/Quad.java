package objects;

import material.Material;
import math.*;

@Deprecated
public class Quad implements Hittable, Boundable {
    private final Point Q;
    private final Vector u;
    private final Vector v;
    private final Material material;
    private final AABB box;
    private final Vector normal;
    private double D;
    private final Vector w;

    public Quad(Point q, Vector u, Vector v, Material material) {
        Q = q;
        this.u = u;
        this.v = v;
        this.material = material;

        Vector n = Vectors.cross(u, v);
        this.normal = n.unit();
        this.D = Vectors.dot(normal, new Vector(this.Q));
        this.w = n.divide(Vectors.dot(n, n));

        this.box = new AABB(Q, Q.move(u).move(v)).pad();

    }

    @Override
    public AABB boundingBox() {
        return box;
    }

    @Override
    public boolean hit(Ray r, Interval tInterval, HitRecord rec) {
        double denom = Vectors.dot(normal, r.getDirection());
        if (Math.abs(denom) < 1e-8)
            return false;

        double t = (D - Vectors.dot(normal, new Vector(r.getOrigin()))) / denom;
        if (!tInterval.contains(t)) {
            return false;
        }

        Point intersection = r.at(t);
        Vector hitVector = new Vector(Q, intersection);
        double alpha = Vectors.dot(w, Vectors.cross(hitVector, v));
        double beta = Vectors.dot(w, Vectors.cross(u, hitVector));

        if (!isInterior(alpha, beta, rec))
            return false;

        rec.setT(t)
                .setPoint(intersection)
                .setMaterial(material)
                .setFaceNormal(r, normal);

        return true;
    }

    private boolean isInterior(double a, double b, HitRecord rec) {
        if ((a < 0) || (1 < a) || (b < 0) || (1 < b))
            return false;

        rec.setU(a);
        rec.setV(b);
        return true;
    }
}
