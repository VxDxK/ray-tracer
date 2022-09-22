package material;

import math.Ray;
import math.Vector;
import math.Vectors;
import math.Color;
import util.HitRecord;

/**
 * @see <a href="https://en.wikipedia.org/wiki/Lambertian_reflectance">Lambertian reflection</a>
 */
public class Lambertian implements Material{
    private final Color albedo;

    public Lambertian(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        Vector scatteredDirection = record.getNormal().add(Vectors.randomUnitVector());
        if(scatteredDirection.nearZero()){
            scatteredDirection = record.getNormal();
        }
        scattered.setDirection(scatteredDirection).setOrigin(record.getPoint()).setTimeMoment(rayIn.getTimeMoment());
        attenuation.set(albedo);
        return true;
    }
}
