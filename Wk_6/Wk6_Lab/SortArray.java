/**
 * This is a collection of sorting algorithms that demonstrate
 * step-by-step transformations of an array during sorting.
 *
 * Each algorithm prints intermediate array states to show
 * how the list of characters changes through the sorting process.
 */

import java.util.Arrays;

public class SortArray {

    public static <T> void printArray(T[] a) {
        System.out.println(Arrays.toString(a));
    }

    //Selection sort
    public static <T extends Comparable<? super T>>
    void selectionSort(T[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            swap(a, i, min);
            printArray(a);
        }
    }

    //Shell sort
    public static <T extends Comparable<? super T>>
    void shellSort(T[] a, int n) {

        for (int gap = n / 2; gap > 0; gap = gap / 2) {

            //If gap is even, make it odd (better behavior)
            if (gap % 2 == 0 && gap > 1) {
                gap++;
            }

            //Perform gap-based insertion sort passes
            for (int start = 0; start < gap; start++) {

                for (int i = start + gap; i < n; i += gap) {

                    T temp = a[i];
                    int j = i;

                    while (j - gap >= 0 &&
                        a[j - gap].compareTo(temp) > 0) {
                        a[j] = a[j - gap];
                        j -= gap;
                    }

                    a[j] = temp;
                }
            }

            printArray(a);
        }
    }

    //Merge sort (iterative)
    public static <T extends Comparable<? super T>>
    void iterativeMergeSort(T[] a, int n) {

        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[n];

        for (int size = 1; size < n; size *= 2) {

            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                merge(a, temp, left, mid, right);
            }

            printArray(a);
        }
    }

    private static <T extends Comparable<? super T>>
    void merge(T[] a, T[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (a[i].compareTo(a[j]) <= 0) temp[k++] = a[i++];
            else temp[k++] = a[j++];
        }

        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        for (int x = left; x <= right; x++) {
            a[x] = temp[x];
        }
    }

    //Quick sort
    public static <T extends Comparable<? super T>>
    void quickSort(T[] a, int low, int high) {

        if (low < high) {
            int p = partition(a, low, high);

            printArray(a);

            quickSort(a, low, p - 1);
            quickSort(a, p + 1, high);
        }
    }

    private static <T extends Comparable<? super T>>
    int partition(T[] a, int low, int high) {
        T pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j].compareTo(pivot) <= 0) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);
        return i + 1;
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
