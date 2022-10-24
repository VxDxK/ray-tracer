package util.collections.impl;

import math.Interval;
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
    public boolean hit(Ray r, Interval tInterval, HitRecord rec) {
        HitRecord tempHit = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = tInterval.getMax();

        for (Hittable i: this) {
            if(i.hit(r, new Interval(tInterval.getMin(), closestSoFar), tempHit)){
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
