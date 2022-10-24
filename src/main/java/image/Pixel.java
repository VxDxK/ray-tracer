package image;

import math.Color;
import math.Colors;

public class Pixel {
    private final int x;
    private final int y;
    private final Color color;

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = new Colors.ImmutableColor(color);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
