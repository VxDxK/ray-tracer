package material;

import math.Ray;
import math.Vector;
import math.Vectors;
import math.Color;
import texture.SolidColorTexture;
import texture.Texture;
import util.HitRecord;

public class Metal implements Material{
    private final Texture albedo;
    //Для создания нечеткого отражения
    private final double fuzz;

    public Metal(Color albedo) {
        this.albedo = new SolidColorTexture(albedo);
        fuzz = 0d;
    }


    public Metal(Color albedo, double fuzz) {
        this.albedo = new SolidColorTexture(albedo);
        this.fuzz = fuzz;
    }

    public Metal(Texture texture){
        this.albedo = texture;
        this.fuzz = 0d;
    }

    public Metal(Texture texture, double fuzz){
        this.albedo = texture;
        this.fuzz = fuzz;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        Vector reflected = Vectors.reflect(rayIn.getDirection().unit(), record.getNormal());
        scattered.setOrigin(record.getPoint())
                .setDirection(reflected.add(Vectors.randomInUnitSphere().multiply(fuzz)))
                .setTimeMoment(rayIn.getTimeMoment());
        attenuation.set(albedo.value(record.getU(), record.getV(), record.getPoint()));
        return Vectors.dot(scattered.getDirection(), record.getNormal()) > 0;
    }
}
