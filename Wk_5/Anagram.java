import java.util.Scanner;

public class Anagram {

public static void main (String[] args) {
    
    //user input
    Scanner input = new Scanner(System.in);

    System.out.print("Enter word 1: "); 
    String word1 = input.nextLine();

    System.out.print("Enter word 2: ");
    String word2 = input.nextLine();
    
    boolean result = isAnagram(word1, word2);

    if (result) {
        System.out.println("The words are anagrams.");
    } else {
        System.out.println("The words are NOT anagrams.");
    }

    input.close();
}

public static boolean isAnagram(String, str1, String str2) {
    //check for different word lengths first
    if (str1.length() != str2.length()) {
        return false;
    }

    //now convert strings to char arrays
    char [] arr1 = str1.toCharArray();
    char [] arr2 = str2.toCharArray();

    //recursive sorting for each array
    selectionSort(arr1, 0);
    selectionSort(arr2, 0);

    return arraysEqual(arr1, arr2, 0);
}
    
}
