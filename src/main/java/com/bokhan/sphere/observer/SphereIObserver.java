package com.bokhan.sphere.observer;

import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import com.bokhan.sphere.storage.SphereStorage;


/**
 * Created by vbokh on 26.05.2017.
 */
public class SphereIObserver implements IObserver {
    private Sphere sphere;

    public void addObservable(Sphere sphere) {
        this.sphere = sphere;
    }

    public void handleEvent(SphereEvent event) {
        SphereParameters parameters = new SphereParameters(sphere);
        SphereStorage.getInstance().updateStorage(sphere,parameters);


    }
}
