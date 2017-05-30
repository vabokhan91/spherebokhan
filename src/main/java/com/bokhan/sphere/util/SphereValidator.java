package com.bokhan.sphere.util;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * Created by vbokh on 24.05.2017.
 */
public class SphereValidator {
    public boolean objectIsSphere(Point centre,double radius) {
        if(radius > 0 && centre!=null){
            return true;
        }
        return false;
    }
}
