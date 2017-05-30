package com.bokhan.sphere.observer;

import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;

import java.util.EventObject;

/**
 * Created by vbokh on 26.05.2017.
 */
public class SphereEvent extends EventObject {
    public SphereEvent(Sphere sphere) {
        super(sphere);
    }

    @Override
    public Sphere getSource() {
        return (Sphere) super.getSource();
    }
}
