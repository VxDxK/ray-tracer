package raytracer;

import util.Dimension;

import java.util.Objects;

public class RayTracerConfig {
    private final Dimension imageDimension;
    private final int depth;
    private final int samplesPerPixel;
    public RayTracerConfig(Dimension imageDimension, int depth, int samplesPerPixel) {
        this.imageDimension = imageDimension;
        this.depth = depth;
        this.samplesPerPixel = samplesPerPixel;
    }

    public Dimension getImageDimension() {
        return imageDimension;
    }

    public int getDepth() {
        return depth;
    }

    public int getSamplesPerPixel() {
        return samplesPerPixel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RayTracerConfig that)) return false;
        return depth == that.depth && samplesPerPixel == that.samplesPerPixel && Objects.equals(imageDimension, that.imageDimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageDimension, depth, samplesPerPixel);
    }

    public int getHeight() {
        return imageDimension.getHeight();
    }

    public int getWidth() {
        return imageDimension.getWidth();
    }
}
