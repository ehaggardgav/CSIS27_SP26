/**
 * This program compares the time complexity of array and linked list implementations
 * of stack and queue ADT's, using memory as the response variable, averaging over 
 * several trials (in this case 3 for simplicity)
 */

public class CompareArrayLinked {   
    public static void main(String[] args){
        int[] sizes = {1000, 10000, 100000};
        int trials = 3; 

        for (int n : sizes) {

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
            System.out.println("ArrayList Stack avg memory: " + arrStackMem + " bytes");
            System.out.println("LinkedList Stack avg memory: " + linkStackMem + " bytes");
            System.out.println("ArrayList Queue avg memory: " + arrQueueMem + " bytes");
            System.out.println("LinkedList Queue avg memory: " + linkQueueMem + " bytes");
            System.out.println("----------------------");
        }
    
    }
    
}
