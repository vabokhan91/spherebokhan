package util;

import entity.Sphere;

/**
 * Created by vbokh on 24.05.2017.
 */
public class SphereValidator {
    public boolean objectIsSphere(Sphere sphere) {
        boolean result = (sphere.getRadius() > 0 && sphere.getCentre() != null);
        return result;
    }
}
