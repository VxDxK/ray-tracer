import org.junit.jupiter.api.Test;
import math.Vector;
import math.Vectors;

import static org.junit.jupiter.api.Assertions.*;

class VectorsTest {

    @Test
    void dot() {
        Vector vector1 = new Vector(5, -1, 4);
        Vector vector2 = new Vector(4, 1, -5);
        double result = Vectors.dot(vector1, vector2);
        assertEquals(result, -1);

        assertEquals(Vectors.dot(new Vector(1, 0, 0), new Vector(0, 1, 0)), 0);

    }

    @Test
    void cross() {
        Vector vector1 = new Vector(5, -1, 4);
        Vector vector2 = new Vector(4, 1, -5);

        Vector result = Vectors.cross(vector1, vector2);

        assertEquals(result, new Vector(1, 41, 9));

    }

    @Test
    void reflect() {
        Vector vector = new Vector(3, -3, 0);
        Vector normal = new Vector(0, 1, 0);
        assertEquals(Vectors.reflect(vector, normal), new Vector(3, 3, 0));
    }

    @Test
    void refract() {

    }
}