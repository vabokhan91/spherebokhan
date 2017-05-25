package storage;

import creator.SphereCreator;
import entity.Point;
import entity.Sphere;
import entity.SphereParameters;
import reader.ReaderFromFile;
import util.SphereParametersCalculator;
import util.SphereValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereStorage {
    private Map<Sphere, SphereParameters> sphereStorage = createSphereStorageMap();
    private static final String FILENAME = "src/main/resources/data.txt";

    private SphereStorage() {
    }

    private static class SphereStorageHolder{
        private final static SphereStorage INSTANCE = new SphereStorage();
    }

    private Map<Sphere, SphereParameters> createSphereStorageMap(){
        Map<Sphere, SphereParameters> sphereStorage = new HashMap<Sphere, SphereParameters>();
        ReaderFromFile readerFromFile = new ReaderFromFile();
        Map<Point, Double> data = readerFromFile.readDataFromFile(FILENAME);
        SphereCreator creator = new SphereCreator();
        List<Sphere> spheres = new ArrayList<Sphere>();
        SphereValidator validator = new SphereValidator();
        for(Map.Entry<Point, Double> entry : data.entrySet()){
            Sphere sphere = creator.createSphere(entry.getKey(), entry.getValue());
            if (validator.objectIsSphere(sphere)) {
                spheres.add(sphere);
            }
        }
        for (Sphere sphere : spheres) {
            SphereParameters parameters = new SphereParameters(sphere);
            sphere.addParams(parameters);
            sphereStorage.put(sphere, parameters);
        }
        return sphereStorage;
    }

    public static SphereStorage getInstance(){
        return SphereStorageHolder.INSTANCE;
    }

    public Map<Sphere, SphereParameters> getSphereStorage() {
        return sphereStorage;
    }
}
