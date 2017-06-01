package com.bokhan.sphere.entity;

import com.bokhan.sphere.generator.IdGenerator;
import com.bokhan.sphere.observer.IObserver;
import com.bokhan.sphere.observer.SphereEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by vbokh on 24.05.2017.
 */
public class Sphere {
    private final static Logger LOGGER = LogManager.getLogger();
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
        LOGGER.log(Level.INFO, "Parameters of sphere " + id + " were changed. New parameters : " + this.parameters);
    }

    public void setRadius(double radius) {
        this.radius = radius;
        notifyObservers();
        LOGGER.log(Level.INFO, "Parameters of sphere " + id + " were changed. New parameters : " + this.parameters);
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
        int result;
        long temp;
        result = centre != null ? centre.hashCode() : 0;
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "centre=" + centre +
                ", radius=" + radius + ", id =" + id +
                '}';
    }
}
