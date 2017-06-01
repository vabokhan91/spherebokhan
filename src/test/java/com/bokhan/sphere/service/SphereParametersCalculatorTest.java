package com.bokhan.sphere.service;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by vbokh on 25.05.2017.
 */
@RunWith(Enclosed.class)
public class SphereParametersCalculatorTest {
    private static Sphere sphere;
    private final static String SPHERE = " Sphere ";
    private final static String OXY = " is crossed by OXY coordinate plane. Ratio of volumes of sections : ";
    private final static String OXZ = " is crossed by OXZ coordinate plane. Ratio of volumes of sections : ";
    private final static String OYZ = " is crossed by OYZ coordinate plane. Ratio of volumes of sections : ";
    private final static String NOCROSSING = " is not crossed by any coordinate plane";

    @RunWith(Parameterized.class)
    public static class ParametrizedPart {
        private final Sphere sphereForCheckingCrossing;
        private final boolean expected;

        public ParametrizedPart(Sphere sphereForCheckingCrossing, boolean expected) {
            this.sphereForCheckingCrossing = sphereForCheckingCrossing;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Object[]> crossingData() {
            return Arrays.asList(new Object[][] {
                    { new Sphere(new Point(3,5,6),Double.valueOf(2)), false},
                    { new Sphere(new Point(7,7,7),Double.valueOf(9)), true },
                    { new Sphere(new Point(3,5,6),Double.valueOf(4)), true },
                    { new Sphere(new Point(3,2,2),Double.valueOf(3)), true },
            });
        }

        @Test
        public void isSphereCrossedByCoordinatePlaneTest() {
            boolean actual = SphereParametersCalculator.isSphereCrossedByCoordinatePlane(sphereForCheckingCrossing);
            assertEquals(expected,actual);
        }
    }

    public static class NonParametrizedPart{
        @BeforeClass
        public static void initSphere() {
            sphere = new Sphere(new Point(7, 7, 7), Double.valueOf(9));
        }

        @Test
        public void calculateSquareTest(){
            double expected = 4 * Math.PI * Math.pow(9, 2);
            double actual = SphereParametersCalculator.calculateSquare(sphere);
            assertEquals(expected,actual,0.01);
        }

        @Test
        public void calculateVolumeTest(){
            double expected = 4 / 3 * Math.PI * Math.pow(9, 3);
            double actual = SphereParametersCalculator.calculateVolume(sphere);
            assertEquals(expected,actual,0.01);
        }

        @Test
        public void calculateSphereVolumesRatioCrossingWithThreeCoordinatePlanesTest() {
            double ratioWithOXZ = 0.05;
            double ratioWithOXY = 0.05;
            double ratioWithOYZ = 0.05;
            String expected = SPHERE + sphere.getId() + OXY + ratioWithOXY + SPHERE + sphere.getId() + OXZ + ratioWithOXZ + SPHERE + sphere.getId() + OYZ + ratioWithOYZ;
            String actual = SphereParametersCalculator.calculateSphereVolumesRatio(sphere);
            assertEquals(expected,actual);
        }

        @Test
        public void calculateSphereVolumesRatioCrossingWithOXYAndOXZCoordinatePlanesTest() {
            Sphere sphereOXYOXZCrossing = new Sphere(new Point(8, 5, 6), Double.valueOf(7));
            double ratioOXY = 0.02;
            double ratioOXZ = 0.09;
            String expected = SPHERE + sphereOXYOXZCrossing.getId() + OXY + ratioOXY + SPHERE + sphereOXYOXZCrossing.getId() + OXZ + ratioOXZ;
            String actual = SphereParametersCalculator.calculateSphereVolumesRatio(sphereOXYOXZCrossing);
            assertEquals(expected,actual);
        }

        @Test
        public void calculateSphereVolumesRatioCrossingWithOYZCoordinatePlaneTest() {
            Sphere sphereOYZ = new Sphere(new Point(3, 7, -5), Double.valueOf(4));
            double ratioOYZ = 0.07;
            String expected = SPHERE + sphereOYZ.getId() + OYZ + ratioOYZ;
            String actual = SphereParametersCalculator.calculateSphereVolumesRatio(sphereOYZ);
            assertEquals(expected, actual);
        }

        @Test
        public void calculateSphereVolumesRatioNoCrossingTest() {
            Sphere noCrossingSphere = new Sphere(new Point(5, 6, 8), Double.valueOf(3));
            String expected = SPHERE + noCrossingSphere.getId() + NOCROSSING;
            String actual = SphereParametersCalculator.calculateSphereVolumesRatio(noCrossingSphere);
            assertEquals(expected,actual);
        }
    }






}