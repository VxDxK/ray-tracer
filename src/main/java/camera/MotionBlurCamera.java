package camera;

import math.Ray;

public class MotionBlurCamera implements Camera{
    private final Camera camera;
    private final double shutterOpeningMoment;
    private final double shutterClosingMoment;

    public MotionBlurCamera(Camera camera, double shutterOpeningMoment, double shutterClosingMoment) {
        if(shutterClosingMoment < shutterOpeningMoment){
            throw new IllegalArgumentException("shutterClosingMoment can`t be less then shutterOpeningMoment");
        }
        this.camera = camera;
        this.shutterOpeningMoment = shutterOpeningMoment;
        this.shutterClosingMoment = shutterClosingMoment;
    }

    @Override
    public Ray getRay(double s, double t) {
        double shutterOpenMoment = (Math.random() * (shutterClosingMoment - shutterOpeningMoment)) + shutterOpeningMoment;
        return camera.getRay(s, t).setTimeMoment(shutterOpenMoment);
    }
}
