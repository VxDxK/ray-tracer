import math.Color;
import math.Point;
import math.Points;
import util.PerlinNoise;

import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;

import static util.Util.clamp;

public class Perlin {
    public static void main(String[] args) throws Exception{
        try (OutputStream stream = Files.newOutputStream(Paths.get("image.ppm"));
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream);
             BufferedWriter writer = new BufferedWriter(outputStreamWriter)){
            long start = System.currentTimeMillis();
            new Perlin(writer);
            System.out.println("Processing time: " + (((double)(System.currentTimeMillis() - start))/1000));
        }
        Convertor.main(args);
    }

    Perlin(BufferedWriter writer) throws Exception{
        int width = 100;
        int height = 100;

        PerlinNoise perlinNoise = new PerlinNoise();
        PerlinNoise r = new PerlinNoise();
        PerlinNoise g = new PerlinNoise();
        PerlinNoise b = new PerlinNoise();

        writer.write("P3\n" + width + " " + height + "\n255\n");
        for(int j = 0; j < height; j++){
            System.out.print("Lines remaining " + (height - j) + "\r");
            for(int i = 0; i < width; i++){
                Color red = new Color(1, 0, 0).scale(15 * r.turbulence(Points.scale(new Point(i, 0, j), 0.1), 1));
                Color green = new Color(0, 1, 0).scale(10 * g.turbulence(Points.scale(new Point(i, 0, j), 0.1), 1));
                Color blue = new Color(0, 0, 1).scale(10 * b.turbulence(Points.scale(new Point(i, 0, j), 0.1), 1));

                Color start = new Color();

                Color mixed = new Color(start.getRed() + red.getRed() + green.getRed() + blue.getRed(),
                        start.getGreen() + red.getGreen() + green.getGreen() + blue.getGreen(),
                        start.getBlue() + red.getBlue() + green.getBlue() + blue.getBlue());
                mixed = mixed.scale((1d/4d));
                double scaling = perlinNoise.noise(Points.scale(new Point(i, 0, j), 0.1));
                Color pixelColor = new Color(1, 1, 1).scale(scaling);
                writeColor(writer, mixed, x -> x);
            }
        }
        writer.flush();
    }

    private void writeColor(BufferedWriter writer, Color color, Function<Double, Double> gammaCorrectionFunction) throws IOException {
        double r = gammaCorrectionFunction.apply(color.getRed());
        double g = gammaCorrectionFunction.apply(color.getGreen());
        double b = gammaCorrectionFunction.apply(color.getBlue());
        writer.write((int)(256 * clamp(r, 0d, 0.999)) + " " + (int)(256 * clamp(g, 0d, 0.999)) + " " + (int)(256 * clamp(b, 0d, 0.999)) + '\n');
    }

}
