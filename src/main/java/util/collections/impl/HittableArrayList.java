package util.collections.impl;

import objects.AABB;
import math.HitRecord;
import objects.Hittable;
import math.Ray;
import util.collections.AbstractHittableList;

import java.util.ArrayList;

public class HittableArrayList extends AbstractHittableList {
    public HittableArrayList() {
        super(new ArrayList<>());
    }

    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        HitRecord tempHit = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = tMax;

        for (Hittable i: this) {
            if(i.hit(r, tMin, closestSoFar, tempHit)){
                hitAnything = true;
                closestSoFar = tempHit.getT();
                rec.set(tempHit);
            }
        }
        return hitAnything;
    }

    @Override
    public String toString() {
        return "HittableArrayList{" +
                "list=" + list +
                '}';
    }
}
