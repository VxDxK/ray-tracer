package image;

import math.Color;
import util.Pair;

import java.util.*;

public class ArrayImage implements Image {
    private final int xLen;
    private final int yLen;
    private final Color[][] colors;

    public ArrayImage(int xLen, int yLen) {
        this.xLen = xLen;
        this.yLen = yLen;
        colors = new Color[xLen][yLen];
    }

    public ArrayImage(Color[][] colors) {
        this.colors = colors;
        this.xLen = colors.length;
        this.yLen = colors[0].length;
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return new Pair<>(xLen, yLen);
    }

    @Override
    public Color getColor(int x, int y) {
        if (x >= xLen || y >= yLen) {
            throw new IllegalArgumentException("Coordinate out of image size");
        }
        return colors[x][y];
    }

    @Override
    public Iterator<Pixel> iterator() {
        List<Pixel> list = new ArrayList<>();
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                list.add(new Pixel(i, j, colors[i][j]));
            }
        }
        return list.iterator();
    }
}
