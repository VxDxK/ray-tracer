package util.material;

import math.Ray;
import math.Vector;
import math.Vectors;
import util.Color;
import util.HitRecord;

public class Metal implements Material{
    private final Color albedo;
    //Для создания нечеткого отражения
    private final double fuzz;

    public Metal(Color albedo) {
        this.albedo = albedo;
        fuzz = 0d;
    }

    public Metal(Color albedo, double fuzz) {
        this.albedo = albedo;
        this.fuzz = fuzz;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        Vector reflected = Vectors.reflect(rayIn.getDirection().unit(), record.getNormal());
        scattered.setOrigin(record.getPoint());
        scattered.setDirection(reflected.add(Vectors.randomInUnitSphere().multiply(fuzz)));
        attenuation.set(albedo);
        return Vectors.dot(scattered.getDirection(), record.getNormal()) > 0;
    }
}
