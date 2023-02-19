import math.Point;
import math.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void move() {
        Point point = new Point(5, -1, 10);
        Vector vector = new Vector(6, 6, -1);
        Point moved = new Point(11, 5, 9);
        assertEquals(point.move(vector), moved);
    }
}