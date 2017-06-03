package com.bokhan.sphere.parser;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NoFileException;
import com.bokhan.sphere.reader.ReaderFromFile;
import com.bokhan.sphere.service.SphereValidator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 29.05.2017.
 */
public class SphereParserTest {
    private static Map<Point, Double> expected = new HashMap<>();
    private static SphereParser parser;
    private static List<String> testString;
    private static SphereValidator validator;
    private static ReaderFromFile readerFromFile;
    private final static String FILE_NAME = "src/main/resources/data.txt";
    private static List<String> emptyList = new ArrayList<>();

    @BeforeClass
    public static void initData() throws NoFileException, MissingDataException {
        readerFromFile = new ReaderFromFile();
        parser = new SphereParser();
        validator = new SphereValidator();
        expected.put(new Point(5.1, 7.5, 5.5), 4.6);
        expected.put(new Point(3, -4, -3), 8.0);
        expected.put(new Point(7, 7, 7), 9.0);
        expected.put(new Point(3, 4, 5), 2.0);
        List<String> dataFromFile = readerFromFile.readDataFromFile(FILE_NAME);
        testString = validator.validateData(dataFromFile);
    }

    @Test
    public void parseDataTest() throws MissingDataException {
        Map<Point, Double> actual = parser.parseData(testString);
        assertEquals(expected, actual);
    }

    @Test
    public void parseWrongData() throws MissingDataException {
        List<String> listWithWrongData = new ArrayList<>();
        listWithWrongData.add("1 5 five -9");
        Map<Point, Double> expected = new HashMap<>();
        Map<Point, Double> actual = parser.parseData(listWithWrongData);
        assertEquals(expected, actual);
    }

    @Test
    public void parseRedundantData() throws MissingDataException {
        List<String> listWithRedundantData = new ArrayList<>();
        listWithRedundantData.add("1 5 10 -9 1");
        Map<Point, Double> expected = new HashMap<>();
        Map<Point, Double> actual = parser.parseData(listWithRedundantData);
        assertEquals(expected, actual);
    }

    @Test
    public void parseIncompleteData() throws MissingDataException {
        List<String> listWithRedundantData = new ArrayList<>();
        listWithRedundantData.add("1 5 10");
        Map<Point, Double> expected = new HashMap<>();
        Map<Point, Double> actual = parser.parseData(listWithRedundantData);
        assertEquals(expected, actual);
    }

    @Test(expected = MissingDataException.class)
    public void parseDataEmptyListTest() throws MissingDataException {
        parser.parseData(emptyList);
    }

    @Test(expected = MissingDataException.class)
    public void parseDataNullTest() throws MissingDataException {
        parser.parseData(null);
    }


}