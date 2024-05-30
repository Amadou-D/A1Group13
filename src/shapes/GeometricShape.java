package shapes;

import java.util.Comparator;

public abstract class GeometricShape implements Comparable<GeometricShape>, Comparator<GeometricShape> {
    protected abstract double getHeight();
    protected abstract double getVolume();
    protected abstract double getBaseArea();

    @Override
    public int compareTo(GeometricShape other) {
        return Double.compare(this.getHeight(), other.getHeight());
    }

    @Override
    public int compare(GeometricShape shape1, GeometricShape shape2) {
        double result = shape1.getBaseArea() - shape2.getBaseArea();
        if (result == 0) {
            result = shape1.getVolume() - shape2.getVolume();
        }
        return (int) result;
    }
    
    
}
