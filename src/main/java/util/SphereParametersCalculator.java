package util;

import entity.Sphere;

/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereParametersCalculator {
    private Sphere sphere;
    private static final double P = Math.PI;

    public double calculateSquare(Sphere sphere) {
        double radius = sphere.getRadius();
        double square = 4 * P * Math.pow(radius, 2);
        return square;
    }

    public double calculateVolume(Sphere sphere) {
        double radius = sphere.getRadius();
        double volume = 4 / 3 * P * Math.pow(radius, 3);
        return volume;
    }

    public void calculateVolumeRatio() {

    }
}
