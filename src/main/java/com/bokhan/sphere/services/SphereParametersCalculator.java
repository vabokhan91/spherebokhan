package com.bokhan.sphere.services;

import com.bokhan.sphere.entity.Sphere;

/**
 * Created by vbokh on 25.05.2017.
 */
public class SphereParametersCalculator {
    private final static String SPHERE = " Sphere ";
    private final static String OXY = " is crossed by OXY coordinate plane. Ratio of volumes of sections : ";
    private final static String OXZ = " is crossed by OXZ coordinate plane. Ratio of volumes of sections : ";
    private final static String OYZ = " is crossed by OYZ coordinate plane. Ratio of volumes of sections : ";
    private final static String NOCROSSING = " is not crossed by any coordinate plane";
    private final static double P = Math.PI;

    public static double calculateSquare(Sphere sphere) {
        double radius = sphere.getRadius();
        double square = 4 * P * Math.pow(radius, 2);
        return square;
    }

    public static double calculateVolume(Sphere sphere) {
        double radius = sphere.getRadius();
        double sphereVolume = 4 / 3 * P * Math.pow(radius, 3);
        return sphereVolume;
    }

    public static String calculateSphereVolumesRatio(Sphere sphere) {
        StringBuilder volumesRatio = new StringBuilder();
        if (isSphereCrossedByCoordinatePlane(sphere)) {
            if (isSphereCrossedByOXY(sphere)) {
                double ratioOXY = calculateRatioWithOXY(sphere);
                volumesRatio.append(SPHERE + sphere.getId() + OXY + ratioOXY);
            }
            if (isSphereCrossedByOXZ(sphere)) {
                double ratioOXZ = calculateRatioWithOXZ(sphere);
                volumesRatio.append(SPHERE + sphere.getId() + OXZ + ratioOXZ);
            }
            if (isSphereCrossedByOYZ(sphere)) {
                double ratioOYZ = calculateRatioWithOYZ(sphere);
                volumesRatio.append(SPHERE + sphere.getId() + OYZ + ratioOYZ);
            }
        } else {
            volumesRatio.append(SPHERE + sphere.getId() + NOCROSSING);
        }
        return volumesRatio.toString();
    }

    private static double calculateRatioWithOYZ(Sphere sphere) {
        double heightOfCrossingSection = sphere.getRadius() - Math.abs(sphere.getCentre().getX());
        double ratioOYZ = getRatio(sphere, heightOfCrossingSection);
        return ratioOYZ;

    }

    private static double calculateRatioWithOXZ(Sphere sphere) {
        double heightOfCrossingSection = sphere.getRadius() - Math.abs(sphere.getCentre().getY());
        double ratioOXZ = getRatio(sphere, heightOfCrossingSection);
        return ratioOXZ;
    }


    public static double calculateRatioWithOXY(Sphere sphere) {
        double heightOfCrossingSection = sphere.getRadius() - Math.abs(sphere.getCentre().getZ());
        double ratioOXY = getRatio(sphere, heightOfCrossingSection);
        return ratioOXY;
    }

    private static double getRatio(Sphere sphere, double heightOfCrossingSection) {
        double volumeOfSphere = calculateVolume(sphere);
        double volumeOfCrossingSection = calculateVolumeOfSection(sphere, heightOfCrossingSection);
        double result = volumeOfCrossingSection / (volumeOfSphere - volumeOfCrossingSection);
        result = Math.round(result * 100) / 100d;
        return result;
    }

    public static boolean isSphereCrossedByCoordinatePlane(Sphere sphere) {
        if (isSphereCrossedByOXY(sphere))
            return true;
        if (isSphereCrossedByOXZ(sphere))
            return true;
        if (isSphereCrossedByOYZ(sphere)) {
            return true;
        }
        return false;
    }

    private static double calculateVolumeOfSection(Sphere sphere, double height) {
        double volume = P * Math.pow(height, 2) * (sphere.getRadius() - 1 / 3 * height);
        return volume;
    }

    private static boolean isSphereCrossedByOXY(Sphere sphere) {
       return sphere.getRadius() > Math.abs(sphere.getCentre().getZ());
    }

    private static boolean isSphereCrossedByOXZ(Sphere sphere) {
        return sphere.getRadius() > Math.abs(sphere.getCentre().getY());

    }

    private static boolean isSphereCrossedByOYZ(Sphere sphere) {
      return sphere.getRadius() > Math.abs(sphere.getCentre().getX());
    }
}
