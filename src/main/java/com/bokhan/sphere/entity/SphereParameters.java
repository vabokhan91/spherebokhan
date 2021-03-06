package com.bokhan.sphere.entity;

import com.bokhan.sphere.observer.IObserver;
import com.bokhan.sphere.observer.SphereEvent;
import com.bokhan.sphere.service.SphereParametersCalculator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereParameters implements IObserver {
    private final static Logger LOGGER = LogManager.getLogger();
    private double sphereSquare;
    private double sphereVolume;
    private String sphereVolumesRatio;
    private ArrayList<Sphere> observableSpheres = new ArrayList<>();

    public SphereParameters(Sphere sphere) {
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
        calculateSphereParameters(updatingSphere);
        LOGGER.log(Level.INFO, "Parameters of sphere " + updatingSphere.getId() + " were changed.  New parameters : " + this);
    }

    @Override
    public void removeObservable(Sphere sphere) {
        observableSpheres.remove(sphere);
    }

    public void addObservable(Sphere sphere) {
        observableSpheres.add(sphere);
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
                ", sphereVolume=" + sphereVolume + "\n" + sphereVolumesRatio +
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
