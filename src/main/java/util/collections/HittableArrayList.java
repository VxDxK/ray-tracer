package util.collections;

import objects.AABB;
import util.HitRecord;
import objects.Hittable;
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

    @Override
    public boolean boundingBox(double time0, double time1, AABB outputBox) {
        if(this.list.isEmpty()){
            return false;
        }

        AABB tmpBox = new AABB();
        boolean firstBox = true;

        for(Hittable i : this){
            if(!i.boundingBox(time0, time1, tmpBox)){
                return false;
            }
            if(firstBox){
                outputBox.set(tmpBox);
            }else{
                outputBox.set(AABB.surroundingBox(outputBox, tmpBox));
            }
            firstBox = false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HittableArrayList{" +
                "list=" + list +
                '}';
    }
}
