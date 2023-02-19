package raytracer.scene;

import camera.Camera;
import camera.ClearCamera;
import camera.MotionBlurCamera;
import material.Lambertian;
import math.Color;
import math.Colors;
import math.Point;
import math.Vector;
import objects.BVHNode;
import objects.Hittable;
import objects.MovingSphere;
import objects.Sphere;
import texture.ImageTexture;
import texture.Texture;
import util.collections.BoundableList;
import util.collections.HittableList;
import util.collections.impl.BoundableArrayList;
import util.collections.impl.HittableArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BallsScene extends Scene {
    @Override
    public void init() {
        Path earth = Paths.get("src", "main", "resources", "earthrealistic.jpg");
        Texture earthTexture = new ImageTexture(earth);


        boundableObjects.add(new MovingSphere(new Point(-1, 1, 0), new Point(1, 1, 0), 1, 2, 5, new Lambertian(Colors.PURPLE)));
        boundableObjects.add(new Sphere(new Point(0, -1000, 0), 1000, new Lambertian(new Color(0.8, 0.7, 0.8))));
        boundableObjects.add(new Sphere(new Point(0, 3, 0), 1, new Lambertian(earthTexture)));

        Camera camera = new ClearCamera(new Point(8, 4, 8), new Point(0, 2, 0), new Vector(0, 1, 0), 20, 16d / 9d);
        this.camera = new MotionBlurCamera(camera, 1, 2);
    }
}
