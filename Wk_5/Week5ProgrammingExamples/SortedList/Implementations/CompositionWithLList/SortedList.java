/**
 A class that implements the ADT sorted list by using an instance of LList.
 
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
public class SortedList<T extends Comparable<? super T>>
implements SortedListInterface<T>
{
   private ListInterface<T> list;
   
   public SortedList()
   {
      list = new LList<T>();
   } // end default constructor

   public void add(T newEntry)
   {
      int newPosition = Math.abs(getPosition(newEntry));
      list.add(newPosition, newEntry);
   } // end add

   public boolean remove(T anEntry)
   {
      boolean result = false;
      int position = getPosition(anEntry);
      
      if (position > 0)
      {
         list.remove(position);
         result = true;
      } // end if
      
      return result;
   } // end remove

   public int getPosition(T anEntry)
   {
      int position = 1;
      int length = list.getLength();
      
      // Find position of anEntry
      while ( (position <= length) &&
             (anEntry.compareTo(list.getEntry(position)) > 0) )
      {
         position++;
      } // end while
      
      // See whether anEntry is in list
      if ( (position > length) ||
          (anEntry.compareTo(list.getEntry(position)) != 0) )
      {
         position = -position; // anEntry is not in list
      } // end if
      
      return position;
   } // end getPosition
   /*
   // ALTERNATE: exercise to call getEntry once per iteration
   public int getPosition(T anEntry)
   {
      int position = 1;
      int length = list.getLength();

      // find position of anEntry
      T entry = null;
      boolean found = false;
      while (!found && (position <= length))
      { 
         entry = list.getEntry(position);
         if (anEntry.compareTo(entry) > 0) )
            position++;
         else 
            found = true;
      } // end while

      // see whether anEntry is in list
      if ( (position > length) || (anEntry.compareTo(entry) != 0) )
      {
         position = -position; // anEntry is not in list
      } // end if

      return position;
   } // end getPosition
   */	

   // ADT list operations
   public boolean contains(T anEntry)
   {
      return getPosition(anEntry) > 0; 
   } // end contains 

   public T getEntry(int givenPosition)
   {
      return list.getEntry(givenPosition);
   } // end getEntry

   public T remove(int givenPosition)
   {
      return list.remove(givenPosition);
   } // end remove

   public void clear()
   {
      list.clear();
   } // end clear

   public int getLength()
   {
      return list.getLength();
   } // end getLength

   public boolean isEmpty()
   {  
      return list.isEmpty();
   } // end isEmpty

   // Must override this method, because toArray in LList
   // allocates an array of objects that cannot be cast to Comparable objects.
   // Note that we must use the methods in LList  to access the list.
   public T[] toArray()
   {
      int numberOfEntries = list.getLength();
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Comparable[numberOfEntries]; // Warning: [unchecked] unchecked cast

      for (int index = 0; index < numberOfEntries; index++)
         result[index] = list.getEntry(index + 1);

      return result;
   } // end toArray
   
/* The following method will not work! Cannot cast Objects to Comparable.
   public T[] toArray()
   {
      return list.toArray();
   } // end toArray
*/
} // end SortedList
