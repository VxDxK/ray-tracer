import math.Interval;
import math.Point;
import math.Vector;
import objects.AABB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AABBTest {
    @Test
    void testIntervals() {
        AABB aabb = new AABB(new Point(1, 2, 3), new Point(3, 4, 5));
        Interval x = aabb.getX();
        Interval y = aabb.getY();
        Interval z = aabb.getZ();
        assertEquals(x, new Interval(1, 3));
        assertEquals(y, new Interval(2, 4));
        assertEquals(z, new Interval(3, 5));

    }

    @Test
    void padTest() {
        AABB aabb = new AABB(new Point(0, 0, 0), new Point(0, 0, 0).move(new Vector(0, 0, 2)).move(new Vector(2, 0, 0)));
        AABB padded = aabb.pad();
        assertNotEquals(padded.getY().size(), 0d);
    }
}