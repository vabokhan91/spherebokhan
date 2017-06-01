package com.bokhan.sphere.reader;

import com.bokhan.sphere.exception.NullFileException;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class ReaderFromFileTest {
    private static List<String > data;
    private final static String FILENAME = "src/main/resources/data.txt";

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
    public void readDataFromFile() throws NullFileException {
        List<String> expected = data;
        List<String> actual = new ReaderFromFile().readDataFromFile(FILENAME);
        assertEquals(expected, actual);
    }

    @Test(expected = NullFileException.class)
    public void readNull() throws NullFileException {
        List<String> actual = new ReaderFromFile().readDataFromFile(null);
    }

}