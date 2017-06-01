package com.bokhan.sphere.creator;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NullFileException;
import com.bokhan.sphere.parser.SphereParser;
import com.bokhan.sphere.reader.ReaderFromFile;
import com.bokhan.sphere.service.SphereValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Created by vbokh on 29.05.2017.
 */
@RunWith(Enclosed.class)
public class SphereCreatorTest {

    public static class NonParametrizedPart {
        private static Map<Sphere, SphereParameters> spheres;
        private static SphereValidator validator;
        private static SphereParser parser;
        private static SphereCreator creator;
        private static ReaderFromFile reader;
        private final static String FILENAME = "src/main/resources/data.txt";
        private Map<Point, Double> emptyMap = null;

        @BeforeClass
        public static void init() {
            reader = new ReaderFromFile();
            spheres = new HashMap<>();
            validator = new SphereValidator();
            parser = new SphereParser();
            creator = new SphereCreator();
            Sphere sphere1 = new Sphere(new Point(7, 7, 7), 9);
            SphereParameters params1 = new SphereParameters(sphere1);
            sphere1.addObserver(params1);
            Sphere sphere2 = new Sphere(new Point(5.1, 7.5, 5.5), 4.6);
            SphereParameters params2 = new SphereParameters(sphere2);
            sphere2.addObserver(params2);
            Sphere sphere4 = new Sphere(new Point(3, -4, -3), 8);
            SphereParameters params4 = new SphereParameters(sphere4);
            sphere4.addObserver(params4);
            Sphere sphere5 = new Sphere(new Point(3, 4, 5), 2);
            SphereParameters params5 = new SphereParameters(sphere5);
            sphere5.addObserver(params5);
            spheres.put(sphere1, params1);
            spheres.put(sphere2, params2);
            spheres.put(sphere4, params4);
            spheres.put(sphere5, params5);
        }

        @Test
        public void createSpheresAndParametersTest() throws NullFileException,MissingDataException{
            Map<Sphere, SphereParameters> actual = creator.createSpheresAndParameters(parser.parseListOfData(validator.validateData(reader.readDataFromFile(FILENAME))));
            assertEquals(spheres,actual);

        }
        @Test(expected = MissingDataException.class)
        public void getSpheresNullTest() throws MissingDataException, NullFileException {
            Map<Sphere, SphereParameters> actual = creator.createSpheresAndParameters(emptyMap);
        }
    }


    @RunWith(Parameterized.class)
    public static class ParametrizedPart{
        private final Sphere testingSphere;
        private final boolean expected;

        public ParametrizedPart(Sphere testingSphere, boolean expected) {
            this.testingSphere = testingSphere;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Object[]> spheresForTesting() {
            return Arrays.asList(new Object[][] {
                    { new Sphere(new Point(3,5,6),Double.valueOf(2)), true},
                    { new Sphere(new Point(7,7,7),Double.valueOf(9)), true },
                    { new Sphere(new Point(0,0,0),Double.valueOf(-12)), false },
                    { new Sphere(null,Double.valueOf(3)), false },
            });
        }
        
        @Test
        public void isSphereTest() {
            boolean actual = new SphereCreator().isSphere(testingSphere);
            assertEquals(expected,actual);
        }
    }



}