package math;

public class Ray {
    private Point origin = new Point();
    private Vector direction = new Vector();
    private double timeMoment = 0;

    public Ray() {
    }

    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray(Point origin, Vector direction, double timeMoment) {
        this(origin, direction);
        this.timeMoment = timeMoment;
    }

    public Point at(double t) {
        return origin.move(direction.multiply(t));
    }

    public Point getOrigin() {
        return origin;
    }

    public Ray setOrigin(Point origin) {
        this.origin = origin;
        return this;
    }

    public Vector getDirection() {
        return direction;
    }

    public Ray setDirection(Vector direction) {
        this.direction = direction;
        return this;
    }

    public double getTimeMoment() {
        return timeMoment;
    }

    public Ray setTimeMoment(double timeMoment) {
        this.timeMoment = timeMoment;
        return this;
    }
}
