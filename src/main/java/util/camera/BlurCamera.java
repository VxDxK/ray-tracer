package util.camera;

import math.Point;
import math.Ray;
import math.Vector;
import math.Vectors;
import util.camera.Camera;

public class BlurCamera implements Camera {
    private final Point origin;
    private final Point lowerLeftCorner;
    private final Vector horizontal;
    private final Vector vertical;
    private final double lensRadius;
    private final Vector w, u, v;
    public BlurCamera(Point lookFrom, Point lookAt, Vector worldNormal, double verticalFov, double aspectRatio, double aperture, double focusDist){
        double theta = Math.toRadians(verticalFov);
        double h = Math.tan(theta/2);
        double viewportHeight = 2.0 * h;
        double viewportWidth = aspectRatio * viewportHeight;

        double focalLength = 1.0;

        w = new Vector(lookAt, lookFrom).unit();
        u = Vectors.cross(worldNormal, w).unit();
        v = Vectors.cross(w, u);

        this.origin = lookFrom;
        this.horizontal = u.multiply(viewportWidth).multiply(focusDist);
        this.vertical = v.multiply(viewportHeight).multiply(focusDist);
        this.lowerLeftCorner = origin.move(vertical.negate().divide(2)).move(horizontal.negate().divide(2)).move(w.negate().multiply(focusDist));

        lensRadius = aperture/2;
    }

    @Override
    public Ray getRay(double s, double t){
        Vector rd = Vectors.randomInUnitSphere().multiply(lensRadius);
        Vector offset = u.multiply(rd.getX()).add(v.multiply(rd.getY()));

        Vector vector = horizontal.multiply(s).add(vertical.multiply(t));

        return new Ray(origin.move(offset), new Vector(origin, lowerLeftCorner).add(vector).subtract(offset));
    }


    public Point getOrigin() {
        return origin;
    }

    public Point getLowerLeftCorner() {
        return lowerLeftCorner;
    }

    public Vector getHorizontal() {
        return horizontal;
    }

    public Vector getVertical() {
        return vertical;
    }
}
