package objects;

import math.Point;
import math.Ray;
import math.Vector;
import math.Vectors;
import util.HitRecord;
import material.Material;

public class MovingSphere implements Hittable{
    private final Point centerStart;
    private final Point centerFinish;
    private final double timeStart;
    private final double timeFinish;
    private final double radius;
    private final Material material;

    public MovingSphere(Point centerStart, Point centerFinish, double radius, double timeStart, double timeFinish,  Material material) {
        this.centerStart = centerStart;
        this.centerFinish = centerFinish;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.radius = radius;
        this.material = material;
    }

    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        Vector oc = new Vector(center(r.getTimeMoment()), r.getOrigin());
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

        rec.setT(root);
        rec.setPoint(r.at(rec.getT()));
        Vector outwardNormal = new Vector(center(r.getTimeMoment()), rec.getPoint()).divide(radius);
        rec.setFaceNormal(r, outwardNormal);
        rec.setMaterial(this.material);
        return true;
    }

    private Point center(double time){
        if(Math.abs(timeFinish - timeStart) <= 1e-10){
            return centerStart;
        }
        return centerStart.move(new Vector(centerStart, centerFinish).multiply((time - timeStart)/(timeFinish - timeStart)));
    }

}
