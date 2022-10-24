package objects;

import material.Material;
import math.*;

public class Plane implements Hittable{
    protected final Point p;
    protected final Vector u;
    protected final Vector v;
    protected final Material material;

    protected final Vector normal;
    protected final double D;

    public Plane(Point p, Vector u, Vector v, Material material) {
        this.p = p;
        this.u = u;
        this.v = v;
        this.material = material;
        this.normal = Vectors.cross(u, v).unit();
        this.D = Vectors.dot(normal, new Vector(new Point(), p));
    }
    public Plane(Point p, Point u, Point v, Material material) {
        this(p, new Vector(u, p), new Vector(v, p), material);
    }



    @Override
    public boolean hit(Ray r, Interval tInterval, HitRecord rec) {
        double denom = Vectors.dot(normal, r.getDirection());
        if (Math.abs(denom) <= 1e-8)
            return false;
        double t = (D - Vectors.dot(normal, new Vector(new Point(), r.getOrigin()))) / denom;
        if (!tInterval.contains(t))
            return false;

        Point intersection = r.at(t);
        rec.setT(t).setPoint(intersection).setMaterial(material).setFaceNormal(r, normal);
        return true;
    }
}
