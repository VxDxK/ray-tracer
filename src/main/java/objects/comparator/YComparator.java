package objects.comparator;

import objects.Boundable;
import objects.Hittable;

import java.util.Comparator;
import java.util.function.BiFunction;

public class YComparator implements Comparator<Boundable> {
    BoxComparator boxComparator = new BoxComparator();

    @Override
    public int compare(Boundable a, Boundable b) {
        return boxComparator.apply(a, b, 1);
    }
}
