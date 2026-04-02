import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class MedianMethod {
    public static void main(String [] args) {
        ArrayList<Integer> arrayList = genRandList(9); //populate ArrayList with 5 random integers

        System.out.println("ArrayList: " + arrayList);

        System.out.println("Median: " + Median(arrayList));
    }  

    /**
        Method "genRandList" generates an ArrayList containing random integers.
        @param n The number of random integers to generate.
        @return An ArrayList<Integer> containing n random integers between 0 and 99.
    */
    public static ArrayList<Integer> genRandList(int n) { //store random integers in empty ArrayList
        ArrayList<Integer> list = new ArrayList<>(); 
        Random rand = new Random();  //object to generate random numbers
        for (int i = 0; i < n; i++) { //for-loop to repeat for n integers
            int randomInt = rand.nextInt(100);
            list.add(randomInt);
        }
        return list;
    }
    
    /**
        Method "Median" finds the median of integers in an ArrayList
        @param list - takes as input values from the list of integers
        @return returns value in double format
    */
    public static double Median(ArrayList<Integer> list) { 
        if (list.isEmpty()) { //test if list is empty first
            return 0.0;
        }

        Collections.sort(list); //arrange list values in order

        int n = list.size(); //get size of list

        if (n % 2 == 1) { //if list size is odd, take number at n/2
            return list.get(n/2);
        }

        else { //if list is even, take average of middle numbers
            return (list.get(n/2 - 1) + list.get(n/2)) / 2;
        }
    }  

    
    
}
