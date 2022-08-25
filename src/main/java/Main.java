import math.Point;
import math.Ray;
import math.Vector;
import util.collections.*;
import util.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception{
        try (OutputStream stream = Files.newOutputStream(Paths.get("image.ppm"));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter)){
            new Main(writer);
        }
    }

    public Main(BufferedWriter writer) throws IOException {
        //Image
        double aspectRatio = 16.0/9.0;
        int width = 400;
        int height = (int)(width / aspectRatio);
        int samplesPerPixel = 100;
        //World
        HittableList world = new HittableArrayList();
        world.add(new Sphere(new Point(0, 0, -1), 0.5));
        world.add(new Sphere(new Point(0, -100.5, -1), 100));
        world.add(new Sphere(new Point(0.5, 0.4, -1),0.4));

        //Camera
        Camera camera = new Camera(new Point(), 2d, 2d*aspectRatio, 1d);

        Random random = new Random();
        //Rendering
        writer.write("P3\n" + width + " " + height + "\n255\n");
        for(int j = height - 1; j > -1; j--){
            for(int i = 0; i < width; i++){
                Color pixelColor = new Color();
                for(int s = 0; s < samplesPerPixel; s++){
                    double u = ((double) i + random.nextDouble())/(width - 1);
                    double v = ((double) j + random.nextDouble())/(height - 1);
                    Ray ray = camera.getRay(u, v);
                    Color color = rayColor(ray, world);
                    pixelColor.setRed(pixelColor.getRed() + color.getRed())
                            .setGreen(pixelColor.getGreen() + color.getGreen())
                            .setBlue(pixelColor.getBlue() + color.getBlue());
                }
                writeColor(writer, pixelColor, samplesPerPixel);
            }

        }
        writer.flush();
    }

    private Color rayColor(Ray r, Hittable world){
        HitRecord record = new HitRecord();

        if(world.hit(r, 0,  Double.POSITIVE_INFINITY, record)){
            Vector normal = record.getNormal();
            return new Color(0.5 * (normal.getX() + 1), 0.5 * (normal.getY() + 1), 0.5 * (normal.getZ() + 1));
        }

        Vector unitDirection = r.getDirection().unit();

        double t = 0.5 * (unitDirection.getY() + 1.0);
        return new Color((1.0 - t) + (t * 0.5), (1.0 - t) + (t * 0.7), (1.0 - t) + (t));
    }

    private void writeColor(BufferedWriter writer, Color color, int samplesPerPixel) throws IOException {
        double r = color.getRed()/samplesPerPixel;
        double g = color.getGreen()/samplesPerPixel;
        double b = color.getBlue()/samplesPerPixel;

        writer.write((int)(256 * clamp(r, 0d, 0.999)) + " " + (int)(256 * clamp(g, 0d, 0.999)) + " " + (int)(256 * clamp(b, 0d, 0.999)) + '\n');
    }

    private double clamp(double x, double min, double max){
        if(x < min) return min;
        if(x > max) return max;
        return x;
    }

}
