package raytracer;

import camera.Camera;
import image.ArrayImage;
import image.Image;
import math.*;
import objects.Hittable;
import raytracer.scene.Scene;

import java.util.Random;

public class RayTracerLights implements RayTracer{
    private final Camera camera;
    private final Hittable world;

    private final RayTracerConfig config;
    private final Color[][] colorMatrix;

    public RayTracerLights (RayTracerConfig config, Scene scene){
        this.config = config;
        this.camera = scene.getCamera();
        this.world = scene.getWorld();
        this.colorMatrix = new Color[config.imageDimension().getWidth()][config.imageDimension().getHeight()];
    }
    @Override
    public Image render() {
        Random random = new Random();
        for (int j = 0; j < config.getHeight(); j++) {
            System.out.print("Lines remaining " + (config.getHeight() - j) + "\r");
            for (int i = 0; i < config.getWidth(); i++) {
                Color pixelColor = new Color();
                for (int s = 0; s < config.samplesPerPixel(); s++) {
                    double u = ((double) i + random.nextDouble())/(config.getWidth() - 1);
                    double v = ((double) j + random.nextDouble())/(config.getHeight() - 1);
                    Ray ray = camera.getRay(u, v);
                    Color color = rayColor(ray, config.depth()).scale(1d/config.samplesPerPixel());
                    pixelColor = Colors.add(pixelColor, color);
                }
                colorMatrix[i][j] = pixelColor;
            }
        }
        return new ArrayImage(colorMatrix);
    }

    private Color rayColor(Ray r, int aDepth){
        HitRecord record = new HitRecord();
        if(aDepth <= 0){
            return new Color(0, 0, 0);
        }
        if(!world.hit(r, new Interval(0.00001,  Double.POSITIVE_INFINITY), record)){
            return Colors.BLACK;
        }

        Ray scattered = new Ray();
        Color attenuation = new Color();
        Color colorFromEmission = record.getMaterial().emitted(record.getU(), record.getV(), record.getPoint());

        if(!record.getMaterial().scatter(r, record, attenuation, scattered)){
            return colorFromEmission;
        }

        Color colorFromScatter = Colors.multiply(rayColor(scattered, aDepth - 1), attenuation);

        return Colors.add(colorFromEmission, colorFromScatter);

    }
}
