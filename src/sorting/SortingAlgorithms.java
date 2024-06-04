package sorting;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    /**
     * Bubble Sort:
     * This method is used to sort an array by repeatedly stepping through the list,
     * comparing the elements and swapping them if they're in the wrong order.
     * It continues this process until the list is sorted.
     * 
     * @param takes an array to be sorted
     */
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

    /**
     * Insertion Sort:
     * This method builds a sorted array one element at a time, it does this by repeatedly taking the next element
     * and inserting it into the correct position in the already sorted part of the array.
     * 
     * @param takes an array to be sorted
     */
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

    /**
     * Selection Sort:
     * This method sorts an array by dividing it into a sorted and an unsorted part.
     * It repeatedly selects the smallest or largest element from the unsorted part and moves it to the sorted.
     * 
     * @param takes an array to be sorted
     */
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

    /**
     * Merge Sort:
     * This is a divide-and-conquer algorithm that splits the input array into sub arrays,
     * recursively sorts each half, and then uses the merge method to merge the sorted halves back together.
     * 
     * @param takes an array to be sorted
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array.length == 1) {
            return;
        }
        
        int mid = array.length / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }
    
    /**
     * Merge Method: 
     * This helper method merges two arrays into one sorted array.
     * 
     * @param takes an array which is the original array to store the merged result
     * @param takes the left half of the array
     * @param takes the right half of the array
     */
    private static <T extends Comparable<T>> void merge(T[] array, T[] left, T[] right) {
        int p1 = 0, p2 = 0, oIdx = 0;
        
        // Merge the two halves back into the original array
        while (p1 < left.length && p2 < right.length) {
            if (left[p1].compareTo(right[p2]) <= 0) {
                array[oIdx++] = left[p1++];
            } else {
                array[oIdx++] = right[p2++];
            }
        }
        
        
        // Copy the remaining elements of left and right into the original array
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
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
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
    public static <T extends Comparable<T>> void quickSort(T[] array, int start, int end) {
        if (start < end) {
        	
            // Choose a random pivot element and move it to the end
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
            
            // Place the pivot in its correct array position
            T temp = array[i];
            array[i] = array[end];
            array[end] = temp;

            // Recursively sort the left and right parts
            quickSort(array, start, i - 1);
            quickSort(array, i + 1, end);
        }
    }

    /**
     * Heap Sort:
     * This method sorts an array by turning it into a max heap, then repeatedly
     * extracting the max element and rebuilding the heap with the remaining elements.
     * 
     * @param array the array to be sorted
     */
    public static <T extends Comparable<T>> void customSort(T[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {

        	T temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            
            heapify(array, i, 0);
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
    private static <T extends Comparable<T>> void heapify(T[] array, int size, int index) {
        int largest = index;
        int leftChildIdx = 2 * index + 1;
        int rightChildIdx = 2 * index + 2;

        if (leftChildIdx < size && array[leftChildIdx].compareTo(array[largest]) > 0) {
            largest = leftChildIdx;
        }

        if (rightChildIdx < size && array[rightChildIdx].compareTo(array[largest]) > 0) {
            largest = rightChildIdx;
        }

        if (largest != index) {
            T swap = array[index];
            array[index] = array[largest];
            array[largest] = swap;

            heapify(array, size, largest);
        }
    }
}
