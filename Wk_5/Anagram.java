import java.util.Scanner;

public class Anagram {

public static void main (String[] args) {
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
    
}
