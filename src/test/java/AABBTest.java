import math.Interval;
import math.Point;
import objects.AABB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AABBTest {
    @Test
    void testIntervals(){
        AABB aabb = new AABB(new Point(1, 2, 3), new Point(3, 4, 5));
        Interval x = aabb.getX();
        Interval y = aabb.getY();
        Interval z = aabb.getZ();
        assertEquals(x, new Interval(1, 3));
        assertEquals(y, new Interval(2, 4));
        assertEquals(z, new Interval(3, 5));

    }
}