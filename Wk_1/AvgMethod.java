import java.util.ArrayList;
import java.util.LinkedList;

public class AvgMethod {
    public static void main(String [] args) {
    }  
    
    /**
    * Method "average" calculates the average of integers in an ArrayList
    * @param list - takes as input values from the list of integers
    * @return returns value in double format
    * Returns 0.0 if the list is empty (to avoid division by 0)
    */
    public static double average(ArrayList<Integer> list) { 
        double sum = 0; //initialize sum of integers to 0

        for (int i = 0; i < list.size(); i++) { //for-loop to search entries in ArrayList
            sum += list.get(i); //add each entry to sum
        }

        if (list.size() == 0) { //return 0.0 if list is empty
            return 0.0;
        }

        return sum/list.size(); //else return average of the entries

    }  

    /**
     * Method "averageLinked" calculates the average of integers in a LinkedList
     * @param list - takes as input values from the list of integers
     * @return returns value in double format
     * Returns 0.0 if the list is empty (to avoid division by 0)
     * For-each loop instead of for-loop leverages the structure of LinkedList, 
     * keeping track of the current node, so no need to restart from the first node each time
     */
    public static double averageLinked(LinkedList<Integer> list) {

        double sum = 0; //initialize sum of integers to 0

        for (int num : list) { //for-each loop to keep track of nodes
            sum += num; //add entry at current node to sum
        }

        if (list.size() == 0) { //return  0.0 if list is empty
            return 0.0;
        }

        return sum / list.size(); //else return average of the entries
    }
    
}
