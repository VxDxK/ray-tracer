package image;

import util.Pair;

public interface Image extends Iterable<Pixel>{
    Pair<Integer, Integer> getSize();
}
