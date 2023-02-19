package camera;

import math.Point;
import math.Ray;
import math.Vector;
import math.Vectors;


public class ClearCamera implements Camera {
    private final Point origin;
    private final Point lowerLeftCorner;
    private final Vector horizontal;
    private final Vector vertical;

    public ClearCamera(Point lookFrom, Point lookAt, Vector worldNormal, double verticalFov, double aspectRatio) {
        double theta = Math.toRadians(verticalFov);
        double h = Math.tan(theta / 2);
        double viewportHeight = 2.0 * h;
        double viewportWidth = aspectRatio * viewportHeight;

        double focalLength = 1.0;
        Vector w = new Vector(lookAt, lookFrom).unit();
        Vector u = Vectors.cross(worldNormal, w).unit();
        Vector v = Vectors.cross(w, u);

        this.origin = lookFrom;
        this.horizontal = u.multiply(viewportWidth);
        this.vertical = v.multiply(viewportHeight);
        this.lowerLeftCorner = origin.move(vertical.negate().divide(2)).move(horizontal.negate().divide(2)).move(w.negate());

    }

    @Override
    public Ray getRay(double s, double t) {
        Vector vector = horizontal.multiply(s).add(vertical.multiply(1 - t));
        return new Ray(origin, new Vector(origin, lowerLeftCorner).add(vector));
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
