import java.util.Arrays;
/**
   A class that implements the ADT sorted list by using a resizable array.
   Duplicate entries are allowed.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.1
*/
public class ArraySortedList<T extends Comparable<? super T>>
                               implements SortedListInterface<T>
{
	private T[] list;			 			// Array of list entries; ignore list[0]
	private int numberOfEntries; 
   private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public ArraySortedList()
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor
	
	public ArraySortedList(int initialCapacity)
	{
      integrityOK = false;

      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      else // Is initialCapacity too big?
         checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempList = (T[])new Comparable[initialCapacity + 1];
      list = tempList;
      numberOfEntries = 0;
      integrityOK = true;
	} // end default constructor

   public void add(T newEntry)
   {
      // getPosition calls getEntry, which  calls checkIntegrity,
      // so we do not have to
      int newEntryPosition = Math.abs(getPosition(newEntry));
      
		// Beginning at the end of the list, shift entries to next higher
		// position until proper sorted position for newEntry is reached
      int currentIndex = numberOfEntries;
      
      // Loop is skipped if sorted list is empty or
      // insertion is at end of sorted list
		while (currentIndex >= newEntryPosition)
      {
         list[currentIndex + 1] = list[currentIndex];
         currentIndex--;
      } // end while
      
		list[currentIndex + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity(); // Ensure enough room for next add
	} // end add

   public boolean remove(T anEntry)
	{
      checkIntegrity();
		boolean found = false;
		
		if (!isEmpty())
		{
			int position = getPosition(anEntry);
         assert position != 0;
			if (position > 0)
			{
				removeGap(position);
				numberOfEntries--;
				found = true;
			} // end if
		} // end if
		
		return found;
	} // end remove

   public int getPosition(T anEntry)
   {
      // Assertion: numberOfEntries >= 0
		int position = 1;
		
      // getEntry calls checkIntegrity, so we do not have to
      
		while ( (position <= numberOfEntries) && (anEntry.compareTo(getEntry(position)) > 0) ) 
		{	
			position++;
		} // end while

		if ( (position > numberOfEntries) || !anEntry.equals(getEntry(position)))
		{
			position = -position;
		} // end if
		
		return position;
	} // end getPosition
	
// List operations
	public T getEntry(int givenPosition)
	{
      checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
			assert !isEmpty();
         return list[givenPosition];
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	public boolean contains(T anEntry)
	{
		return getPosition(anEntry) > 0;
	} // end contains

	public T remove(int givenPosition)
	{
      checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();
         T result = list[givenPosition]; // Get entry to be removed
         
         // Move subsequent entries towards entry to be removed,
         // unless it is last in list
         if (givenPosition < numberOfEntries)
            removeGap(givenPosition);
         
         numberOfEntries--;
         return result;
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
 	} // end remove

	public void clear()
	{
      checkIntegrity();

      // Clear entries but retain array; no need to create a new array
		for (int index = 1; index <= numberOfEntries; index++) // Loop is part of Q4
			list[index] = null;
      
		numberOfEntries = 0;
	} // end clear

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
      return numberOfEntries == 0;
   } // end isEmpty
   
   public T[] toArray()
   {
      checkIntegrity();

      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
      for (int index = 0; index < numberOfEntries; index++)
      {
         result[index] = list[index + 1];
      } // end for
      
      return result;
   } // end toArray
   
   // Doubles the capacity of the array list if it is full.
   // Precondition: checkInitialization has been called.
   private void ensureCapacity()
   {
      int capacity = list.length - 1;
      if (numberOfEntries >= capacity)
      {
         int newCapacity = 2 * capacity;
         checkCapacity(newCapacity);
         list = Arrays.copyOf(list, newCapacity + 1);
      } // end if
   } // end ensureCapacity
   
	// Shifts entries that are beyond the entry to be removed to the
	// next lower position.
   // Precondition: checkInitialization has been called.
	// Precondition: 1 <= givenPosition < numberOfEntries;
	//               numberOfEntries is list's length before removal. */
	private void removeGap(int givenPosition)
	{
      assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
      
      int removedIndex = givenPosition;
      int lastIndex = numberOfEntries;
      
      for (int index = removedIndex; index < lastIndex; index++)
         list[index] = list[index + 1];
	} // end removeGap
   
   // Throws an exception if this object is corrupt.
   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException ("ArraySortedList object is corrupt.");
   } // end checkIntegrity
   
   // Throws an exception if the client requests a capacity that is too large.
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a sorted list " +
                                         "whose capacity is larger than " +
                                         MAX_CAPACITY);
   } // end checkCapacity
} // end ArraySortedList
