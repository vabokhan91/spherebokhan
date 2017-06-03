package com.bokhan.sphere.creator;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NoFileException;
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
        private static SphereValidator validator;
        private static SphereParser parser;
        private static SphereCreator creator;
        private static ReaderFromFile reader;
        private final static String FILE_NAME = "src/main/resources/data.txt";
        private static Map<Point, Double> emptyMap;
        private final static long EXPECTED_SIZE = 4;

        @BeforeClass
        public static void init() {
            emptyMap = new HashMap<>();
            reader = new ReaderFromFile();
            validator = new SphereValidator();
            parser = new SphereParser();
            creator = new SphereCreator();
        }

        @Test
        public void createSpheresAndParametersTest() throws NoFileException, MissingDataException {
            List<String> dataFromFile = reader.readDataFromFile(FILE_NAME);
            List<String> validatedData = validator.validateData(dataFromFile);
            Map<Point, Double> parsedData = parser.parseData(validatedData);
            Map<Sphere, SphereParameters> actual = creator.createSphereAndParameters(parsedData);
            assertEquals(EXPECTED_SIZE, actual.size());
        }

        @Test(expected = MissingDataException.class)
        public void createSpheresAndParametersEmptyMapTest() throws MissingDataException, NoFileException {
            Map<Sphere, SphereParameters> actual = creator.createSphereAndParameters(emptyMap);
        }

        @Test(expected = MissingDataException.class)
        public void createSpheresAndParametersNullTest() throws MissingDataException, NoFileException {
            Map<Sphere, SphereParameters> actual = creator.createSphereAndParameters(null);
        }
    }


    @RunWith(Parameterized.class)
    public static class ParametrizedPart {
        private final Sphere testingSphere;
        private final boolean expected;

        public ParametrizedPart(Sphere testingSphere, boolean expected) {
            this.testingSphere = testingSphere;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Object[]> spheresForTesting() {
            return Arrays.asList(new Object[][]{
                    {new Sphere(new Point(3, 5, 6), Double.valueOf(2)), true},
                    {new Sphere(new Point(7, 7, 7), Double.valueOf(9)), true},
                    {new Sphere(new Point(0, 0, 0), Double.valueOf(-12)), false},
                    {new Sphere(null, Double.valueOf(3)), false},
            });
        }

        @Test
        public void isSphereTest() {
            boolean actual = new SphereCreator().isSphere(testingSphere);
            assertEquals(expected, actual);
        }
    }


}