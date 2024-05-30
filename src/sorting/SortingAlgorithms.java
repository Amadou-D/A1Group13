package sorting;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            T temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array.length <= 1) {
            return;
        }
        int mid = array.length / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }
    
    private static <T extends Comparable<T>> void merge(T[] array, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = new Random().nextInt(end - start + 1) + start;
            T pivot = array[pivotIndex];
            array[pivotIndex] = array[end];
            array[end] = pivot;

            int i = start;
            for (int j = start; j < end; j++) {
                if (array[j].compareTo(pivot) < 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                }
            }
            T temp = array[i];
            array[i] = array[end];
            array[end] = temp;

            quickSort(array, start, i - 1);
            quickSort(array, i + 1, end);
        }
    }
    
    public static <T extends Comparable<T>> void customSort(T[] array) {
    	
    }
}
