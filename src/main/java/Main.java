import camera.BlurCamera;
import camera.MotionBlurCamera;
import math.*;
import camera.Camera;
import camera.ClearCamera;
import material.Dielectric;
import material.Lambertian;
import material.Material;
import material.Metal;
import objects.*;
import texture.CheckerTexture;
import texture.ImageTexture;
import texture.SolidColorTexture;
import texture.Texture;
import math.HitRecord;
import util.collections.impl.BoundingArrayList;
import util.collections.impl.HittableArrayList;
import util.collections.HittableList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Convertor.main(args);
    }

    public Main(BufferedWriter writer) throws IOException {
        //Image
        double aspectRatio = 16.0/9.0;
        int width = 400;
        int height = (int)(width / aspectRatio);
        int samplesPerPixel = 100;
        int depth = 50;
        //World
        HittableList listWorld = new HittableArrayList();
        BoundingArrayList world = new BoundingArrayList();
        Path earth = Paths.get("src", "main", "resources", "earthrealistic.jpg");
//        Path earth = Paths.get("src", "main", "resources", "earthmap.jpg");

        Texture earthTexture = new ImageTexture(earth);
        Texture groundChecker = new CheckerTexture(earthTexture, new SolidColorTexture(new Color(0.3, 0.2, 0.6)));
//        Texture groundChecker = new CheckerTexture(new Color(0.2, 0.3, 0.1), new Color(0.9, 0.9, 0.9));
//        Material ground = new Lambertian(new Color(0.5, 0.5, 0.5));

        Material ground = new Lambertian(groundChecker);
        Material left = new Metal(new Color(1, 1, 1));
        Material center = new Metal(new Color(0.8, 0.6, 0.2));
        Material right = new Dielectric(1.5);

        Material metalFuzz = new Metal(new Color(1, 1, 1), 1.0);

        Material metalFuzz1 = new Metal(new Color(1, 1, 1));
        world.add(new Sphere(new Point(0, -1001, 0), 1000, ground));
//        world.add(new Sphere(new Point(0, -1000, 0), 1000, new Metal(new Color(1, 1, 1), 0.1)));

//        world.add(new Sphere(new Point(-4, 0, 0), 1, left));
//        world.add(new Sphere(new Point(-2, 0, 0), 1, left));
//        world.add(new Sphere(new Point(-5, 0, -5), 1, left));
//        world.add(new Sphere(new Point(2, 0, -5), 1, left));
//        world.add(new Sphere(new Point(-2, 0, -5), 1, left));
//        world.add(new Sphere(new Point(0, 1, 0), 1, new Lambertian(new CheckerTexture(new Color(1, 1, 1), new Color(0.8, 0.6, 0.2)))));
//        world.add(new Sphere(new Point(0, 1, 0), 1, new Metal(earthTexture)));
        Sphere centralSphere = new Sphere(new Point(0, 0, 0), 1, new Lambertian(earthTexture));
        world.add(centralSphere);

//        world.add(new Sphere(new Point(0, 1, 0), 1, new Lambertian(new CheckerTexture(groundChecker, new SolidColorTexture( new Color(0.8, 0.6, 0.2))))));

//        world.add(new MovingSphere(new Point(0, 0, 0), new Point(0, 2, 0), 1, 0, 1, left));
//        world.add(new Sphere(new Point(2, 0, 0), 1, right));
//        Util.fillScene(world);

        BVHNode bvhNode = new BVHNode(world, 0, 0);
        listWorld.add(bvhNode);
//        System.exit(-1);
        //Camera
        Point lookFrom = new Point(13, 5, 13);
//        Point lookFrom = new Point(0, 5, 13);
        Point lookAt = new Point(0, 1, 0);
        Vector worldNormal = new Vector(0, 1, 0);
        double fov = 20;
        double aperture = 0.1;
        double focusDist = new Vector(lookAt, lookFrom).length();

        Camera cameraClear = new ClearCamera(lookFrom, lookAt, worldNormal, fov, aspectRatio);
        Camera cameraBlur = new BlurCamera(lookFrom, lookAt, worldNormal, fov, aspectRatio, aperture, focusDist);

        Camera camera = new MotionBlurCamera(cameraClear, 0, 0);

        Random random = new Random();
        //Rendering
        writer.write("P3\n" + width + " " + height + "\n255\n");
        for(int j = height - 1; j > -1; j--){
            System.out.print("Lines remaining " + (j + 1) + "\r");
            for(int i = 0; i < width; i++){
                Color pixelColor = new Color();
                for(int s = 0; s < samplesPerPixel; s++){
                    double u = ((double) i + random.nextDouble())/(width - 1);
                    double v = ((double) j + random.nextDouble())/(height - 1);
                    Ray ray = camera.getRay(u, v);
                    Color color = rayColor(ray, listWorld, depth).scale(1d/samplesPerPixel);
//                    Color color = Util.normalColor(ray, world).scale(1d/samplesPerPixel);
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
