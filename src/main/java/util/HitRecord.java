package util;

import math.Point;
import math.Ray;
import math.Vector;
import math.Vectors;
import util.material.Material;

public class HitRecord{
    private Point point = new Point();
    private Vector normal = new Vector();
    private double t = 0d;
    private boolean frontFace = true;
    private Material material;
    public HitRecord() {
    }


    public void set(HitRecord record){
        point = record.point;
        normal = record.normal;
        t = record.t;
        frontFace = record.frontFace;
        material = record.getMaterial();
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

    public Material getMaterial() {
        return material;
    }

    public HitRecord setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
