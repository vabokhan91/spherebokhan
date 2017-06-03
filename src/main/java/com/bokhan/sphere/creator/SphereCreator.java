package com.bokhan.sphere.creator;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import com.bokhan.sphere.exception.MissingDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vbokh on 24.05.2017.
 */
public class SphereCreator {
    private final static Logger LOGGER = LogManager.getLogger();

    public Map<Sphere, SphereParameters> createSphereAndParameters(Map<Point, Double> parsedData) throws MissingDataException {
        if (parsedData == null || parsedData.size() == 0) {
            throw new MissingDataException("No data was received for creating spheres");
        }
        Map<Sphere, SphereParameters> sphereAndParameters = new HashMap<>();
        for (Map.Entry<Point, Double> entry : parsedData.entrySet()) {
            Sphere sphere = new Sphere(entry.getKey(), entry.getValue());
            if (isSphere(sphere)) {
                SphereParameters parameters = new SphereParameters(sphere);
                sphere.addObserver(parameters);
                sphereAndParameters.put(sphere, parameters);
            }
        }
        return sphereAndParameters;
    }

    public boolean isSphere(Sphere sphere) {
        if (sphere.getRadius() > 0 && sphere.getCentre() != null) {
            LOGGER.log(Level.INFO, sphere + " is sphere. Radius = " + sphere.getRadius() + ", coordinates : " + sphere.getCentre());
            return true;
        }
        LOGGER.log(Level.INFO, sphere + " is not a sphere. Radius = " + sphere.getRadius() + ", coordinates : " + sphere.getCentre());
        return false;
    }
}
