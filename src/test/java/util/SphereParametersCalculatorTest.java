package util;

import creator.SphereCreator;
import entity.Point;
import entity.Sphere;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereParametersCalculatorTest {
    private static Sphere sphere;
    private static SphereParametersCalculator calculator;

    @BeforeClass
    public static void initSphere(){
        SphereCreator creator = new SphereCreator();
        sphere = creator.createSphere(new Point(3, 6, 8), 7);
        calculator = new SphereParametersCalculator();
    }

    @Test
    public void calculateSquare(){
        double expected = 4 * Math.PI * Math.pow(7, 2);
        double actual = calculator.calculateSquare(sphere);
        assertEquals(expected,actual,0.01);
    }

    @Test
    public void calculateVolume(){
        double expected = 4 / 3 * Math.PI * Math.pow(7, 3);
        double actual = calculator.calculateVolume(sphere);
        assertEquals(expected,actual,0.01);
    }

    @Ignore
    @Test
    public void calculateVolumeRatio(){

    }

}