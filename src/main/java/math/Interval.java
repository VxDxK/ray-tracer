package math;

import java.util.Objects;

public class Interval {
    private double min;
    private double max;

    public Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public Interval(Interval interval){
        this.min = interval.getMin();
        this.max = interval.getMax();
    }

    public Interval(Interval a, Interval b){
        this.min = Math.min(a.getMin(), b.getMin());
        this.max = Math.max(a.getMax(), b.getMax());
    }

    public boolean contains(double x){
        return min <= x && x <= max;
    }

    public Interval expand(double delta){
        return new Interval(min + delta/2, max + delta/2);
    }

    public Interval scale(double t){
        return new Interval(min * t, max * t);
    }

    public double size(){
        return max - min;
    }

    public Interval setMin(double min) {
        this.min = min;
        return this;
    }

    public Interval setMax(double max) {
        this.max = max;
        return this;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval interval)) return false;
        return Double.compare(interval.min, min) == 0 && Double.compare(interval.max, max) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return "Interval{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
