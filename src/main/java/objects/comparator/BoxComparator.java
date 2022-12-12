package objects.comparator;

import math.Interval;
import math.Points;
import objects.AABB;
import objects.Boundable;
import objects.Hittable;
import util.TriFunction;

public class BoxComparator implements TriFunction<Boundable, Boundable, Integer, Integer> {
    @Override
    public Integer apply(Boundable a, Boundable b, Integer axis) {
        Interval[] aAxes = new Interval[]{a.boundingBox().getX(), a.boundingBox().getY(), a.boundingBox().getZ()};
        Interval[] bAxes = new Interval[]{b.boundingBox().getX(), b.boundingBox().getY(), b.boundingBox().getZ()};
        return Double.compare(aAxes[axis].getMin(), bAxes[axis].getMin());
    }
}
