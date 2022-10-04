package util.collections;

import objects.Boundable;

import java.util.List;

public interface BoundingList extends List<Boundable>, Boundable {
    List<Boundable> getList();
    HittableList getHittableList();
}
