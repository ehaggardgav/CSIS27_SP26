
/**
   A class of methods for searching a linked chain of nodes.
   The objects in a sorted chain must be Comparable.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class ChainSearcher<T extends Comparable<? super T>>
{
   private Node firstNode;       // Head reference to first node
   private Node lastNode;        // Tail reference to last node
   private int  numberOfEntries; // Number of entries in chain
   
   public ChainSearcher()
   {
      initializeDataFields();
   } // end default constructor
   
   // Segment 19.18
   /** Searches an unsorted chain for anEntry iteratively. */
   public boolean inUnsortedChainIterative(T anEntry)
   {
      boolean found = false;
      Node currentNode = firstNode;
      while (!found && (currentNode != null))
      {
         if (anEntry.equals(currentNode.getData()))
            found = true;
         else
            currentNode = currentNode.getNextNode();
      } // end while
      return found;
   } // end inUnsortedChainIterative
   
   // Segment 19.20
   /** Searches an unsorted chain for anEntry by calling a recursive private method. */
   public boolean inUnsortedChainRecursive(T anEntry)
   {
      return search(firstNode, anEntry);
   } // end inUnsortedChainRecursive
   
   // Recursively searches a chain of nodes sequentially for desiredItem,
   // beginning with the node that currentNode references.
   private boolean search(Node currentNode, T desiredItem)
   {
      boolean found;
      if (currentNode == null)
         found = false;
      else if (desiredItem.equals(currentNode.getData()))
         found = true;
      else
         found = search(currentNode.getNextNode(), desiredItem);
      return found;
   } // end search
   
   // Segment 19.22.
   /** Searches a sorted chain for anEntry sequentially and iteratively. */
   public boolean inSortedChainIterative(T anEntry)
   {
      Node currentNode = firstNode;
      while ( (currentNode != null) &&
             (anEntry.compareTo(currentNode.getData()) > 0) )
      {
         currentNode = currentNode.getNextNode();
      } // end while
      
      return (currentNode != null) && anEntry.equals(currentNode.getData());
   } // end inSortedChainIterative

// ========================================================================================
   public final void clear()
   {
      initializeDataFields();
   } // end clear
   
   public int getLength()
   {
      return numberOfEntries;
   } // end getLength
   
   public boolean isEmpty()
   {
      boolean result;
      
      if (numberOfEntries == 0) // Or getLength() == 0
      {
         // Assertion: firstNode == null
         result = true;
      }
      else
      {
         // Assertion: firstNode != null
         result = false;
      } // end if
      
      return result;
   } // end isEmpty
   
   // Adds a new node containing newEntry to the end of a chain.
   public void addToEnd(T newEntry)
   {
      Node newNode = new Node(newEntry);
      
      if (isEmpty())
         firstNode = newNode;
      else
         lastNode.setNextNode(newNode);
      
      lastNode = newNode;
      numberOfEntries++;
   }  // end addToEnd
   
   public void display()
   {
      System.out.print("The chain contains the following entries: ");
      Node currentNode = firstNode;
      for (int counter = 0; counter < numberOfEntries; counter++)
      {
         System.out.print(currentNode.getData() + " ");
         currentNode = currentNode.getNextNode();
      } // end for
      
      System.out.println();
   }  // end display
   
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
      firstNode = null;
      lastNode = null;
      numberOfEntries = 0;
   } // end initializeDataFields
   
   private class Node
   {
      private T data;     // Entry in list
      private Node next;  // Link to next node
      
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor
      
      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
   } // end Node
} // end ChainSearcher

