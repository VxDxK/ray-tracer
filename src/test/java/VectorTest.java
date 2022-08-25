
import org.junit.jupiter.api.Test;
import math.Vector;
import math.Point;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    @Test
    void constructorTest(){
        Point point1 = new Point(0, 0, 0);
        Point point2 = new Point(5, 1, 7);

        Vector vector = new Vector(point1, point2);

        Vector forTest = new Vector(5, 1, 7);
        assertEquals(vector, forTest);
    }

    @Test
    void multiply() {
        Vector vector = new Vector(5, 6, 10);
        Vector doubledVector = new Vector(10, 12, 20);
        assertEquals(vector.multiply(2), doubledVector);
        assertEquals(vector.length() * 2, doubledVector.length());
    }

    @Test
    void divide() {
        Vector vector = new Vector(5, 6, 10);
        Vector doubledVector = new Vector(10, 12, 20);
        assertEquals(doubledVector.divide(2), vector);
        assertEquals(doubledVector.length() / 2, vector.length());
    }

    @Test
    void unit() {
        Vector vector = new Vector(5, 3, 4);
        assertEquals(vector.unit().length(), 1.0);
    }

    @Test
    void negate() {
        Vector positive = new Vector(4, 6, 1);
        Vector negative = new Vector(-4, -6, -1);
        assertEquals(positive.negate(), negative);
        assertEquals(positive.length(), negative.length());
    }

    @Test
    void length() {
        Vector vector0 = new Vector();
        assertEquals(vector0.length(), 0.0);

        Vector vector1 = new Vector(1, 0, 0);
        assertEquals(vector1.length(), 1.0);
    }

    @Test
    void add() {
        Vector vector1 = new Vector(1, 5, -1);
        Vector vector2 = new Vector(5, -3, 64);
        Vector result = new Vector(6, 2, 63);
        assertEquals(vector1.add(vector2), result);
    }

    @Test
    void subtract() {
        Vector vector1 = new Vector(1, 5, -1);
        Vector vector2 = new Vector(5, -3, 64);
        Vector result1 = new Vector(-4, 8, -65);
        Vector result2 = new Vector(4, -8, 65);
        assertEquals(vector1.subtract(vector2), result1);
        assertEquals(vector2.subtract(vector1), result2);
    }
}