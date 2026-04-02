/**
   A class that implements the ADT sorted list by extending LinkedChainBase.
   Duplicate entries are allowed.
  
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class LinkedSortedList<T extends Comparable<? super T>>
             extends LinkedChainBase<T>
             implements SortedListInterface<T>
{
	public LinkedSortedList()
	{
		super();
	} // end default constructor

	public void add(T newEntry)
	{
		Node theNode = new Node(newEntry);
		Node nodeBefore = getNodeBefore(newEntry);

		if (nodeBefore == null)  // No need to call isEmpty
			addFirstNode(theNode);
		else
			addAfterNode(nodeBefore, theNode);
	} // end add

	public boolean remove(T anEntry)
	{
		boolean found = false;

		if (getLength() > 0)
		{
			Node nodeToRemove;
			Node nodeBefore = getNodeBefore(anEntry);

			if (nodeBefore == null)
			{
				// Node to remove would be at the front of the list
				nodeToRemove = getFirstNode();
				
				if (anEntry.equals(nodeToRemove.getData()))
				{
					// Remove the front node
					removeFirstNode();
					found = true;
				} // end if
			}
			else
			{
				// Node to remove would be after the front
				nodeToRemove = nodeBefore.getNextNode();
				
				if ( (nodeToRemove != null) && anEntry.equals(nodeToRemove.getData()) )
				{
					// Found it, remove it
					removeAfterNode(nodeBefore);
					found = true;
				} // end if
			} // end if
		} // end if

		return found;
	} // end remove

	public int getPosition(T anEntry)
	{
		int position = 1;
		Node currentNode = getFirstNode();

		while ( (currentNode != null) && ( anEntry.compareTo(currentNode.getData()) > 0) )
		{
			currentNode = currentNode.getNextNode();
			position++;
		} // end while

		if ( (currentNode == null) || anEntry.compareTo(currentNode.getData()) != 0)
		{
			position = -position;
		} // end if

		return position;
	} // end getPosition

	// Overriding contains with a more efficient version
	public boolean contains(T anEntry)
	{
		Node currentNode = getFirstNode();

		while ( (currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0) )
		{
			currentNode = currentNode.getNextNode();
		} // end while

		return (currentNode != null) && anEntry.equals(currentNode.getData());
	} // end contains

	// Finds the node that is before the node that should or does contain a given entry.
   // Parameter: anEntry  The object to be located.
	// Returns either a reference to the node that is before the node that contains
	// or should contain anEntry, or null if no prior node exists (that is, if anEntry
	// is or belongs at the beginning of the list).
	protected Node getNodeBefore(T anEntry)
	{
		Node currentNode = getFirstNode();
		Node nodeBefore = null;

		while ( (currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0) )
		{
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		} // end while

		return nodeBefore;
	} // end getNodeBefore
     
//======== Methods also in ADT list:

	public T remove(int givenPosition)
	{
      T result = null;                            // Return value

      if ((givenPosition >= 1) && (givenPosition <= getLength()))
      {
         assert !isEmpty();

         if (givenPosition == 1)                  // Case 1: remove first entry
            result = removeFirstNode();
         else                                     // Case 2: givenPosition > 1
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            result = removeAfterNode(nodeBefore); // Save entry to be removed
         } // end if
      } // end if

      return result;                              // Return removed entry or
                                                  // null if operation fails
	} // end remove

   public T getEntry(int givenPosition)
   {
      T result = null;  // Result to return

      if ((givenPosition >= 1) && (givenPosition <= getLength()))
      {
         assert !isEmpty();
         result = getNodeAt(givenPosition).getData();
      } // end if

      return result;
   } // end getEntry
   
   // Must override toArray, because in LinkedChainBase it allocates an array
   // of objects that cannot be cast to Comparable objects.
   // Note that we must use the protected methods in LinkedChainBase
   // to access the nodes in the chain.
   public T[] toArray()
   {
      int numberOfEntries = getLength();
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Comparable[numberOfEntries];

      int index = 0;
      Node currentNode = getFirstNode();
      while ((index < numberOfEntries) && (currentNode != null))
      { 
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++; 
      } // end while

      return result;
   } // end toArray
} // end LinkedSortedList
