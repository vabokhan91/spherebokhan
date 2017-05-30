package com.bokhan.sphere.parser;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.exception.NoFileException;
import com.bokhan.sphere.reader.ReaderFromFile;
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
    private static Map<Point, Double> data;
    private static SphereParser parser;
    private static List<String> line;
    private static final String FILENAME = "src/main/resources/data.txt";

    @BeforeClass
    public static void initData() throws NoFileException{
        ReaderFromFile readerFromFile = new ReaderFromFile();
        parser = new SphereParser();
        data = new HashMap<>();
        data.put(new Point(5.1, 7.5, 5.5 ), 4.6);
        data.put(new Point(3, -4, -3), 8.0);
        data.put(new Point(7, 7, 7 ), 9.0);
        data.put(new Point(3, 4, 5 ), 2.0);
        data.put(new Point(3, -3, -8), -9.0);
        line = readerFromFile.readDataFromFile(FILENAME);
    }

    @Test
    public void parseDataFromFileTest() {
        Map<Point, Double> actual = parser.parseDataFromReader(line);
        assertTrue(actual.equals(data));
    }


}