package appDomain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import shapes.GeometricShape;
import sorting.SortingAlgorithms;

public class AppDriver {

    public static void main(String[] args) {
        Map<String, String> argMap = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i].toLowerCase();
            if (arg.startsWith("-")) {
                String key = arg.substring(0, 2);
                String value = (arg.length() > 2) ? arg.substring(2) : null;
                if (value == null && i + 1 < args.length) {
                    value = args[++i];
                }
                argMap.put(key, value);
            }
        }

        String fileName = argMap.get("-f");
        if (fileName == null) {
            System.err.println("File name must be provided with -f option.");
            return;
        }

        File file = new File(fileName);
        if (!file.isAbsolute()) {
            if (!fileName.startsWith("res/") && !fileName.contains("\\")) {
                fileName = "res/" + fileName.trim().replaceAll("\\\\", "/");
            }
        }

        char sortBy = argMap.getOrDefault("-t", "h").charAt(0);
        char algorithm = argMap.getOrDefault("-s", "b").charAt(0);

        if (sortBy != 'h' && sortBy != 'v' && sortBy != 'a') {
            System.err.println(
                    "Invalid sort type specified. Please use 'h' for height, 'v' for volume, or 'a' for base area.");
            return;
        }

        GeometricShape[] shapes;
        try {
            shapes = readShapesFromFile(fileName);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        long startTime = System.nanoTime();
        Comparator<GeometricShape> comparator = null;
        switch (sortBy) {
            case 'v':
                comparator = Comparator.comparing(GeometricShape::getVolume);
                break;
            case 'a':
                comparator = Comparator.comparingDouble(GeometricShape::getBaseArea);
                break;
            case 'h': // Handle sorting by height using Comparable interface
                if (!(shapes[0] instanceof Comparable)) {
                    System.err.println("Shapes do not implement Comparable interface for sorting by height.");
                    return;
                }
                comparator = Comparator.naturalOrder();
                break;
            default:
                System.err.println("Invalid sorting type specified.");
                return;
        }

        switch (algorithm) {
            case 'b':
                SortingAlgorithms.bubbleSort(shapes, comparator, sortBy);
                break;
            case 'i':
                SortingAlgorithms.insertionSort(shapes, comparator, sortBy);
                break;
            case 's':
                SortingAlgorithms.selectionSort(shapes, comparator, sortBy);
                break;
            case 'm':
                SortingAlgorithms.mergeSort(shapes, comparator, sortBy);
                break;
            case 'q':
                SortingAlgorithms.quickSort(shapes, comparator, sortBy);
                break;
            case 'z':
                SortingAlgorithms.customSort(shapes, comparator, sortBy);
                break;
            default:
                System.err.println("Invalid sorting algorithm specified.");
                return;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        for (int i = 1000; i < shapes.length; i += 1000) {
            System.out.println("Shape at position " + i + ": " + shapes[i]);
        }

        System.out.println("Sorting completed in: " + duration + " milliseconds.");
        System.out.println("First sorted value: " + shapes[0]);
        System.out.println("Last sorted value: " + shapes[shapes.length - 1]);
    }

    private static GeometricShape[] readShapesFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int numberOfShapes = Integer.parseInt(reader.readLine().trim());
        GeometricShape[] shapes = new GeometricShape[numberOfShapes];

        String line;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid line format: " + line);
            }
            String shapeType = parts[0];
            double dimension1 = Double.parseDouble(parts[1]);
            double dimension2 = Double.parseDouble(parts[2]);

            try {
                Class<?> shapeClass = Class.forName("shapes." + shapeType);
                Constructor<?> constructor = shapeClass.getConstructor(double.class, double.class);
                shapes[index++] = (GeometricShape) constructor.newInstance(dimension1, dimension2);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error creating shape instance: " + e.getMessage(), e);
            }
        }
        reader.close();
        return shapes;
    }
}
