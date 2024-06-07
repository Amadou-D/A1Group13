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

/**
 * The following program was created by group 13: Kyle Simons, Amadou Diallo, and Ricky Asuncion
 * for Object-Oriented Programming 3 (CPRG-304-B) with professor Prashant Sharma at SAIT.
 * 
 * The AppDriver class is the entry point for running the sorting application.
 *
 * Problem Statement:
 * The goal is to create an application that reads a file of random shapes,
 * sorts them using various sorting algorithms, and benchmarks the performance
 * of each algorithm. The shapes should be manipulated as elements of a collection,
 * and the sorting should be done based on user-specified criteria.
 *
 * Solution:
 * 1. Implement an abstract Shape class representing a three-dimensional geometric shape.
 *    Each shape subclass (e.g., Cylinder, Cone, Pyramid, Prisms) calculates its volume
 *    and base area.
 *
 * 2. Implement Comparable interface in the Shape class to compare shapes by height.
 *    Implement a Comparator to compare shapes by base area and volume.
 *
 * 3. Create a utility class Sorter with different sorting algorithms (Bubble, Selection,
 *    Insertion, Merge, Quick, and Heap Sort) that work on arrays of Comparable elements
 *    or elements with a provided Comparator.
 *
 * 4. The AppDriver class reads command-line arguments to determine the input file,
 *    sorting criteria, and sorting algorithm. It reads shapes from the file, sorts
 *    them according to the specified algorithm, and prints benchmarking information.
 *    
 * Disclosure:
 * ChatGPT was used to help generate the mergeSort compare() and compareTo().
 *
 * Moving Parts:
 * - Shape class: Abstract base class for shapes with methods to calculate volume and base area.
 * - Shape subclasses: Specific shapes (Cylinder, Cone, Pyramid, various Prisms) that implement
 *   the abstract methods from Shape.
 * - Sorter class: Contains static methods for various sorting algorithms.
 * - AppDriver class: Reads input, initializes shapes, and sorts them using the Sorter methods.
 *
 * Use of Test Files:
 * - shapes1.txt, shapes2.txt, shapes3.txt: Input files containing random shapes.
 *   Each file's first line contains the number of shapes, followed by lines with
 *   the shape type, height, and other dimensions.
 *
 * Testing:
 * - The application reads a specified input file, sorts the shapes using the selected algorithm,
 *   and prints the time taken for sorting.
 * - Displays the first, last, and every thousandth sorted shape.
 *
 * Command-line Usage:
 * - java -jar Sort.jar -f<file_name> -t<sort_type> -s<sort_algorithm>
 *   - -f or -F: Specifies the input file (e.g., shapes1.txt).
 *   - -t or -T: Specifies the sorting type (h for height, v for volume, a for base area).
 *   - -s or -S: Specifies the sorting algorithm (b for bubble, s for selection, i for insertion,
 *     m for merge, q for quick, z for heap).
 *
 * Example:
 * - java -jar Sort.jar -fshapes1.txt -tv -sb
 * - java -jar Sort.jar -fshapes2.txt -th -sh
 */
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
