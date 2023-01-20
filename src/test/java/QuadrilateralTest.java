import material.Lambertian;
import math.Colors;
import math.Point;
import math.Vector;
import objects.BVHNode;
import objects.Quadrilateral;
import org.junit.jupiter.api.Test;
import util.collections.impl.BoundableArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuadrilateralTest {

    @Test
    void boundingBox() {
        Quadrilateral quadrilateral = new Quadrilateral(new Point(0, 0, 0), new Vector(2, 0, 0), new Vector(0, 0, 2), new Lambertian(Colors.RED));
        BVHNode bvhNode = new BVHNode(new BoundableArrayList() {{
            add(quadrilateral);
        }});
        System.out.println(bvhNode);
    }
}