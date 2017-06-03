package com.bokhan.sphere.reader;

import com.bokhan.sphere.exception.NoFileException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class ReaderFromFileTest {
    private static List<String> data;
    private final static String FILE_NAME = "src/main/resources/data.txt";
    private final static String WRONG_FILE_NAME = "src//data.txt";
    private final static String EMPTY_STRING = "";

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
    public void readDataTest() throws NoFileException {
        List<String> expected = data;
        List<String> actual = new ReaderFromFile().readDataFromFile(FILE_NAME);
        assertEquals(expected, actual);
    }

    @Test(expected = NoFileException.class)
    public void readNull() throws NoFileException {
        List<String> actual = new ReaderFromFile().readDataFromFile(null);
    }

    @Test(expected = NoFileException.class)
    public void readEmptyStringTest() throws NoFileException {
        List<String> actual = new ReaderFromFile().readDataFromFile(EMPTY_STRING);
    }

    @Test
    public void readWrongFilenameTest() throws NoFileException {
        List<String> expected = null;
        List<String> actual = new ReaderFromFile().readDataFromFile(WRONG_FILE_NAME);
        assertEquals(expected, actual);
    }

}