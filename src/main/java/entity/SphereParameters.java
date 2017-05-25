package entity;

import util.SphereParametersCalculator;


/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereParameters {
    private double sphereSquare;
    private double sphereVolume;
    private double sphereVolumeRatio;
    private SphereParametersCalculator calculator;
    private Sphere sphere;

    public SphereParameters(Sphere sphere) {
        this.sphere = sphere;
        calculator = new SphereParametersCalculator();
        sphereSquare = calculator.calculateSquare(sphere);
        sphereVolume = calculator.calculateVolume(sphere);
    }

    public void update(Sphere sphere) {
        this.sphere=sphere;
        sphereSquare = calculator.calculateSquare(sphere);
        sphereVolume = calculator.calculateVolume(sphere);
    }

    public double getSphereSquare() {
        return sphereSquare;
    }

    public void setSphereSquare(double sphereSquare) {
        this.sphereSquare = sphereSquare;
    }

    public double getSphereVolume() {
        return sphereVolume;
    }

    public void setSphereVolume(double sphereVolume) {
        this.sphereVolume = sphereVolume;
    }

    public double getSphereVolumeRatio() {
        return sphereVolumeRatio;
    }

    public void setSphereVolumeRatio(double sphereVolumeRatio) {
        this.sphereVolumeRatio = sphereVolumeRatio;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public SphereParametersCalculator getCalculator() {
        return calculator;
    }

    @Override
    public String toString() {
        return "SphereParameters{" +
                "sphereSquare=" + sphereSquare +
                ", sphereVolume=" + sphereVolume +
                ", sphereVolumeRatio=" + sphereVolumeRatio+
                '}';
    }
}
