package shapes;


public class Pyramid extends GeometricShape {
    private double height;
    private double edgeLength;

    public Pyramid(double height, double edgeLength) {
        this.height = height;
        this.edgeLength = edgeLength;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getVolume() {
        return (1.0 / 3.0) * edgeLength * edgeLength * height;
    }

    @Override
    public double getBaseArea() {
        return edgeLength * edgeLength;
    }
    
    @Override
    public String toString() {
        return "Pyramid: Height = " + height + ", Edge Length = " + edgeLength;
    }
}