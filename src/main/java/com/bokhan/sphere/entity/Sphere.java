package com.bokhan.sphere.entity;

import com.bokhan.sphere.observer.IObserver;
import com.bokhan.sphere.observer.SphereEvent;
import com.bokhan.sphere.observer.SphereIObserver;

/**
 * Created by vbokh on 24.05.2017.
 */
public class Sphere {
    private Point centre;
    private double radius;
    private IObserver observer;

    public Sphere(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public Point getCentre() {
        return centre;
    }

    public double getRadius() {
        return radius;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
        notifyObservers();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        notifyObservers();
    }

    public void addObserver(SphereIObserver observer) {
        this.observer = observer;
        observer.addObservable(this);
    }

    private void notifyObservers() {
        if (observer != null) {
            observer.handleEvent(new SphereEvent(this));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        Sphere sp = (Sphere) obj;
        if(getRadius() !=sp.getRadius())
            return false;
        if (centre == null) {
            if (sp.centre != null) {
                return false;
            }
        } else if (!centre.equals(sp.centre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        return result += centre.hashCode() + radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "centre=" + centre +
                ", radius=" + radius +
                '}';
    }
}
