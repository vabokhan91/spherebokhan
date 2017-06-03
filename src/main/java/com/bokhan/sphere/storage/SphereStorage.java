package com.bokhan.sphere.storage;

import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereStorage {
    private final static Logger LOGGER = LogManager.getLogger();
    private Map<Sphere, SphereParameters> sphereStorage;
    private static SphereStorage instance;

    private SphereStorage() {
        if (instance != null) {
            throw new RuntimeException("Instance of class already exists");
        }
        sphereStorage = new HashMap<>();
    }

    public static SphereStorage getInstance() {
        if (instance == null) {
            synchronized (SphereStorage.class) {
                if (instance == null) {
                    instance = new SphereStorage();
                }
            }
        }
        return instance;
    }

    public void updateStorage(Map<Sphere, SphereParameters> newData) {
        sphereStorage.putAll(newData);
        LOGGER.log(Level.INFO, "New data in storage " + newData);
    }

    public void updateStorage(Sphere sphere, SphereParameters parameters) {
        sphereStorage.put(sphere, parameters);
        LOGGER.log(Level.INFO, "New data in storage " + sphere + " " + parameters);
    }

    public Map<Sphere, SphereParameters> getSphereStorage() {
        return sphereStorage;
    }
}
