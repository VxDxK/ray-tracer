package material;

import math.Ray;
import math.Vector;
import math.Vectors;
import math.Color;
import texture.SolidColorTexture;
import texture.Texture;
import math.HitRecord;

/**
 * @see <a href="https://en.wikipedia.org/wiki/Lambertian_reflectance">Lambertian reflection</a>
 */
public class Lambertian implements Material {
    private final Texture albedo;

    public Lambertian(Color albedo) {
        this.albedo = new SolidColorTexture(albedo);
    }

    public Lambertian(Texture texture) {
        this.albedo = texture;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        Vector scatteredDirection = record.getNormal().add(Vectors.randomUnitVector());
        if (scatteredDirection.nearZero()) {
            scatteredDirection = record.getNormal();
        }
        scattered.setDirection(scatteredDirection).setOrigin(record.getPoint()).setTimeMoment(rayIn.getTimeMoment());
        attenuation.set(albedo.value(record.getU(), record.getV(), record.getPoint()));
        return true;
    }
}
