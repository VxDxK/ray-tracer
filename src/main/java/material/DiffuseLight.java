package material;

import math.*;
import texture.SolidColorTexture;
import texture.Texture;

public class DiffuseLight implements Material{
    private final Texture texture;

    public DiffuseLight(Color color) {
        this.texture = new SolidColorTexture(color);
    }

    public DiffuseLight(Texture texture) {
        this.texture = texture;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        scattered.setOrigin(new Point()).setDirection(new Vector());
        return false;
    }

    @Override
    public Color emitted(double u, double v, Point p) {
        return texture.value(u, v, p);
    }
}
