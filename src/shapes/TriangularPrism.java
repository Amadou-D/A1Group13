package shapes;

public class TriangularPrism extends GeometricShape {
    private double height;
    private double edgeLength;

    public TriangularPrism(double height, double edgeLength) {
        this.height = height;
        this.edgeLength = edgeLength;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getVolume() {
        return (Math.sqrt(3) / 4) * edgeLength * edgeLength * height;
    }

    @Override
    public double getBaseArea() {
        return (Math.sqrt(3) / 4) * edgeLength * edgeLength;
    }
    
    @Override
    public String toString() {
        return "TriangularPrism: Height = " + height + ", Edge Length = " + edgeLength;
    }
}