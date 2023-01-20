package util.collections.impl;

import math.Interval;
import math.Ray;
import objects.AABB;
import math.HitRecord;
import objects.Boundable;
import objects.Hittable;
import util.collections.AbstractBoundableList;
import util.collections.HittableList;

import java.util.ArrayList;

public class BoundableArrayList extends AbstractBoundableList {
    public BoundableArrayList() {
        super(new ArrayList<>());
    }

    @Override
    public AABB boundingBox() {
        if(this.list.isEmpty()){
            throw new RuntimeException("Empty BoundingArrayList");
        }
        AABB tmpBox = new AABB();
        boolean firstBox = true;

        for(Boundable i : this){
            if(firstBox){
                tmpBox = i.boundingBox();
                firstBox = false;
            }else{
                tmpBox = (AABB.surroundingBox(i.boundingBox(), tmpBox));
            }
        }
        return tmpBox;
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
    public HittableList getHittableList() {
        HittableList list = new HittableArrayList();
        list.addAll(this);
        return list;
    }

    @Override
    public String toString() {
        return "BoundableArrayList{" +
                "list=" + list +
                '}';
    }
}
