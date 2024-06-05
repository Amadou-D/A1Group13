package shapes;

import java.util.Comparator;

public abstract class GeometricShape implements Comparable<GeometricShape>, Comparator<GeometricShape> {
    public abstract double getHeight();
    public abstract double getVolume();
    public abstract double getBaseArea();

    @Override
    public int compareTo(GeometricShape other) {
        return Double.compare(this.getHeight(), other.getHeight());
    }

    @Override
    public int compare(GeometricShape shape1, GeometricShape shape2) {
        return shape1.compareBy(shape2, 'a');
    }

    public int compareBy(GeometricShape shape2, char sortBy) {
        int result;
        if (sortBy == 'v') {
            // Compare by volume
            result = Double.compare(this.getVolume(), shape2.getVolume());
            if (result == 0) {
                // If volumes are equal, compare by base area
                result = Double.compare(this.getBaseArea(), shape2.getBaseArea());
            }
        } else {
            // Default to compare by area
            result = Double.compare(this.getBaseArea(), shape2.getBaseArea());
            if (result == 0) {
                // If areas are equal, compare by volume
                result = Double.compare(this.getVolume(), shape2.getVolume());
            }
        }
        return result;
    }
}
