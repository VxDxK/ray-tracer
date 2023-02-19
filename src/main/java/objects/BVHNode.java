package objects;

import math.Interval;
import math.Ray;
import objects.comparator.XComparator;
import objects.comparator.YComparator;
import objects.comparator.ZComparator;
import math.HitRecord;
import util.collections.BoundableList;

import java.util.*;

public class BVHNode implements Boundable {
    private final Boundable left;
    private final Boundable right;
    private final AABB box;

    public BVHNode(BoundableList list) {
        this(list, 0, list.size());
    }

    public BVHNode(BoundableList list, int begin, int end) {
        if (list.isEmpty()) {
            left = new Boundable() {
                @Override
                public AABB boundingBox() {
                    return new AABB() {
                        @Override
                        public boolean hit(Ray r, Interval tInterval) {
                            return false;
                        }
                    };
                }

                @Override
                public boolean hit(Ray r, Interval tInterval, HitRecord rec) {
                    return false;
                }
            };
            right = left;
            box = AABB.surroundingBox(left.boundingBox(), right.boundingBox());
            return;
        }
        Random random = new Random();
        List<Comparator<Boundable>> comparators = List.of(new XComparator(), new YComparator(), new ZComparator());
        Comparator<Boundable> comparator = comparators.get(random.nextInt(0, 3));
        int span = end - begin;
        if (span == 1) {
            left = list.get(begin);
            right = left;
        } else if (span == 2) {
            left = list.get(begin);
            right = list.get(begin + 1);
        } else {
            list.subList(begin, end).sort(comparator);
            int mid = begin + span / 2;
            left = new BVHNode(list, begin, mid);
            right = new BVHNode(list, mid, end);
        }
        box = AABB.surroundingBox(left.boundingBox(), right.boundingBox());
    }


    @Override
    public AABB boundingBox() {
        return box;
    }

    @Override
    public boolean hit(Ray r, Interval tInterval, HitRecord rec) {
        if (!box.hit(r, tInterval))
            return false;
        boolean hitLeft = left.hit(r, tInterval, rec);
        boolean hitRight = right.hit(r, new Interval(tInterval.getMin(), hitLeft ? rec.getT() : tInterval.getMax()), rec);
        return hitLeft || hitRight;
    }

    @Override
    public String toString() {
        return "BVHNode{" +
                ", box=" + box +
                "\nleft=" + left +
                ", \nright=" + right +
                '}';
    }
}
