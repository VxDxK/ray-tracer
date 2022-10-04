package objects.comparator;

import objects.Hittable;

import java.util.Comparator;
import java.util.function.BiFunction;

public class YComparator implements Comparator<Hittable> {
    BoxComparator boxComparator = new BoxComparator();
    @Override
    public int compare(Hittable a, Hittable b) {
        return boxComparator.apply(a, b, 1);
    }
}
