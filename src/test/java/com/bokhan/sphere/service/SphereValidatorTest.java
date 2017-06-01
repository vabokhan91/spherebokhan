package com.bokhan.sphere.service;

import com.bokhan.sphere.exception.NullFileException;
import com.bokhan.sphere.reader.ReaderFromFile;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by vbokh on 25.05.2017.
 */

public class SphereValidatorTest {
    private static SphereValidator validator;
    private static List<String> listOfTestData;
    private final static String FILENAME = "src/main/resources/data.txt";

    @BeforeClass
    public static void initValidator() {
        validator = new SphereValidator();
        listOfTestData = new ArrayList<>();
        listOfTestData.add("3 4 5   2");
        listOfTestData.add("7 7 7 9");
        listOfTestData.add("5.1 7.5 5.5 4.6");
        listOfTestData.add("3 -4 -3 8");
    }
    @Test
    public void validateDataTest() throws NullFileException {
        List<String> actual = validator.validateData(new ReaderFromFile().readDataFromFile(FILENAME));
        assertEquals(listOfTestData,actual);
    }
}