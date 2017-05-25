package creator;

import entity.Point;
import entity.Sphere;


/**
 * Created by vbokh on 24.05.2017.
 */
public class SphereCreator {
    public Sphere createSphere(Point point, double radius) {
        Sphere sphere = new Sphere(point, radius);
        return sphere;
    }
}
