package util.collections;

import objects.Boundable;

import java.util.List;

public interface BoundableList extends List<Boundable>, Boundable {
    HittableList getHittableList();
}
