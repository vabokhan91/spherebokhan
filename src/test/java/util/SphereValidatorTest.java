package util;

import creator.SphereCreator;
import entity.Point;
import entity.Sphere;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereValidatorTest {
    private Sphere sphere;
    @Before
    public void initSphere() {
        sphere = new SphereCreator().createSphere(new Point(3, 5, 7), 5);
    }

    @Test
    public void objectIsSphereTest(){
        Sphere sphere1 = new Sphere(new Point(3, 5, 7), 5);
        boolean expected = (sphere1.getRadius() > 0 && sphere1.getCentre()!=null);
        SphereValidator validator = new SphereValidator();
        boolean actual = validator.objectIsSphere(sphere);
        Assert.assertEquals(expected, actual);
    }
}