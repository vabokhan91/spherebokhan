package com.bokhan.sphere.storage;

import com.bokhan.sphere.creator.SphereCreator;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NoFileException;
import com.bokhan.sphere.reader.ReaderFromFile;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereStorage {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Map<Sphere, SphereParameters> sphereStorage;
    private static final String FILENAME = "src/main/resources/data.txt";
    private static SphereStorage instance;

    private SphereStorage() {
        if(instance != null){
            throw new RuntimeException("You can not make another class instance");
        }
        sphereStorage = new HashMap<>();
        createSphereStorageMap();
    }

    private void createSphereStorageMap(){
        ReaderFromFile readerFromFile = new ReaderFromFile();
        List<String> unparsedData = null;
        List<Sphere> spheres = null;
        try {
            unparsedData = readerFromFile.readDataFromFile(FILENAME);
            spheres = SphereCreator.getSpheres(unparsedData);
        } catch (NoFileException e) {
            LOGGER.log(Level.ERROR, "Filename was not received");
        } catch (MissingDataException e) {
            LOGGER.log(Level.ERROR, "");
        }
        for (Sphere sphere : spheres) {
            SphereParameters params = new SphereParameters(sphere);
            addData(sphere,params);
        }
    }

    public static SphereStorage getInstance(){
        if(instance == null){
            synchronized (SphereStorage.class){
                if(instance == null){
                    instance = new SphereStorage();
                }
            }
        }
        return instance;
    }

    private void addData(Sphere sphere, SphereParameters parameters) {
        sphereStorage.put(sphere, parameters);
    }

    public void updateStorage(Sphere sphere, SphereParameters parameters) {
        sphereStorage.put(sphere, parameters);
        LOGGER.log(Level.INFO, "New data in storage " + sphereStorage);
    }

    public Map<Sphere, SphereParameters> getSphereStorage() {
        return sphereStorage;
    }
}
