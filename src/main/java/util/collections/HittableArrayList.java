package util.collections;

import util.HitRecord;
import util.Hittable;
import math.Ray;

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

}
