package com.bokhan.sphere.parser;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.exception.NullFileException;
import com.bokhan.sphere.reader.ReaderFromFile;
import com.bokhan.sphere.services.SphereValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created by vbokh on 29.05.2017.
 */
public class SphereParserTest {
    private static Map<Point, Double> expected;
    private static SphereParser parser;
    private static List<String> testString;
    private static SphereValidator validator;
    private final static String FILENAME = "src/main/resources/data.txt";

    @BeforeClass
    public static void initData() throws NullFileException {
        ReaderFromFile readerFromFile = new ReaderFromFile();
        parser = new SphereParser();
        validator = new SphereValidator();
        expected = new HashMap<>();
        expected.put(new Point(5.1, 7.5, 5.5 ), 4.6);
        expected.put(new Point(3, -4, -3), 8.0);
        expected.put(new Point(7, 7, 7 ), 9.0);
        expected.put(new Point(3, 4, 5 ), 2.0);
        testString = validator.validateData(readerFromFile.readDataFromFile(FILENAME));
    }

    @Test
    public void parseDataFromFileTest() {
        Map<Point, Double> actual = parser.parseListOfData(testString);
        assertEquals(expected,actual);
    }


}