package com.bokhan.sphere.creator;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NoFileException;
import com.bokhan.sphere.parser.SphereParserTest;
import com.bokhan.sphere.reader.ReaderFromFile;
import com.bokhan.sphere.util.SphereValidatorTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 29.05.2017.
 */
public class SphereCreatorTest {
    private static List<Sphere> spheres;
    private static ReaderFromFile reader;
    private static final String FILENAME = "src/main/resources/data.txt";
    private List<String> emptyList = null;
    @BeforeClass
    public static void initList() {
        reader = new ReaderFromFile();
        spheres = new ArrayList<>();
        spheres.add(new Sphere(new Point(3, 4, 5), Double.valueOf(2)));
        spheres.add(new Sphere(new Point(3, -4, -3), Double.valueOf(8)));
        spheres.add(new Sphere(new Point(5.1, 7.5, 5.5), Double.valueOf(4.6)));
        spheres.add(new Sphere(new Point(7, 7, 7), Double.valueOf(9)));

    }

    @Test
    public void getSpheresTest() throws NoFileException,MissingDataException{
        List<Sphere> actual = SphereCreator.getSpheres(reader.readDataFromFile(FILENAME));
        assertTrue(actual.containsAll(spheres));
    }
    @Test(expected = MissingDataException.class)
    public void getSpheresNullTest() throws MissingDataException, NoFileException{
        List<Sphere> actual = SphereCreator.getSpheres(emptyList);
    }

}