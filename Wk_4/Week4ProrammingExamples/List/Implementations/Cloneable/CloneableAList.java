import java.util.Arrays;
/**
 A class that implements a cloneable list of cloneable objects by using an array.
 Entries in a list have positions that begin with 1.
 Duplicate entries are allowed.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
*/
public class CloneableAList<T extends Copyable> implements CloneableListInterface<T>
{
   private T[] list;   // Array of list entries; ignore list[0]
   private int numberOfEntries;
   private boolean integrityOK;
   private static final int DEFAULT_CAPACITY = 25;
   private static final int MAX_CAPACITY = 10000;
   
   public CloneableAList()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
   
   public CloneableAList(int initialCapacity)
   {
      integrityOK = false;
      
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      else // Is initialCapacity too big?
         checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempList = (T[])new Copyable[initialCapacity + 1];
      list = tempList;
      numberOfEntries = 0;
      integrityOK = true;
   } // end constructor

   public Object clone()
   {
      CloneableAList<T> theCopy = null;

      // Clone the list
      try
      {
         @SuppressWarnings("unchecked")
         CloneableAList<T> temp = (CloneableAList<T>)super.clone();
         theCopy = temp;
      }
      catch (CloneNotSupportedException e)
      {
         throw new Error(e.toString());
      }

      // Clone the list's array
      theCopy.list = list.clone();

      // Clone the entries in the array (list[0] is unused and ignored)
      for (int index = 1; index <= numberOfEntries; index++)
      {
         @SuppressWarnings("unchecked")
         T temp = (T)list[index].clone();
         theCopy.list[index] = temp;
      } // end for

      return theCopy;
   } // end clone

   public void add(T newEntry)
	{
      add(numberOfEntries + 1, newEntry);
/*    // Alternate code
		checkIntegrity();
      list[numberOfEntries + 1] = newEntry;
      numberOfEntries++;
      ensureCapacity(); 
*/
	} // end add
   
   public void add(int givenPosition, T newEntry)
   {
      checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
      {
         if (givenPosition <= numberOfEntries)
            makeRoom(givenPosition);
         
         // Add clone of new entry to list
         @SuppressWarnings("unchecked")
         T newEntryClone = (T)newEntry.clone();
         list[givenPosition] = newEntryClone;
         
         numberOfEntries++;
         ensureCapacity(); // Ensure enough room for next add
      }
      else
         throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
   } // end add
   
   public T remove(int givenPosition)
   {
      checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: The list is not empty
         T result = list[givenPosition]; // Get entry to be removed
         
         // Move subsequent entries towards entry to be removed,
         // unless it is last in list
         if (givenPosition < numberOfEntries)
            removeGap(givenPosition);
         list[numberOfEntries] = null;
         numberOfEntries--;
         return result;                  // Return reference to removed entry
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

	public T replace(int givenPosition, T newEntry)
	{
		checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: The list is not empty
         T originalEntry = list[givenPosition];
         list[givenPosition] = newEntry;
         return originalEntry;
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } // end replace

	public T getEntry(int givenPosition)
	{
		checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: The list is not empty
         // Clone the entry before returning it
         // (NB: Since the list contains clones of the client's original entries,
         // returning a clone of a clone might be unnecessary.
         @SuppressWarnings("unchecked")
         T entryClone = (T)list[givenPosition].clone();
         return entryClone;
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

   public T[] toArray()
   {
		checkIntegrity();
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Copyable[numberOfEntries]; // Unchecked cast
      for (int index = 0; index < numberOfEntries; index++)
      {
         result[index] = list[index + 1];
      } // end for
      
      return result;
   } // end toArray
   
   public boolean contains(T anEntry)
   {
      checkIntegrity();
      boolean found = false;
      int index = 1;
      while (!found && (index <= numberOfEntries))
      {
         if (anEntry.equals(list[index]))
            found = true;
         index++;
      } // end while
      return found;
   } // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength
   
   public boolean isEmpty()
   {
      return numberOfEntries == 0; // Or getLength() == 0
   } // end isEmpty
   
   // Doubles the capacity of the array list if it is full.
   // Precondition: checkIntegrity has been called.
   private void ensureCapacity()
   {
      int capacity = list.length - 1;
      if (numberOfEntries >= capacity)
      {
         int newCapacity = 2 * capacity;
         checkCapacity(newCapacity); // Is capacity too big?
         list = Arrays.copyOf(list, newCapacity + 1);
      } // end if
   } // end ensureCapacity

   // Makes room for a new entry at newPosition.
   // Precondition: 1 <= newPosition <= numberOfEntries + 1;
	//               numberOfEntries is list's length before addition;
   //               checkIntegrity has been called.
   private void makeRoom(int givenPosition)
   {
      // Assertion: (newPosition >= 1) && (newPosition <= numberOfEntries + 1)
      int newIndex = givenPosition;
      int lastIndex = numberOfEntries;
      
      // Move each entry to next higher index, starting at end of
      // list and continuing until the entry at newIndex is moved
      for (int index = lastIndex; index >= newIndex; index--)
         list[index + 1] = list[index];
   }  // end makeRoom

   // Shifts entries that are beyond the entry to be removed to the
   // next lower position.
   // Precondition: 1 <= givenPosition < numberOfEntries;
   //               numberOfEntries is list's length before removal;
   //               checkIntegrity has been called.
     private void removeGap(int givenPosition)
   {
      int removedIndex = givenPosition;
      for (int index = removedIndex; index < numberOfEntries; index++)
         list[index] = list[index + 1];
   } // end removeGap
   
   // Throws an exception if this object is corrupt.
   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException ("AList object is corrupt.");
   } // end checkIntegrity
   
   // Throws an exception if the client requests a capacity
   // that is either too small or too large.
   private void checkCapacity(int capacity)
   {
      if (capacity < DEFAULT_CAPACITY)
         throw new IllegalStateException("Attempt to create a heap " +
                                         "whose capacity is smaller than " +
                                         DEFAULT_CAPACITY);
      else if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a heap " +
                                         "whose capacity is larger than " +
                                         MAX_CAPACITY);
   } // end checkCapacity
} // end CloneableAList

