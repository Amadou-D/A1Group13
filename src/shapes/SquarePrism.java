package shapes;

public class SquarePrism extends GeometricShape {
    private double height;
    private double edgeLength;

    public SquarePrism(double height, double edgeLength) {
        this.height = height;
        this.edgeLength = edgeLength;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getVolume() {
        return edgeLength * edgeLength * height;
    }

    @Override
    public double getBaseArea() {
        return edgeLength * edgeLength;
    }
    
    @Override
    public String toString() {
        return "SquarePrism: Height = " + height + ", Edge Length = " + edgeLength;
    }
}