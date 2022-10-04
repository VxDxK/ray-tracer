package objects;

import math.Point;
import math.Ray;
import math.Vector;
import math.Vectors;
import math.HitRecord;
import material.Material;
import util.Pair;

public class Sphere implements Boundable {
    private Point center;
    private double radius;
    private Material material;

    public Sphere(Point center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        Vector oc = new Vector(center, r.getOrigin());
        double A = r.getDirection().lengthSquared();
        double halfB = Vectors.dot(oc, r.getDirection());
        double C = oc.lengthSquared() - radius*radius;
        double discriminant = halfB*halfB - A*C;
        if(discriminant < 0d){
            return false;
        }
        double sqrtd = Math.sqrt(discriminant);
        double root = (-halfB - sqrtd) / A;
        if (root < tMin || tMax < root) {
            root = (-halfB + sqrtd) / A;
            if (root < tMin || tMax < root)
                return false;
        }

        rec.setT(root)
                .setPoint(r.at(rec.getT()));
        Vector outwardNormal = new Vector(center, rec.getPoint()).divide(radius);
        rec.setFaceNormal(r, outwardNormal)
                .setMaterial(this.material).setUV(getHitCoordinates(new Point().move(outwardNormal)));
        return true;
    }

    @Override
    public AABB boundingBox(double time0, double time1) {
        return new AABB(center.move(new Vector(radius, radius, radius).negate()), center.move(new Vector(radius, radius, radius)));
    }

    private Pair<Double, Double> getHitCoordinates(Point point){
        double u = (Math.atan2(-point.getZ(), point.getX()) + Math.PI) / (2 * Math.PI);
        double v = Math.acos(-point.getY())/Math.PI;
        return new Pair<>(u, v);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


}
