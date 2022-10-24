import camera.BlurCamera;
import camera.MotionBlurCamera;
import image.Image;
import material.*;
import math.*;
import camera.Camera;
import camera.ClearCamera;
import objects.*;
import math.HitRecord;
import texture.ImageTexture;
import texture.Texture;
import util.collections.impl.BoundableArrayList;
import util.collections.impl.HittableArrayList;
import util.collections.HittableList;

import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
        BoundableArrayList world = new BoundableArrayList();
        Path earth = Paths.get("src", "main", "resources", "earthrealistic.jpg");
        Texture earthTexture = new ImageTexture(earth);

//        world.add(new Sphere(new Point(0, -1000, 0), 999, new Metal(new Color(1, 1, 1), 0.5)));
//        world.add(new Sphere(new Point(0, -1000, 0), 999, new Metal(new Color(0.1, 0.1, 0.1), 0.5)));
//        Boundable boundable = new Quadrilateral(new Point(0, 0, 0), new Vector(1, 4, 0), new Vector(0, 0.5, 1), new Lambertian(Colors.BLUE));

        if(false){
            listWorld.add(new Quadrilateral(new Point(-2,-3,6), new Vector(0,0,-6), new Vector(0,6,0), new Lambertian(Colors.RED)));
            listWorld.add(new Quadrilateral(new Point(-2,-3,0), new Vector(6,0,0), new Vector(0,7,0), new Lambertian(Colors.WHITE)));
            listWorld.add(new Quadrilateral(new Point(-2,3,0), new Vector(6,0,0), new Vector(0,0,6), new Lambertian(Colors.GREEN)));
            listWorld.add(new Quadrilateral(new Point(-2,-3,6), new Vector(6,0,0), new Vector(0,0,-6), new Lambertian(Colors.BLACK)));
        }else{
            double fuzz = 0.0;
            listWorld.add(new Quadrilateral(new Point(-2,-3,6), new Vector(0,0,-6), new Vector(0,6,0), new Metal(Colors.WHITE, fuzz)));
//            listWorld.add(new Quadrilateral(new Point(-2,-3,0), new Vector(6,0,0), new Vector(0,7,0), new Metal(Colors.WHITE, fuzz)));
            listWorld.add(new Quadrilateral(new Point(-2,3,0), new Vector(6,0,0), new Vector(0,0,6), new Metal(Colors.WHITE, fuzz)));
            listWorld.add(new Quadrilateral(new Point(-2,-3,6), new Vector(6,0,0), new Vector(0,0,-6), new Metal(Colors.WHITE, fuzz)));
        }
        listWorld.add(new Sphere(new Point(0, -1000, 0), 997, new Lambertian(new Color(0.7, 0.7, 0.8))));
        listWorld.add(new Sphere(new Point(2, 0, 3), 1, new Lambertian(Colors.PURPLE)));



        //        world.add(new Sphere(new Point(0.5, 0, 0.5), 1, new Metal(Colors.RED)));
//        world.add(new Sphere(new Point(0.5, 0, 3.5), 1, new Dielectric(1.5, Colors.WHITE)));
//        world.add(new Sphere(new Point(), 1, new Lambertian(earthTexture)));
//        world.add(new Sphere(new Point(), 1, new Metal(new Color(20, 1, 20))));
        BVHNode node = new BVHNode(world);
        listWorld.add(node);

//        listWorld.add(new Quadrilateral(new Point(0, -1, 0), new Vector(1, 0, 0), new Vector(0, 0, 1), new Metal(Colors.BLACK)));
//        listWorld.add(new Plane(new Point(0, -1, 0), new Point(1, -1, 0), new Point(0, -1, 1), new Metal(Colors.WHITE)));

        Point lookFrom = new Point(4, 1, 9);
        Point lookAt = new Point(0, 0, 0);
        Vector worldNormal = new Vector(0, 1, 0);
        double fov = 80;
        double aperture = 0.1;
        double focusDist = 10;

        Camera cameraClear = new ClearCamera(lookFrom, lookAt, worldNormal, fov, aspectRatio);
        Camera cameraBlur = new BlurCamera(lookFrom, lookAt, worldNormal, fov, aspectRatio, aperture, focusDist);

        Camera camera = new MotionBlurCamera(cameraClear, 0, 1);

        Random random = new Random();
        //Rendering
        writer.write("P3\n" + width + " " + height + "\n255\n");
        for (int j = 0; j < height; j++) {
            System.out.print("Lines remaining " + (height - j) + "\r");
            for (int i = 0; i < width; i++){
                Color pixelColor = new Color();
                for (int s = 0; s < samplesPerPixel; s++) {
                    double u = ((double) i + random.nextDouble())/(width - 1);
                    double v = ((double) j + random.nextDouble())/(height - 1);
                    Ray ray = camera.getRay(u, v);
                    Color color = rayColor(ray, listWorld, depth).scale(1d/samplesPerPixel);
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
        if (depth <= 0) {
            return new Color(0, 0, 0);
        }
        HitRecord record = new HitRecord();

        if(world.hit(r, new Interval(0.00001,  Double.POSITIVE_INFINITY), record)){
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
