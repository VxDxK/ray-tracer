package util.collections;

import objects.Hittable;

import java.util.List;

public interface HittableList extends List<Hittable>, Hittable {
    List<Hittable> getList();
}
