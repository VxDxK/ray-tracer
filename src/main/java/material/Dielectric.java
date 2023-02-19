package material;

import math.Ray;
import math.Vector;
import math.Vectors;
import math.Color;
import math.HitRecord;

public class Dielectric implements Material {
    private final double refractionIndex;
    private final Color albedo;

    public Dielectric(double refractionIndex) {
        this(refractionIndex, new Color(1, 1, 1));
    }

    @SuppressWarnings("I dont know is it right to add albedo")
    public Dielectric(double refractionIndex, Color albedo) {
        this.refractionIndex = refractionIndex;
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        attenuation.set(albedo);
        double refractionRatio = record.isFrontFace() ? (1.0 / refractionIndex) : refractionIndex;
        Vector unitDirection = rayIn.getDirection().unit();

        double cosTheta = Math.min(1d, Vectors.dot(unitDirection.negate(), record.getNormal()));
        double sinTheta = Math.sqrt(1d - cosTheta * cosTheta);
        boolean cannotRefract = refractionRatio * sinTheta > 1.0;

        Vector direction;
        if (cannotRefract || reflectance(cosTheta, refractionRatio) > Math.random()) {
            direction = Vectors.reflect(unitDirection, record.getNormal());
        } else {
            direction = Vectors.refract(unitDirection, record.getNormal(), refractionRatio);
        }
        scattered.setOrigin(record.getPoint()).setDirection(direction).setTimeMoment(rayIn.getTimeMoment());
        return true;
    }

    /**
     * @see <a href="https://en.wikipedia.org/wiki/Schlick%27s_approximation">Schlick approximation</a>
     */
    private double reflectance(double cos, double refRat) {
        double R0 = Math.pow((1d - refRat) / (1d + refRat), 2);
        return R0 + (1 - R0) * Math.pow((1 - cos), 5);
    }

}
