package com.bokhan.sphere.storage;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 29.05.2017.
 */
public class SphereStorageTest {
    private static Map<Sphere, SphereParameters> mapOfSpheresAndParams;
    private static Sphere singleSphere;
    private static SphereParameters singleParameters;

    @BeforeClass
    public static void initMap() {
        singleSphere = new Sphere(new Point(5, 7, 15), 100);
        singleParameters = new SphereParameters(singleSphere);
        mapOfSpheresAndParams = new HashMap<>();
        Sphere sphere1 = new Sphere(new Point(7, 7, 7), Double.valueOf(9));
        SphereParameters params1 = new SphereParameters(sphere1);
        Sphere sphere2 = new Sphere(new Point(5.1, 7.5, 5.5), 4.6);
        SphereParameters params2 = new SphereParameters(sphere2);
        Sphere sphere4 = new Sphere(new Point(3, -4, -3), Double.valueOf(8));
        SphereParameters params4 = new SphereParameters(sphere4);
        Sphere sphere5 = new Sphere(new Point(3, 4, 5), Double.valueOf(2));
        SphereParameters params5 = new SphereParameters(sphere5);
        mapOfSpheresAndParams.put(sphere1, params1);
        mapOfSpheresAndParams.put(sphere2, params2);
        mapOfSpheresAndParams.put(sphere4, params4);
        mapOfSpheresAndParams.put(sphere5, params5);
    }

    @Test
    public void updateSphereStorageWithCollectionTest() {
        SphereStorage.getInstance().updateStorage(mapOfSpheresAndParams);
        Map<Sphere, SphereParameters> actual = SphereStorage.getInstance().getSphereStorage();
        assertEquals(mapOfSpheresAndParams, actual);
    }

    @Test
    public void updateSphereStorageWithSinglePairTest() {
        SphereStorage.getInstance().updateStorage(singleSphere, singleParameters);
        Map<Sphere, SphereParameters> actual = SphereStorage.getInstance().getSphereStorage();
        System.out.println(actual);
        assertTrue(actual.containsKey(singleSphere));
    }

    @After
    public void clear() {
        Map<Sphere, SphereParameters> storage = SphereStorage.getInstance().getSphereStorage();
        storage.clear();
    }

    @AfterClass
    public static void clearAllStorage() {
        Map<Sphere, SphereParameters> storage = SphereStorage.getInstance().getSphereStorage();
        storage.clear();
    }
}