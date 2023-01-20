package util;

import raytracer.RayTracerConfig;

import java.util.Objects;

public class Dimension {
    private final int width;
    private final int height;
    private final double aspectRatio;

    public Dimension(int width, int height) {
        this.height = height;
        this.width = width;
        this.aspectRatio = (double) width / (double)height;
    }

    public Dimension(int width, double aspectRatio) {
        this.width = width;
        this.height = (int) (width / aspectRatio);
        this.aspectRatio = aspectRatio;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    @Override
    public String toString() {
        return "RayTracerConfig{" +
                "width=" + width +
                ", height=" + height +
                ", aspectRatio=" + aspectRatio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dimension that)) return false;
        return height == that.height && width == that.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

}
