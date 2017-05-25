package entity;

/**
 * Created by vbokh on 24.05.2017.
 */
public class Sphere {
    private Point centre;
    private double radius;
    private SphereParameters params;

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
        updateParams();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        updateParams();
    }

    public void addParams(SphereParameters params) {
        this.params = params;
    }
    public void updateParams(){
        if(params!=null){
            params.update(this);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.radius, radius) != 0) return false;
        return centre.equals(sphere.centre);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = centre.hashCode();
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "centre=" + centre +
                ", radius=" + radius +
                '}';
    }
}
