import math.Point;
import math.Ray;
import math.Vector;
import util.camera.BlurCamera;
import util.camera.Camera;
import util.camera.ClearCamera;
import util.collections.*;
import util.*;
import util.material.Dielectric;
import util.material.Lambertian;
import util.material.Material;
import util.material.Metal;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Function;

import static util.Util.clamp;

public class Main {
    public static void main(String[] args) throws Exception{
        try (OutputStream stream = Files.newOutputStream(Paths.get("image.ppm"));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter)){
            long start = System.currentTimeMillis();
            new Main(writer);
            System.out.println("Processing time: " + (((double)(System.currentTimeMillis() - start))/1000));
        }
    }

    public Main(BufferedWriter writer) throws IOException {
        //Image
        double aspectRatio = 16.0/9.0;
        int width = 3840;
        int height = (int)(width / aspectRatio);
        int samplesPerPixel = 500;
        int depth = 50;
        //World
        HittableList world = new HittableArrayList();

        Material ground = new Lambertian(new Color(0.5, 0.5, 0.5));
        Material left = new Lambertian(new Color(0, 0, 1));
        Material center = new Metal(new Color(0.8, 0.6, 0.2));
        Material right = new Dielectric(2.4);

        world.add(new Sphere(new Point(0, -1000, 0), 1000, ground));

        world.add(new Sphere(new Point(-1.5, 1, 0), 1, left));
        world.add(new Sphere(new Point(0, 1, 0), 1, center));
        world.add(new Sphere(new Point(1.5, 1, 0), 1, right));
        Util.fillScene(world);
        //Camera
        Point lookFrom = new Point(13, 3, 13);
        Point lookAt = new Point();
        Vector worldNormal = new Vector(0, 1, 0);
        double fov = 20;
        double aperture = 0.1;
        double focusDist = new Vector(lookAt, lookAt).length();


        Camera camera = new ClearCamera(lookFrom, lookAt, worldNormal, fov, aspectRatio);
//        Camera camera = new BlurCamera(lookFrom, lookAt, worldNormal, fov, aspectRatio, aperture, focusDist);

        Random random = new Random();
        //Rendering
        writer.write("P3\n" + width + " " + height + "\n255\n");
        for(int j = height - 1; j > -1; j--){
            System.out.println("Lines remaining " + (j + 1));
            for(int i = 0; i < width; i++){
                Color pixelColor = new Color();
                for(int s = 0; s < samplesPerPixel; s++){
                    double u = ((double) i + random.nextDouble())/(width - 1);
                    double v = ((double) j + random.nextDouble())/(height - 1);
                    Ray ray = camera.getRay(u, v);
                    Color color = rayColor(ray, world, depth).scale(1d/samplesPerPixel);
                    pixelColor.setRed(pixelColor.getRed() + color.getRed())
                            .setGreen(pixelColor.getGreen() + color.getGreen())
                            .setBlue(pixelColor.getBlue() + color.getBlue());

                }
                writeColor(writer, pixelColor, Math::sqrt);
            }

        }
        writer.flush();
    }

    private Color rayColor(Ray r, Hittable world, int depth){
        if(depth <= 0){
            return new Color(0, 0, 0);
        }
        HitRecord record = new HitRecord();

        if(world.hit(r, 0.00001,  Double.POSITIVE_INFINITY, record)){
            Ray scattered = new Ray();
            Color attenuation = new Color();
            if(record.getMaterial().scatter(r, record, attenuation, scattered)){
                Color newColor = rayColor(scattered, world, depth - 1);
                return attenuation.setRed(attenuation.getRed() * newColor.getRed())
                        .setGreen(attenuation.getGreen() * newColor.getGreen())
                        .setBlue(attenuation.getBlue() * newColor.getBlue());
            }
            return new Color(0, 0, 0);
        }

        Vector unitDirection = r.getDirection().unit();
        double t = 0.5 * (unitDirection.getY() + 1.0);
        return new Color((1.0 - t) + (t * 0.5), (1.0 - t) + (t * 0.7), (1.0 - t) + (t));
    }


    private void writeColor(BufferedWriter writer, Color color, Function<Double, Double> gammaCorrectionFunction) throws IOException {
        double r = gammaCorrectionFunction.apply(color.getRed());
        double g = gammaCorrectionFunction.apply(color.getGreen());
        double b = gammaCorrectionFunction.apply(color.getBlue());

        writer.write((int)(256 * clamp(r, 0d, 0.999)) + " " + (int)(256 * clamp(g, 0d, 0.999)) + " " + (int)(256 * clamp(b, 0d, 0.999)) + '\n');
    }

}
