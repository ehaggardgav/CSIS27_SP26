import java.util.Scanner;

/**
 * AnagramFive
 * 
 * This program determines whether two words are anagrams of each other.
 * It converts the words to arryas, uses recursive selection sort to sort 
 * both words, then compares them.
 */
public class AnagramFive {

    public static void main(String[] args) {

        //Create Scanner object for user input
        Scanner input = new Scanner(System.in);

        //Prompt user input
        System.out.print("Enter first word: ");
        String word1 = input.nextLine();

        System.out.print("Enter second word: ");
        String word2 = input.nextLine();

        //Call the anagram checking method
        boolean result = isAnagram(word1, word2);

        //Display result to the user
        if (result) {
            System.out.println("The words are anagrams.");
        } else {
            System.out.println("The words are NOT anagrams.");
        }

        input.close();
    }

    /**
     * Method 'isAnagram' Determines if two strings are anagrams.
     * it onverts strings to character arrays, sorts them recursively,
     * and compares the sorted arrays.
     */
    public static boolean isAnagram(String str1, String str2) {

        //Check equal lengths
        if (str1.length() != str2.length()) {
            return false;
        }

        //Convert strings into character arrays
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        //Sort both arrays using recursive selection sort
        selectionSort(arr1, 0);
        selectionSort(arr2, 0);

        //Compare the sorted arrays recursively
        return arraysEqual(arr1, arr2, 0);
    }

    /**
     * Method 'selectionSort' recursively sorts a character array using selection sort.
     * 
     * @param arr   The array to sort
     * @param index The current position being sorted
     */
    public static void selectionSort(char[] arr, int index) {

        //Base case: if we reach the end of the array, stop recursion
        if (index >= arr.length - 1) {
            return;
        }

        //Find the index of the smallest element in the remaining array
        int minIndex = findMinIndex(arr, index, index);

        //Swap the current element with the smallest element found
        char temp = arr[index];
        arr[index] = arr[minIndex];
        arr[minIndex] = temp;

        //Recursively sort the rest of the array
        selectionSort(arr, index + 1);
    }

    /**
     * Method 'findMinIndex' recursively finds the index of the smallest element in the array.
     * 
     * @param arr      The array being searched
     * @param current  The current index being checked
     * @param minIndex The index of the smallest value found so far
     * @return The index of the smallest element
     */
    public static int findMinIndex(char[] arr, int current, int minIndex) {

        //Base case: reached end of array
        if (current == arr.length) {
            return minIndex;
        }

        //Update minIndex if a smaller element is found
        if (arr[current] < arr[minIndex]) {
            minIndex = current;
        }

        //Recursively check the next element
        return findMinIndex(arr, current + 1, minIndex);
    }

    /**
     * Method 'arraysEqual recursively compares two character arrays for equality.
     * 
     * @param arr1  First array
     * @param arr2  Second array
     * @param index Current index being compared
     * @return true if arrays are identical, false otherwise
     */
    public static boolean arraysEqual(char[] arr1, char[] arr2, int index) {

        //Base case: all elements have been successfully compared
        if (index == arr1.length) {
            return true;
        }

        //If any pair of elements differ, arrays are not equal
        if (arr1[index] != arr2[index]) {
            return false;
        }

        //Recursively compare the next elements
        return arraysEqual(arr1, arr2, index + 1);
    }
}