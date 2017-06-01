package com.bokhan.sphere.entity;

import com.bokhan.sphere.observer.IObserver;
import com.bokhan.sphere.observer.SphereEvent;
import com.bokhan.sphere.service.SphereParametersCalculator;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereParameters implements IObserver{
    private double sphereSquare;
    private double sphereVolume;
    private String sphereVolumesRatio;
    private List<Sphere> observableSpheres;

    public SphereParameters(Sphere sphere) {
        observableSpheres = new ArrayList<>();
        calculateSphereParameters(sphere);
    }

    private void calculateSphereParameters(Sphere sphere) {
        sphereSquare = SphereParametersCalculator.calculateSquare(sphere);
        sphereVolume = SphereParametersCalculator.calculateVolume(sphere);
        sphereVolumesRatio = SphereParametersCalculator.calculateSphereVolumesRatio(sphere);
    }

    @Override
    public void handleEvent(SphereEvent event) {
        Sphere updatingSphere = event.getSource();
        update(updatingSphere);
    }

    @Override
    public void removeObservable(Sphere sphere) {
        observableSpheres.remove(sphere);
    }

    public void addObservable(Sphere sphere) {
        observableSpheres.add(sphere);
    }

    private void update(Sphere sphere) {
        sphereSquare = SphereParametersCalculator.calculateSquare(sphere);
        sphereVolume = SphereParametersCalculator.calculateVolume(sphere);
        sphereVolumesRatio = SphereParametersCalculator.calculateSphereVolumesRatio(sphere);
    }

    public double getSphereSquare() {
        return sphereSquare;
    }

    public double getSphereVolume() {
        return sphereVolume;
    }

    public String getSphereVolumesRatio() {
        return sphereVolumesRatio;
    }

    @Override
    public String toString() {
        return "SphereParameters{" +
                "sphereSquare=" + sphereSquare +
                ", sphereVolume=" + sphereVolume + "\n" +sphereVolumesRatio +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SphereParameters that = (SphereParameters) o;

        if (Double.compare(that.sphereSquare, sphereSquare) != 0) return false;
        return Double.compare(that.sphereVolume, sphereVolume) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(sphereSquare);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sphereVolume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
