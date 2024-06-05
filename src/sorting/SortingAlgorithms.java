package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortingAlgorithms {
	
	/**
	 * Bubble Sort:
	 * This method is used to sort an array by repeatedly stepping through the list,
	 * comparing the elements and swapping them if they're in the wrong order.
	 * It continues this process until the list is sorted.
	 *
	 * @param array      the array to be sorted
	 * @param comparator the comparator to use for comparing elements
	 * @param sortBy     the type of comparison: 'h' for height, 'v' for volume, 'a' for base area
	 */

    public static <T extends Comparable<T>> void bubbleSort(T[] array, Comparator<T> comparator, char sortBy) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int comparisonResult = 0;
                if (sortBy == 'h') {
                    comparisonResult = array[j].compareTo(array[j + 1]);
                } else if (sortBy == 'v' || sortBy == 'a') {
                    comparisonResult = comparator.compare(array[j], array[j + 1]);
                }

                if (comparisonResult > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Insertion Sort:
     * This method builds a sorted array one element at a time, it does this by repeatedly taking the next element
     * and inserting it into the correct position in the already sorted part of the array.
     * 
     * @param takes an array to be sorted
     */
    public static <T extends Comparable<T>> void insertionSort(T[] array, Comparator<T> comparator, char sortBy) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0 && (sortBy == 'h' ? array[j].compareTo(key) > 0 : comparator.compare(array[j], key) > 0)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Selection Sort:
     * This method sorts an array by dividing it into a sorted and an unsorted part.
     * It repeatedly selects the smallest or largest element from the unsorted part and moves it to the sorted.
     * 
     * @param takes an array to be sorted
     */
    public static <T extends Comparable<T>> void selectionSort(T[] array, Comparator<T> comparator, char sortBy) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (sortBy == 'h' ? array[j].compareTo(array[minIdx]) < 0 : comparator.compare(array[j], array[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            T temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }

    /**
     * Merge Sort:
     * This is a divide-and-conquer algorithm that splits the input array into sub arrays,
     * recursively sorts each half, and then uses the merge method to merge the sorted halves back together.
     * 
     * @param takes an array to be sorted
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array, Comparator<T> comparator, char sortBy) {
        if (array.length > 1) {
            int mid = array.length / 2;
            T[] left = Arrays.copyOfRange(array, 0, mid);
            T[] right = Arrays.copyOfRange(array, mid, array.length);
            mergeSort(left, comparator, sortBy);
            mergeSort(right, comparator, sortBy);
            merge(array, left, right, comparator, sortBy);
        }
    }

    /**
     * Merge Method: 
     * This helper method merges two arrays into one sorted array.
     * 
     * @param takes an array which is the original array to store the merged result
     * @param takes the left half of the array
     * @param takes the right half of the array
     */
    private static <T extends Comparable<T>> void merge(T[] array, T[] left, T[] right, Comparator<T> comparator, char sortBy) {
        int p1 = 0, p2 = 0, oIdx = 0;

        while (p1 < left.length && p2 < right.length) {
            int comparisonResult = comparator.compare(left[p1], right[p2]);
            if (sortBy == 'h' ? left[p1].compareTo(right[p2]) <= 0 : comparisonResult <= 0) {
                array[oIdx++] = left[p1++];
            } else {
                array[oIdx++] = right[p2++];
            }
        }

        while (p1 < left.length) {
            array[oIdx++] = left[p1++];
        }

        while (p2 < right.length) {
            array[oIdx++] = right[p2++];
        }
    }
    
    /**
     * Quick Sort:
     * This method picks a "pivot" element and partitions 
     * the array into elements less than the pivot element and greater than the pivot element.
     * It then recursively sorts the sub-arrays.
     * 
     * @param array the array to be sorted
     */
    public static <T extends Comparable<T>> void quickSort(T[] array, Comparator<T> comparator, char sortBy) {
        quickSort(array, 0, array.length - 1, comparator, sortBy);
    }

    /**
     * Quick Sort Helper:
     * This is a helper method that performs the quick sort algorithm. 
     * It first selects a random pivot element from the array and partitions the array into two segments,
     * one with elements less than the pivot element and another with elements greater than the pivot element. 
     * It then recursively uses the same process for each segment until the entire array is sorted.
     * 
     * @param array the array to be sorted
     * @param start the starting index
     * @param end the ending index
     */
    private static <T extends Comparable<T>> void quickSort(T[] array, int start, int end, Comparator<T> comparator, char sortBy) {
        if (start < end) {
            int pivotIndex = new Random().nextInt(end - start + 1) + start;
            T pivot = array[pivotIndex];
            array[pivotIndex] = array[end];
            array[end] = pivot;

            int i = start;
            for (int j = start; j < end; j++) {
                int comparisonResult = comparator.compare(array[j], pivot);
                if (sortBy == 'h' ? array[j].compareTo(pivot) < 0 : comparisonResult < 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                }
            }

            T temp = array[i];
            array[i] = array[end];
            array[end] = temp;

            quickSort(array, start, i - 1, comparator, sortBy);
            quickSort(array, i + 1, end, comparator, sortBy);
        }
    }
    
    /**
     * Custom Sorting Algorithm Max Heap Sort:
     * This method sorts an array by turning it into a max heap, then repeatedly
     * extracting the max element and rebuilding the heap with the remaining elements.
     * 
     * @param array the array to be sorted
     */
    public static <T extends Comparable<T>> void customSort(T[] array, Comparator<T> comparator, char sortBy) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparator, sortBy);
        }

        for (int i = n - 1; i > 0; i--) {
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0, comparator, sortBy);
        }
    }

    /**
     * Heapify:
     * This helper method maintains the heap property for a subtree at the given index.
     * 
     * @param array the array representing the heap
     * @param size the size of the heap
     * @param index the root index of the subtree
     */
    private static <T extends Comparable<T>> void heapify(T[] array, int size, int index, Comparator<T> comparator, char sortBy) {
        int largest = index;
        int leftChildIdx = 2 * index + 1;
        int rightChildIdx = 2 * index + 2;

        if (leftChildIdx < size && (sortBy == 'h' ? array[leftChildIdx].compareTo(array[largest]) > 0 : comparator.compare(array[leftChildIdx], array[largest]) > 0)) {
            largest = leftChildIdx;
        }

        if (rightChildIdx < size && (sortBy == 'h' ? array[rightChildIdx].compareTo(array[largest]) > 0 : comparator.compare(array[rightChildIdx], array[largest]) > 0)) {
            largest = rightChildIdx;
        }

        if (largest != index) {
            T temp = array[index];
            array[index] = array[largest];
            array[largest] = temp;

            heapify(array, size, largest, comparator, sortBy);
        }
    }
}
