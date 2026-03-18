import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This program compares the time complexity of ArrayList and LinkedList
 * implementations of stack and queue ADTs, using execution time as the response variable.
 * Each operation is averaged over several trials (3 in this example).
 */

public class CompareArrayLinkedTime {   
    public static void main(String[] args){
        int[] sizes = {1000, 10000, 100000}; //multiple input sizes for better resolution
        int trials = 3; //number of trials to average time measurements

        //for-loop over each input size
        for (int n : sizes) {

            //measure average time (nanoseconds) for each data structure operation
            long arrStackTime = averageTime(trials, () -> {  
                ArrayList<Integer> stack = new ArrayList<>();
                for (int i = 0; i < n; i++) stack.add(i);                   //push
                for (int i = 0; i < n; i++) stack.remove(stack.size() - 1); //pop
            });

            long linkStackTime = averageTime(trials, () -> {
                LinkedList<Integer> stack = new LinkedList<>();
                for (int i = 0; i < n; i++) stack.addLast(i);       //push
                for (int i = 0; i < n; i++) stack.removeLast();     //pop
            });

            long arrQueueTime = averageTime(trials, () -> {
                ArrayList<Integer> queue = new ArrayList<>();
                for (int i = 0; i < n; i++) queue.add(i);                 //enqueue
                for (int i = 0; i < n; i++) queue.remove(0);       //dequeue
            });

            long linkQueueTime = averageTime(trials, () -> {
                LinkedList<Integer> queue = new LinkedList<>();
                for (int i = 0; i < n; i++) queue.addLast(i);      //enqueue
                for (int i = 0; i < n; i++) queue.removeFirst();   //dequeue
            });

            //print formatted results 
            System.out.println("Input size: " + n);
            System.out.printf("%-20s %12d ns%n", "ArrayList Stack:", arrStackTime);
            System.out.printf("%-20s %12d ns%n", "LinkedList Stack:", linkStackTime);
            System.out.printf("%-20s %12d ns%n", "ArrayList Queue:", arrQueueTime);
            System.out.printf("%-20s %12d ns%n", "LinkedList Queue:", linkQueueTime);
            System.out.println("----------------------");
            
        }
    }

    /**
     * Method 'averageTime' runs a time test multiple times and returns the average time.
     *
     * @param trials Number of times to repeat the test
     * @param test A Runnable containing the operations to measure
     * @return Average time (in nanoseconds) for the test
     */
    public static long averageTime(int trials, Runnable test) {
        long totalTime = 0;

        for (int i = 0; i < trials; i++) {
            totalTime += runTimeTest(test);
        }

        return totalTime / trials;
    }

    /**
     * Method 'runTimeTest' measures execution time of a single run of a block of code.
     *
     * @param test A Runnable containing the operations to measure
     * @return Time taken (in nanoseconds)
     */
    public static long runTimeTest(Runnable test) {
        long start = System.nanoTime(); //start timer
        test.run();                     //run operations
        long end = System.nanoTime();   //end timer
        return end - start;             //return elapsed time
    }
}