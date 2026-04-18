 /**
  * This driver program compares multiple sorting algorithms
  * using the list of characters: ["G", "A", "R", "L", "I", "C"].
  *
  * The program displays step-by-step execution of:
  * selection sort, shell sort, iterative merge sort, and quick sort
  * on identical copies of the original array.
  *
  * Each sort method prints the intermediate states of the array to
  * show how the algorithm transforms the data over time.
  *
  * This driver also demonstrates insertion sort using a linked list
  * and prints the list both before and after sorting.
  */

public class Driver {

    public static void main(String[] args) {

        String[] items = {"G", "A", "R", "L", "I", "C"};

        runArraySort("Selection Sort", items, 1);
        runArraySort("Shell Sort", items, 2);
        runArraySort("Merge Sort", items, 3);
        runArraySort("Quick Sort", items, 4);

        runLinkedInsertionSort(items);
    }

    public static void runArraySort(String name, String[] original, int type) {
        System.out.println("\n" + name + ":");

        String[] arr = original.clone();

        System.out.print("Before: ");
        display(arr);

        switch (type) {
            case 1:
                SortArray.selectionSort(arr, arr.length);
                break;
            case 2:
                SortArray.shellSort(arr, arr.length);
                break;
            case 3:
                SortArray.iterativeMergeSort(arr, arr.length);
                break;
            case 4:
                SortArray.quickSort(arr, 0, arr.length - 1);
                break;
        }

        System.out.print("After:  ");
        display(arr);
    }

    public static void runLinkedInsertionSort(String[] data) {
        System.out.println("\nInsertion Sort (Linked List):");

        LinkedInsertionSort<String> list = new LinkedInsertionSort<>();

        for (int i = data.length - 1; i >= 0; i--) {
            list.add(data[i]);
        }

        System.out.print("Before: ");
        display(list.toArray());

        list.sort();

        System.out.print("After:  ");
        display(list.toArray());
    }

    public static void display(Object[] array) {
        for (Object o : array) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}
