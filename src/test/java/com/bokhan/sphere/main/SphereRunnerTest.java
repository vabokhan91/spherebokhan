package com.bokhan.sphere.main;

import com.bokhan.sphere.creator.SphereCreator;
import com.bokhan.sphere.creator.SphereCreatorTest;
import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NullFileException;
import com.bokhan.sphere.parser.SphereParser;
import com.bokhan.sphere.parser.SphereParserTest;
import com.bokhan.sphere.reader.ReaderFromFile;
import com.bokhan.sphere.reader.ReaderFromFileTest;
import com.bokhan.sphere.storage.SphereStorage;
import com.bokhan.sphere.storage.SphereStorageTest;
import com.bokhan.sphere.util.SphereParametersCalculatorTest;
import com.bokhan.sphere.util.SphereValidator;
import com.bokhan.sphere.util.SphereValidatorTest;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.List;
import java.util.Map;

/**
 * Created by vbokh on 29.05.2017.
 */
@Suite.SuiteClasses({ReaderFromFileTest.class,
        SphereCreatorTest.class,SphereParserTest.class, SphereValidatorTest.class, SphereStorageTest.class, SphereParametersCalculatorTest.class,
        })
@RunWith(Suite.class)
public class SphereRunnerTest {
        private final static String FILENAME = "src/main/resources/data.txt";
        @AfterClass
        public static void mainTest() {
                ReaderFromFile readerFromFile = new ReaderFromFile();
                SphereValidator validator = new SphereValidator();
                SphereParser parser = new SphereParser();
                SphereCreator creator = new SphereCreator();
                Map<Sphere, SphereParameters> map = null;
                try {
                        List<String> unvalidatedData = readerFromFile.readDataFromFile(FILENAME);
                        List<String> validatedData = validator.validateData(unvalidatedData);
                        Map<Point, Double> dataForCreatingSpheres = parser.parseListOfData(validatedData);
                        map = creator.createSpheresAndParameters(dataForCreatingSpheres);
                } catch (NullFileException e) {
                        e.getMessage();
                } catch (MissingDataException e) {
                        e.getMessage();
                }
                Map<Sphere,SphereParameters> storage = SphereStorage.getInstance().getSphereStorage();
                Sphere testSphere =(Sphere) map.keySet().toArray()[1];
                testSphere.setRadius(20);
                Sphere testSphere2 =(Sphere) storage.keySet().toArray()[1];
                System.out.println(testSphere);
                System.out.println(testSphere2);
                System.out.println(storage.containsKey(testSphere));

        }
}
