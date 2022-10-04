package util.collections.impl;

import math.Ray;
import objects.AABB;
import math.HitRecord;
import objects.Boundable;
import objects.Hittable;
import util.collections.AbstractBoundingList;
import util.collections.HittableList;

import java.util.ArrayList;

public class BoundingArrayList extends AbstractBoundingList {
    public BoundingArrayList() {
        super(new ArrayList<>());
    }

    @Override
    public AABB boundingBox(double time0, double time1) {
        if(this.list.isEmpty()){
            throw new RuntimeException("Empty BoundingArrayList");
        }
        AABB tmpBox = new AABB();
        boolean firstBox = true;

        for(Boundable i : this){
            if(firstBox){
                tmpBox = i.boundingBox(time0, time1);
            }else{
                tmpBox = (AABB.surroundingBox(i.boundingBox(time0, time1), tmpBox));
            }
            firstBox = false;
        }
        return tmpBox;
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
    public HittableList getHittableList() {
        HittableList list = new HittableArrayList();
        list.addAll(this);
        return list;
    }
}
