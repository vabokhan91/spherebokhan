package com.bokhan.sphere.entity;

import com.bokhan.sphere.creator.SphereCreator;
import com.bokhan.sphere.exception.IncorrectDataException;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NoFileException;
import com.bokhan.sphere.parser.SphereParser;
import com.bokhan.sphere.reader.ReaderFromFile;
import com.bokhan.sphere.service.SphereValidator;
import com.bokhan.sphere.storage.SphereStorage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 02.06.2017.
 */
public class SphereTest {
    private static Map<Sphere, SphereParameters> storage;
    private final static String FILE_NAME = "src/main/resources/data.txt";
    private static Sphere sphereForWrongRadius;
    private static SphereValidator validator;
    private static SphereParser parser;
    private static SphereCreator creator;
    private static ReaderFromFile reader;

    @BeforeClass
    public static void init() throws MissingDataException, NoFileException {
        sphereForWrongRadius = new Sphere(new Point(3, 7, 9), 9);
        reader = new ReaderFromFile();
        validator = new SphereValidator();
        parser = new SphereParser();
        creator = new SphereCreator();
        List<String> dataFromFile = reader.readDataFromFile(FILE_NAME);
        List<String> validatedData = validator.validateData(dataFromFile);
        Map<Point, Double> parsedData = parser.parseData(validatedData);
        Map<Sphere, SphereParameters> testMap = creator.createSphereAndParameters(parsedData);
        SphereStorage.getInstance().updateStorage(testMap);
        storage = SphereStorage.getInstance().getSphereStorage();
    }

    @Test
    public void setRadiusTest() throws IncorrectDataException {
        Sphere sphere = (Sphere) storage.keySet().toArray()[0];
        SphereParameters parameters = storage.get(sphere);
        sphere.setRadius(20);
        assertTrue(storage.containsKey(sphere) && storage.containsValue(parameters));
    }

    @Test
    public void setCentreTest() throws Exception {
        Sphere sphere = (Sphere) storage.keySet().toArray()[0];
        SphereParameters parameters = storage.get(sphere);
        sphere.setCentre(new Point(100, 200, 300));
        assertTrue(storage.containsKey(sphere) && storage.containsValue(parameters));
    }

    @Test(expected = IncorrectDataException.class)
    public void setWrongRadius() throws IncorrectDataException {
        sphereForWrongRadius.setRadius(-9);
    }

    @AfterClass
    public static void clearStorage() {
        storage.clear();
    }


}