package objects;

import material.Material;
import math.*;

public class Quadrilateral extends Plane implements Boundable{
    private final AABB box;
    private final Vector w;

    public Quadrilateral(Point p, Vector u, Vector v, Material material) {
        super(p, u, v, material);
        this.box = new AABB(p, p.move(this.u).move(this.v)).pad();
        Vector n = Vectors.cross(u, v);
        this.w = n.divide(Vectors.dot(n, n));
    }

    @Override
    public AABB boundingBox() {
        return box;
    }

    @Override
    public boolean hit(Ray r, Interval tInterval, HitRecord rec) {
        if (!super.hit(r, tInterval, rec))
            return false;

        Vector hitVector = new Vector(p, rec.getPoint());
        double alpha = Vectors.dot(w, Vectors.cross(hitVector, v));
        double beta = Vectors.dot(w, Vectors.cross(u, hitVector));

        Interval testInterval = new Interval(0, 1);
        if(!testInterval.contains(alpha) || !testInterval.contains(beta))
            return false;

        rec.setU(alpha).setV(beta);
        return true;
    }
}
