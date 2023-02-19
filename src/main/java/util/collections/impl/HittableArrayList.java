package util.collections.impl;

import math.HitRecord;
import math.Interval;
import math.Ray;
import objects.Hittable;
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

        for (Hittable i : this) {
            if (i.hit(r, new Interval(tInterval.getMin(), closestSoFar), tempHit)) {
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
