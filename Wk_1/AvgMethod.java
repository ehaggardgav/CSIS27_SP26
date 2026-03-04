import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class AvgMethod {
    public static void main(String [] args) {
        ArrayList<Integer> arrayList = genRandList(5); //populate ArrayList with 5 random integers

        LinkedList<Integer> linkedList = new LinkedList<>(genRandList(5)); //constructor that takes genRandList output to generate a new list

        System.out.println("ArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);

        System.out.println("Average of ArrayList: " + average(arrayList));
        System.out.println("Average of LinkedList: " + calculateAverageLoop(linkedList));
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
        Method "average" calculates the average of integers in an ArrayList
        Returns 0.0 if the list is empty (to avoid division by 0)
        @param list - takes as input values from the list of integers
        @return returns value in double format
    */
    public static double average(ArrayList<Integer> list) { 
        if (list.isEmpty()) { //test if list is empty first
            return 0.0;
        }

        double sum = 0; //initialize sum of integers to 0

        for (int i = 0; i < list.size(); i++) { //for-loop to search entries in ArrayList
            sum += list.get(i); //add each entry to sum
        }

        return sum/list.size(); //return average of the entries

    }  

    /**
        Method "calculateAverageLoop" calculates the average of integers in a LinkedList
        Returns 0.0 if the list is empty (to avoid division by 0)
        @param list - takes as input values from the list of integers
        @return returns value in double format
        For-each loop instead of for-loop leverages the structure of LinkedList, 
        keeping track of the current node, so no need to restart from the first node each time
     */
    public static double calculateAverageLoop(LinkedList<Integer> list) {
        if (list.isEmpty()) { //test if list is empty first
            return 0.0;
        }
        
        double sum = 0; //initialize sum of integers to 0

        for (int num : list) { //for-each loop to keep track of nodes
            sum += num; //add entry at current node to sum
        }

        return sum / list.size(); //return average of the entries

    }
    
}
