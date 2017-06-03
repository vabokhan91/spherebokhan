package com.bokhan.sphere.entity;

import com.bokhan.sphere.exception.IncorrectDataException;
import com.bokhan.sphere.generator.IdGenerator;
import com.bokhan.sphere.observer.IObserver;
import com.bokhan.sphere.observer.SphereEvent;


/**
 * Created by vbokh on 24.05.2017.
 */
public class Sphere {
    private Integer id;
    private Point centre;
    private double radius;
    private IObserver parameters;

    public Sphere(Point centre, double radius) {
        this.id = IdGenerator.nextId();
        this.centre = centre;
        this.radius = radius;
    }

    public Point getCentre() {
        return centre;
    }

    public double getRadius() {
        return radius;
    }

    public Integer getId() {
        return id;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
        notifyObservers();
    }

    public void setRadius(double radius) throws IncorrectDataException {
        if (radius <= 0) {
            throw new IncorrectDataException(" Trying to set wrong radius value " + radius + ". Radius can not be negative or 0");
        }
        this.radius = radius;
        notifyObservers();
    }

    public void addObserver(SphereParameters parameters) {
        this.parameters = parameters;
        parameters.addObservable(this);
    }

    private void notifyObservers() {
        if (parameters != null) {
            parameters.handleEvent(new SphereEvent(this));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.radius, radius) != 0) return false;
        return centre != null ? centre.equals(sphere.centre) : sphere.centre == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "centre=" + centre +
                ", radius=" + radius + ", id =" + id +
                '}';
    }
}
