package raytracer;

import util.Dimension;

import java.util.Objects;

public record RayTracerConfig(Dimension imageDimension, int depth, int samplesPerPixel) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RayTracerConfig that)) return false;
        return depth == that.depth && samplesPerPixel == that.samplesPerPixel && Objects.equals(imageDimension, that.imageDimension);
    }

    public int getHeight() {
        return imageDimension.getHeight();
    }

    public int getWidth() {
        return imageDimension.getWidth();
    }
}
