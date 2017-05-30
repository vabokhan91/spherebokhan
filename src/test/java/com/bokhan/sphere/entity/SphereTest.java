package com.bokhan.sphere.entity;

import com.bokhan.sphere.storage.SphereStorage;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 29.05.2017.
 */

public class SphereTest {
    private static Sphere changedRadiusSphere;
    private static Map<Sphere, SphereParameters> data;
    private static Sphere changingSphere;
    private static SphereParameters parameters1;

    @BeforeClass
    public static void initData() {
        data = SphereStorage.getInstance().getSphereStorage();
        changedRadiusSphere = new Sphere(new Point(7, 7, 7), 9);
        for (Sphere sphere : data.keySet()) {
            if (sphere.equals(changedRadiusSphere)) {
                changingSphere = sphere;
            }
        }
    }

    @Test
    public void setRadiusTest() {
        changingSphere.setRadius(10);
        parameters1 = new SphereParameters(changingSphere);
        assertEquals(true, data.containsKey(changingSphere) && data.containsValue(parameters1));

    }

    @Test
    public void setCentreTest() {
        changingSphere.setCentre(new Point(7, 8, 9));
        assertTrue(data.containsKey(changingSphere));
    }


}