import image.Image;
import image.ImageWriter;
import raytracer.RayTracer;
import raytracer.RayTracerConfig;
import raytracer.RayTracerLights;
import raytracer.scene.LightsScene;
import util.Dimension;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        ImageWriter writer = new ImageWriter(Math::sqrt);
//        RayTracer rayTracer = new RayTracerImpl(new RayTracerConfig(new Dimension(400, 16d/9d), 50, 50), new BallsScene());
        RayTracer rayTracer = new RayTracerLights(new RayTracerConfig(new Dimension(400, 16d / 9d), 50, 50), new LightsScene());
        long st = System.currentTimeMillis();
        Image image = rayTracer.render();
        System.out.printf("Processing time: %d seconds\n", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - st));
        writer.writeToFile(Paths.get("image.png"), image);
    }
}
