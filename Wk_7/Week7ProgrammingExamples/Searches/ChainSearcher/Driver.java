/**
   A driver that demonstrates the sequential searches of
   unsorted and sorted chains by using the class ChainSearcher.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class Driver 
{
   public static void main(String[] args)
   {
      Integer[] unsortedArray = {20, 24, 4, 12, 8, 14};
      Integer[] sortedArray = {4, 8, 12, 14, 20, 24};
      ChainSearcher<Integer> unsortedChain = new ChainSearcher<>();
      fillChain(unsortedChain, unsortedArray);
      ChainSearcher<Integer> sortedChain = new ChainSearcher<>();
      fillChain(sortedChain, sortedArray);

      System.out.println("Iterative sequential search of an unsorted chain: ");
      unsortedChain.display();
      
      System.out.println("Is 4 in the chain? "  + unsortedChain.inUnsortedChainIterative(4) + " (should be true)");
      System.out.println("Is 24 in the chain? " + unsortedChain.inUnsortedChainIterative(4) + " (should be true)");
      System.out.println("Is 14 in the chain? " + unsortedChain.inUnsortedChainIterative(14) + " (should be true)");
      System.out.println("Is 10 in the chain? " + unsortedChain.inUnsortedChainIterative(10) + " (should be false)");
      
      System.out.println("\nRecursive sequential search of an unsorted chain: ");
      unsortedChain.display();
      System.out.println("Is 4 in the chain? "  + unsortedChain.inUnsortedChainRecursive(4) + " (should be true)");
      System.out.println("Is 24 in the chain? " + unsortedChain.inUnsortedChainRecursive(24) + " (should be true)");
      System.out.println("Is 14 in the chain? " + unsortedChain.inUnsortedChainRecursive(14) + " (should be true)");
      System.out.println("Is 10 in the chain? " + unsortedChain.inUnsortedChainRecursive(10) + " (should be false)");
      
      System.out.println("\nIterative sequential search of a sorted chain: ");
      sortedChain.display();
      
      System.out.println("Is 4 in the chain? "  + sortedChain.inSortedChainIterative(4) + " (should be true)");
      System.out.println("Is 24 in the chain? " + sortedChain.inSortedChainIterative(4) + " (should be true)");
      System.out.println("Is 14 in the chain? " + sortedChain.inSortedChainIterative(14) + " (should be true)");
      System.out.println("Is 10 in the chain? " + sortedChain.inSortedChainIterative(10) + " (should be false)");
      System.out.println("Is 15 in the chain? " + sortedChain.inSortedChainIterative(15) + " (should be false)");
      System.out.println("Is 30 in the chain? " + sortedChain.inSortedChainIterative(30) + " (should be false)");
   } // end main
   
   public static void fillChain(ChainSearcher<Integer> chain, Integer[] args)
   {
      for (int i = 0; i < args.length; i++)
         chain.addToEnd(args[i]);
   } // end fillChain
}  // end Driver
/*
 Iterative sequential search of an unsorted chain:
 The chain contains the following entries: 20 24 4 12 8 14
 Is 4 in the chain? true (should be true)
 Is 24 in the chain? true (should be true)
 Is 14 in the chain? true (should be true)
 Is 10 in the chain? false (should be false)
 
 Recursive sequential search of an unsorted chain:
 The chain contains the following entries: 20 24 4 12 8 14
 Is 4 in the chain? true (should be true)
 Is 24 in the chain? true (should be true)
 Is 14 in the chain? true (should be true)
 Is 10 in the chain? false (should be false)
 
 Iterative sequential search of a sorted chain:
 The chain contains the following entries: 4 8 12 14 20 24
 Is 4 in the chain? true (should be true)
 Is 24 in the chain? true (should be true)
 Is 14 in the chain? true (should be true)
 Is 10 in the chain? false (should be false)
 Is 15 in the chain? false (should be false)
 Is 30 in the chain? false (should be false)
 
 */
