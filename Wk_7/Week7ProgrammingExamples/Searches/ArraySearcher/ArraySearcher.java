
/**
   A class of static methods for searching an array of objects.
   The objects in a sorted array must be Comparable.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class ArraySearcher
{
   // Segment 19.3
   /** Searches an unsorted array for anEntry iteratively. */
   public static <T> boolean inUnsortedArrayIterative(T[] anArray, T anEntry)
   {
      boolean found = false;
      int index = 0;
      
      while (!found && (index < anArray.length))
      {
         if (anEntry.equals(anArray[index]))
            found = true;
         index++;
      } // end while
      
      return found;
   } // end inUnsortedArrayIterative
// ========================================================================================
   
   // Segment 19.6
   /** Searches an unsorted array for anEntry by calling a recursive private method. */
   public static <T> boolean inUnsortedArrayRecursive(T[] anArray, T anEntry)
   {
      return search(anArray, 0, anArray.length - 1, anEntry);
   } // end inUnsortedArrayRecursive
   
   // Recursively searches anArray[first] through anArray[last] for desiredItem.
   // first >= 0 and < anArray.length.
   // last >= 0 and < anArray.length.
   private static <T> boolean search(T[] anArray, int first, int last, T desiredItem)
   {
      boolean found = false;
      
      if (first > last)
         found = false; // No elements to search
      else if (desiredItem.equals(anArray[first]))
         found = true;
      else
         found = search(anArray, first + 1, last, desiredItem);
      
      return found;
   } // end search
// ========================================================================================

   // Segment 19.13
   /** Searches a sorted array for anEntry by calling a recursive private method. */
   public static <T extends Comparable<? super T>> boolean inSortedArrayRecursive(T[] anArray, T anEntry)
   {
      return binarySearch(anArray, 0, anArray.length - 1, anEntry);
   } // end inSortedArrayRecursive
   
   // Searches anArray[first] through anArray[last] for desiredItem.
   // first >= 0 and < anArray.length.
   // last >= 0 and < anArray.length.
   private static <T extends Comparable<? super T>>
           boolean binarySearch(T[] anArray, int first, int last, T desiredItem)
   {
      boolean found;
      int mid = first + (last - first) / 2;
      
      if (first > last)
         found = false;
      else if (desiredItem.equals(anArray[mid]))
         found = true;
      else if (desiredItem.compareTo(anArray[mid]) < 0)
         found = binarySearch(anArray, first, mid - 1, desiredItem);
      else
         found = binarySearch(anArray, mid + 1, last, desiredItem);
         
      return found;
   } // end binarySearch
// ========================================================================================

   public static <T> void display(T[] anArray)
   {
      System.out.print("The array contains the following entries: ");
      for (int index = 0; index < anArray.length; index++)
      {
         System.out.print(anArray[index] + " ");
      } // end for
      
      System.out.println();
   }  // end display
} // end ArraySearcher

