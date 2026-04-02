// Simple but inefficient implementation that extends LList.
// Obtained from Composition version by omitting the data field list, 
// omitting or revising the constructor, and
// replacing "list" with "super" in calls to methods of LList.
import java.util.*;
/**
   A class that implements the ADT sorted list by extending LList.
   Duplicate entries are allowed.
  
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class SortedList<T extends Comparable<? super T>>
             extends LList<T> implements SortedListInterface<T>
{
   // If you omit this constructor, the compiler will provide it.
   public SortedList()
   {
      super();
   } // end default constructor

   public void add(T newEntry)
   {
      int newPosition = Math.abs(getPosition(newEntry));
      super.add(newPosition, newEntry);
   } // end add

   public boolean remove(T anEntry)
   {
      int position = getPosition(anEntry);
      
      // If position < 0, anEntry is not in list
      if (position > 0)
         super.remove(position);
         
      return position > 0;	
   } // end remove

   public int getPosition(T anEntry)
   {
      int position = 1;
      int length = super.getLength();

      while ( (position <= length) && (anEntry.compareTo(super.getEntry(position)) > 0) )
      {	
         position++;
      } // end while

      if ( (position > length) || (anEntry.compareTo(super.getEntry(position)) != 0) )
      {
         position = -position;
      } // end if
      
      return position;
   } // end getPosition

   public boolean contains(T anEntry)
   {
      return getPosition(anEntry) > 0;
   } // end contains

   public void add(int newPosition, T newEntry)
   {
      throw new UnsupportedOperationException("Illegal attempt to add at a " +
                                              "specified position within a sorted list.");	
   } // end add

   public T replace(int givenPosition, T newEntry)
   {
      throw new UnsupportedOperationException("Illegal attempt to replace " +
                                              "an entry within a sorted list.");	
   } // end replace

   // Must override this method, because toArray in LList
   // allocates an array of objects that cannot be cast to Comparable objects.
   // Note that we must use the methods in LList to access the list.
   public T[] toArray()
   {
      int numberOfEntries = getLength();
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Comparable[numberOfEntries]; // Warning: [unchecked] unchecked cast

      for (int index = 0; index < numberOfEntries; index++)
         result[index] = super.getEntry(index + 1);

      return result;
   } // end toArray
} // end SortedList
