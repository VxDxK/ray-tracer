package image;

import math.Color;
import util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColorImage implements Image {
    private final int x;
    private final int y;
    private final Color color;

    public ColorImage(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public ColorImage(Color color) {
        this(1, 1, color);
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return new Pair<>(x, y);
    }

    @Override
    public Color getColor(int x, int y) {
        return color;
    }

    @Override
    public Iterator<Pixel> iterator() {
        List<Pixel> pixels = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                pixels.add(new Pixel(i, j, color));
            }
        }
        return pixels.iterator();
    }
}
