package math;

public class Ray {
    private Point origin = new Point();
    private Vector direction = new Vector();

    public Ray() {
    }

    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point at(double t){
        return origin.move(direction.multiply(t));
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
}
