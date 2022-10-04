package objects.comparator;

import math.Points;
import objects.AABB;
import objects.Hittable;
import util.TriFunction;

public class BoxComparator implements TriFunction<Hittable, Hittable, Integer, Integer> {
    @Override
    public Integer apply(Hittable a, Hittable b, Integer axis) {
        AABB boxA = new AABB();
        AABB boxB = new AABB();
        if(!a.boundingBox(0, 0, boxA) || !b.boundingBox(0, 0, boxB)){
            throw new RuntimeException("No bounding box in BVHNode constructor");
        }
        return Double.compare(Points.toArray(boxA.getMin())[axis], Points.toArray(boxB.getMin())[axis]);
//        return Points.toArray(boxA.getMin())[axis] < Points.toArray(boxB.getMin())[axis] ? 1 : -1;
    }
}
