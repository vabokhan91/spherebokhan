package com.bokhan.sphere.util;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by vbokh on 25.05.2017.
 */
@RunWith(Parameterized.class)
public class SphereValidatorTest {
    private final Sphere sphere;
    private final boolean expected;
    private static SphereValidator validator;

    public SphereValidatorTest(Sphere sphere, boolean expected) {
        this.sphere = sphere;
        this.expected = expected;
    }

    @BeforeClass
    public static void initValidator() {
        validator = new SphereValidator();
    }
    @Test
    public void objectIsSphereTest() {
        assertEquals(expected,validator.objectIsSphere(sphere.getCentre(),sphere.getRadius()));
    }

    @Parameterized.Parameters
    public static List<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {new Sphere(new Point(3, 4, 5), 2), true},
                {new Sphere(new Point(-2, -7, 5), -4), false},
                {new Sphere(new Point(4, -2, 8), 0.1), true},
                {new Sphere(new Point(5, 8, -7), -7), false}});
    }
}