package image;

import util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListImage implements Image{
    private final int xLen;
    private final int yLen;
    private final List<Pixel> list = new LinkedList<>();

    public ListImage(int xLen, int yLen) {
        this.xLen = xLen;
        this.yLen = yLen;
    }

    @Override
    public Iterator<Pixel> iterator() {
        return list.iterator();
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return new Pair<Integer, Integer>(xLen, yLen);
    }
}
