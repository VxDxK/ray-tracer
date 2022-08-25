package util;

import math.Point;
import math.Ray;
import math.Vector;

public class Camera {
    private final Point origin;
    private final Point lowerLeftCorner;
    private final Vector horizontal;
    private final Vector vertical;

    public Camera(Point origin, double viewportHeight, double viewportWidth, double focalLength){
        this.origin = origin;
        this.horizontal = new Vector(viewportWidth, 0, 0);
        this.vertical = new Vector(0, viewportHeight, 0);
        Vector distanceVector = new Vector(0, 0, focalLength);
        this.lowerLeftCorner = origin.move(distanceVector.negate()).move(vertical.negate().divide(2)).move(horizontal.negate().divide(2));
    }

    public Ray getRay(double u, double v){
        Vector vectorU = horizontal.multiply(u);
        Vector vectorV = vertical.multiply(v);

        Vector resultUV = vectorU.add(vectorV);

        Vector fromOrigin = new Vector(origin, lowerLeftCorner.move(resultUV));
        return new Ray(origin, fromOrigin);
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
