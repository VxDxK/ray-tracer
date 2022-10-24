package math;

public class Colors {
    public final static Color WHITE = new ImmutableColor(1, 1, 1);
    public final static Color RED = new ImmutableColor(1, 0, 0);
    public final static Color GREEN = new ImmutableColor(0, 1, 0);
    public final static Color BLUE = new ImmutableColor(0, 0, 1);
    public final static Color BLACK = new ImmutableColor(0, 0, 0);
    public final static Color PURPLE = ImmutableColor.getByRGB(128,0,128);
    public static Color add(Color a, Color b){
        return new Color(a.getRed() + b.getRed(), a.getGreen() + b.getGreen(), a.getBlue() + b.getBlue());
    }

    public static class ImmutableColor extends Color{

        public ImmutableColor() {
        }

        public ImmutableColor(double red, double green, double blue) {
            super(red, green, blue);
        }

        public ImmutableColor(Color color) {
            super(color);
        }

        @Override
        public Color setRed(double red) {
            return this;
        }

        @Override
        public Color setBlue(double blue) {
            return this;
        }

        @Override
        public Color setGreen(double green) {
            return this;
        }
    }
}
