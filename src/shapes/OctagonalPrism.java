package shapes;

public class OctagonalPrism extends GeometricShape {
    private double height;
    private double edgeLength;

    public OctagonalPrism(double height, double edgeLength) {
        this.height = height;
        this.edgeLength = edgeLength;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getVolume() {
        return 2.0 * (1.0 + Math.sqrt(2)) * edgeLength * edgeLength * height;
    }

    @Override
    public double getBaseArea() {
        return 2.0 * (1.0 + Math.sqrt(2)) * edgeLength * edgeLength;
    }
    
    @Override
    public String toString() {
        return "Octagonal Prism: Height = " + height + ", Edge Length = " + edgeLength;
    }
}