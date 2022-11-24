package raytracer.scene;

import camera.Camera;
import objects.BVHNode;
import objects.Hittable;
import util.collections.BoundableList;
import util.collections.HittableList;
import util.collections.impl.BoundableArrayList;
import util.collections.impl.HittableArrayList;

public abstract class Scene {
    protected Camera camera;
    protected final HittableList world = new HittableArrayList();
    protected final BoundableList boundableObjects = new BoundableArrayList();

    public Scene() {
        init();
        BVHNode bvhNode = new BVHNode(boundableObjects);
        world.add(bvhNode);
    }

    public abstract void init();

    public Camera getCamera() {
        return camera;
    }
    public Hittable getWorld() {
        return world;
    }
}
