package raytracer.scene;

import camera.Camera;
import camera.ClearCamera;
import camera.MotionBlurCamera;
import material.DiffuseLight;
import material.Lambertian;
import material.Material;
import math.Color;
import math.Colors;
import math.Point;
import math.Vector;
import objects.Quad;
import objects.Quadrilateral;
import objects.Sphere;
import texture.MarbleTexture;
import texture.PerlinTexture;
import texture.Texture;
import util.PerlinNoise;

public class LightsScene extends Scene{
    @Override
    public void init() {
        Texture perlin = new MarbleTexture();
        boundableObjects.add(new Sphere(new Point(0, -1000, 0), 1000, new Lambertian(perlin)));
        boundableObjects.add(new Sphere(new Point(0, 2, 0), 2, new Lambertian(perlin)));

        Material diffuseLight = new DiffuseLight(new Color(1, 0.2, 0.4).scale(5));
        boundableObjects.add(new Quadrilateral(new Point(3, 1, -2), new Vector(2, 0, 0), new Vector(0, 2, 0), diffuseLight));
//        boundableObjects.add(new Quadrilateral(new Point(3, 1, -2), new Vector(2, 0, 0), new Vector(0, 2, 0), new Lambertian(new Color(2, 0.4, 1))));

        Camera camera = new ClearCamera(new Point(26, 3, 6), new Point(0, 2, 0), new Vector(0, 1, 0), 20, 16d/9d);
//        Camera camera = new ClearCamera(new Point(26, 3, 26), new Point(0, 2, 0), new Vector(0, 1, 0), 20, 16d/9d);
        this.camera = new MotionBlurCamera(camera, 1, 2);
    }
}
