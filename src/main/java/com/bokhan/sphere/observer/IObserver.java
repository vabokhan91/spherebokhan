package com.bokhan.sphere.observer;

import com.bokhan.sphere.entity.Sphere;

/**
 * Created by vbokh on 26.05.2017.
 */
public interface IObserver {
    void handleEvent(SphereEvent sphere);
}
