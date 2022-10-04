import math.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void getByRGBInt() {
        Color color = Color.getByRGB(0xff00ff);
        assertEquals(color, new Color(1, 0, 1));
    }

    @Test
    void getByRGB() {
        Color color = Color.getByRGB(255, 0, 255);
        assertEquals(color, new Color(1, 0, 1));
    }

    @Test
    void scale() {
        Color color = new Color(0.5, 0.4, 0.3);
        assertEquals(color.scale(2), new Color(1, 0.8, 0.6));
    }
}