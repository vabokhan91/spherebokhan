package com.bokhan.sphere.service;

import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NoFileException;
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
    private static ReaderFromFile readerFromFile;
    private static List<String> listOfTestData;
    private final static String FILE_NAME = "src/main/resources/data.txt";
    private final static List<String> emptyList = new ArrayList<>();

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
    public void validateDataTest() throws NoFileException, MissingDataException {
        readerFromFile = new ReaderFromFile();
        List<String> dataFromFile = readerFromFile.readDataFromFile(FILE_NAME);
        List<String> actual = validator.validateData(dataFromFile);
        assertEquals(listOfTestData, actual);
    }

    @Test(expected = MissingDataException.class)
    public void validateNullTest() throws MissingDataException {
        validator.validateData(null);
    }

    @Test(expected = MissingDataException.class)
    public void validateEmptyDataTest() throws MissingDataException {
        validator.validateData(emptyList);
    }
}