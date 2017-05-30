package com.bokhan.sphere.creator;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.observer.SphereIObserver;
import com.bokhan.sphere.parser.SphereParser;
import com.bokhan.sphere.util.SphereValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by vbokh on 24.05.2017.
 */
public class SphereCreator {
    public static List<Sphere> getSpheres(List<String> unparsedData) throws MissingDataException {
        if (unparsedData == null || unparsedData.size() == 0) {
            throw new MissingDataException("No data was received for creating spheres");
        }
        List<Sphere> listOfSpheres = new ArrayList<>();
        SphereParser parser = new SphereParser();
        Map<Point, Double> parsedData = parser.parseDataFromReader(unparsedData);
        SphereValidator validator = new SphereValidator();
        for (Map.Entry<Point, Double> entry : parsedData.entrySet()) {
            if (validator.objectIsSphere(entry.getKey(), entry.getValue())) {
                Sphere sphere = new Sphere(entry.getKey(), entry.getValue());
                sphere.addObserver(new SphereIObserver());
                listOfSpheres.add(sphere);
            }
        }
        return listOfSpheres;
    }


}
