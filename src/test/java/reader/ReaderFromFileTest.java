package reader;

import entity.Point;
import entity.Sphere;
import entity.SphereParameters;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class ReaderFromFileTest {
//    private static Map<Sphere, SphereParameters> map;
    private static Map<Point,Double> map;
    private static final String FILENAME = "src/main/resources/data.txt";
    @BeforeClass
    public static void initMap(){
        map = new HashMap<Point, Double>();
        Point point1 = new Point(3, 4, 5);
        double radius1 = 5;
        Point point2 = new Point(7, 7, 7);
        double radius2 = 9;
        Point point3 = new Point(5.1, 7.5, 5.5);
        double radius3 = 4.6;
        map.put(point1, radius1);
        map.put(point2, radius2);
        map.put(point3, radius3);
    }

    @Test
    public void readDataFromFile() {
        Map<Point, Double> expected = map;
        Map<Point,Double> actual = new ReaderFromFile().readDataFromFile(FILENAME);
        assertEquals(expected, actual);
    }

}