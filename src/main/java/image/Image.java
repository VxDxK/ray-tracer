package image;

import math.Color;
import util.Pair;

public interface Image extends Iterable<Pixel> {
    Pair<Integer, Integer> getSize();

    Color getColor(int x, int y);
}
