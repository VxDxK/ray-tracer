package util;

import math.Point;
import math.Ray;
import math.Vector;
import math.Vectors;

public class HitRecord{
    private Point point = new Point();
    private Vector normal = new Vector();
    private double t = 0d;
    private boolean frontFace = true;
    public HitRecord() {
    }

    public HitRecord(Point point, Vector normal, double t, boolean frontFace) {
        this.point = point;
        this.normal = normal;
        this.t = t;
        this.frontFace = frontFace;
    }

    public void set(HitRecord record){
        point = record.point;
        normal = record.normal;
        t = record.t;
        frontFace = record.frontFace;
    }

    public void setFaceNormal(Ray r, Vector outwardNormal){
        frontFace = Vectors.dot(r.getDirection(), outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.negate();
    }

    public Point getPoint() {
        return point;
    }

    public HitRecord setPoint(Point point) {
        this.point = point;
        return this;
    }

    public Vector getNormal() {
        return normal;
    }

    public HitRecord setNormal(Vector normal) {
        this.normal = normal;
        return this;
    }

    public double getT() {
        return t;
    }

    public HitRecord setT(double t) {
        this.t = t;
        return this;
    }

    public boolean isFrontFace() {
        return frontFace;
    }

    public HitRecord setFrontFace(boolean frontFace) {
        this.frontFace = frontFace;
        return this;
    }
}
