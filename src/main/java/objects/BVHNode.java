package objects;

import math.Ray;
import objects.comparator.XComparator;
import objects.comparator.YComparator;
import objects.comparator.ZComparator;
import util.HitRecord;
import util.collections.HittableList;

import java.util.*;

public class BVHNode implements Hittable{
    private Hittable left;
    private Hittable right;
    private AABB box;

    private static final List<Comparator<Hittable>> comparators = List.of(new XComparator(), new YComparator(), new ZComparator());

    public BVHNode(HittableList hittableList, double time0, double time1){
        this(hittableList.getList(), 0, hittableList.getList().size(), time0, time1);
    }

    public BVHNode(List<Hittable> objects, int start, int end, double time0, double time1){
        Random random = new Random();
        int axis = random.nextInt(0, 3);
        Comparator<Hittable> comparator = comparators.get(axis);

        long span = end - start;
        if(span == 1){
            left = objects.get(start);
            right = left;
        }else if(span == 2){
            left = objects.get(start);
            right = objects.get(start + 1);
        }else{
            objects.subList(start, end).sort(comparator);
            int mid = start + (int)span/2;
            left = new BVHNode(objects, start, mid, time0, time1);
            right = new BVHNode(objects, mid, end, time0, time1);
        }
        AABB boxLeft = new AABB(), boxRight = new AABB();
        if(!left.boundingBox(time0, time1, boxLeft) || !right.boundingBox(time0, time1, boxRight)){
            throw new RuntimeException("No bounding box in BVHNode constructor");
        }
        box = AABB.surroundingBox(boxLeft, boxRight);
    }

    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        if(!box.hit(r, tMin, tMax)){
            return false;
        }

        boolean hitLeft = left.hit(r, tMin, tMax, rec);
        boolean hitRight = right.hit(r, tMin, hitLeft ? rec.getT() : tMax, rec);
        return hitLeft || hitRight;
    }

    @Override
    public boolean boundingBox(double time0, double time1, AABB aabb) {
        aabb.set(box);
        return true;
    }

    public Hittable getLeft() {
        return left;
    }

    public Hittable getRight() {
        return right;
    }

    public AABB getBox() {
        return box;
    }

    @Override
    public String toString() {
        return "BVHNode{\n" +
                "box=" + box +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
