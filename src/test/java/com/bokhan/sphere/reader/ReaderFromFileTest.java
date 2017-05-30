package com.bokhan.sphere.reader;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.exception.NoFileException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class ReaderFromFileTest {
    private static List<String > data;
    private static final String FILENAME = "src/main/resources/data.txt";

    @BeforeClass
    public static void initList() {
        data = new ArrayList<>();
        data.add("3 4 5   2");
        data.add("7 7 7 9");
        data.add("5.1 7.5 5.5 4.6");
        data.add("3 6 five 1");
        data.add("3 0 7");
        data.add("3 -3 -8 -9");
        data.add("3 -4 -3 8");
    }

    @Test
    public void readDataFromFile() throws NoFileException{
        List<String> expected = data;
        List<String> actual = new ReaderFromFile().readDataFromFile(FILENAME);
        assertEquals(expected, actual);
    }

    @Test(expected = NoFileException.class)
    public void readNull() throws NoFileException{
        List<String> actual = new ReaderFromFile().readDataFromFile(null);
    }

}