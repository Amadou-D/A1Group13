package shapes;

public class Cylinder extends GeometricShape {
    private double height;
    private double radius;

    public Cylinder(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getVolume() {
        return Math.PI * radius * radius * height;
    }

    @Override
    public double getBaseArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public String toString() {
        return "Cylinder: Height = " + height + ", Radius = " + radius;
    }
}
