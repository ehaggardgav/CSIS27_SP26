import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This program compares the time complexity of array and linked list implementations
 * of stack and queue ADT's, using memory as the response variable, averaging over 
 * several trials (in this case 3 for simplicity)
 */

public class CompareArrayLinked {   
    public static void main(String[] args){
        int[] sizes = {1000, 10000, 100000}; //specify multiple input sizes for better resolution
        int trials = 3; 

        for (int n : sizes) {
            //use long  to keep track of bytes, and use lambdas to be able to reuse the same test method for each test case
            long arrStackMem = averageMemory(trials, () -> {  
                ArrayList<Integer> stack = new ArrayList<>();
                for (int i = 0; i < n; i++) stack.add(i);
                for (int i = 0; i < n; i++) stack.remove(stack.size() - 1);
            });

            long linkStackMem = averageMemory(trials, () -> {
                LinkedList<Integer> stack = new LinkedList<>();
                for (int i = 0; i < n; i++) stack.addLast(i);
                for (int i = 0; i < n; i++) stack.removeLast();
            });

            long arrQueueMem = averageMemory(trials, () -> {
                ArrayList<Integer> queue = new ArrayList<>();
                for (int i = 0; i < n; i++) queue.add(i);
                for (int i = 0; i < n; i++) queue.remove(0);
            });

            long linkQueueMem = averageMemory(trials, () -> {
                LinkedList<Integer> queue = new LinkedList<>();
                for (int i = 0; i < n; i++) queue.addLast(i);
                for (int i = 0; i < n; i++) queue.removeFirst();
            });

            System.out.println("Input size: " + n);
            System.out.printf("%-20s %12d bytes%n", "ArrayList Stack:", arrStackMem);
            System.out.printf("%-20s %12d bytes%n", "LinkedList Stack:", linkStackMem);
            System.out.printf("%-20s %12d bytes%n", "ArrayList Queue:", arrQueueMem);
            System.out.printf("%-20s %12d bytes%n", "LinkedList Queue:", linkQueueMem);
            System.out.println("----------------------");
        }
    
    }

    /**
     * Method 'averageMemeory' runs a memory test multiple times and returns the average memory used.
     *
     * @param trials Number of times to repeat the test
     * @param test A Runnable containing the operations to measure
     * @return Average memory used (in bytes)
     */
    public static long averageMemory(int trials, Runnable test) {
        long totalMemory = 0;

        for (int i = 0; i < trials; i++) {
            totalMemory += runMemoryTest(test);
        }

        return totalMemory / trials;
    }

    /**
     * Method 'runMemoryTest' measures memory before and after 
     * running the test and returns the difference.
     *
     * @param test A Runnable containing the operations to measure
     * @return Memory used (in bytes) during the test
     */
    public static long runMemoryTest(Runnable test) {
        Runtime runtime = Runtime.getRuntime();

        System.gc(); //suggest garbage collection to reduce leftover memory

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        test.run();

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        return memoryAfter - memoryBefore;
    }
    
}
