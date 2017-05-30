package com.bokhan.sphere.entity;

import com.bokhan.sphere.observer.IObserver;
import com.bokhan.sphere.observer.SphereEvent;
import com.bokhan.sphere.util.SphereParametersCalculator;

import java.util.EventObject;


/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereParameters {
    private double sphereSquare;
    private double sphereVolume;
    private String sphereVolumesRatio;
    private String crossingWithOXYRatio;
    private String crossingWithOXZRatio;
    private String crossingWithOYZRatio;

    public SphereParameters(Sphere sphere) {
        calculateSphereParameters(sphere);
    }

    private void calculateSphereParameters(Sphere sphere) {
        sphereSquare = SphereParametersCalculator.calculateSquare(sphere);
        sphereVolume = SphereParametersCalculator.calculateVolume(sphere);
        /*crossingWithOXYRatio = SphereParametersCalculator.calculateRatioWithOXY(sphere);
        crossingWithOXZRatio = SphereParametersCalculator.calculateRatioWithOXZ(sphere);
        crossingWithOYZRatio = SphereParametersCalculator.calculateRatioWithOYZ(sphere);*/
        sphereVolumesRatio = SphereParametersCalculator.calculateSphereVolumesRatio(sphere);
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

    public String getSphereVolumesRatio() {
        return sphereVolumesRatio;
    }

    public void setSphereVolumesRatio(String sphereVolumesRatio) {
        this.sphereVolumesRatio = sphereVolumesRatio;
    }

    @Override
    public String toString() {
        return "SphereParameters{" +
                "sphereSquare=" + sphereSquare +
                ", sphereVolume=" + sphereVolume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SphereParameters that = (SphereParameters) o;

        if (Double.compare(that.sphereSquare, sphereSquare) != 0) return false;
        return Double.compare(that.sphereVolume, sphereVolume) == 0;
    }
}
