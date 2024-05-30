package shapes;

public class PentagonalPrism extends GeometricShape {
    private double height;
    private double edgeLength;

    public PentagonalPrism(double height, double edgeLength) {
        this.height = height;
        this.edgeLength = edgeLength;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getVolume() {
        return (5.0 / 2.0) * edgeLength * Math.tan(Math.toRadians(54)) / 2.0 * height;
    }

    @Override
    public double getBaseArea() {
        return (5.0 / 2.0) * edgeLength * Math.tan(Math.toRadians(54)) / 2.0;
    }
    
    public String toString() {
        return "Pentagonal Prism: Height = " + height + ", Edge Length = " + edgeLength;
    }
    
}