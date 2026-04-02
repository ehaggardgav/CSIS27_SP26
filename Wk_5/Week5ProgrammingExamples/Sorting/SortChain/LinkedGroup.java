/**
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class LinkedGroup<T extends Comparable<? super T>>
{
   private Node firstNode;
   int length; // Number of objects in the group
   
   public LinkedGroup()
   {
      initializeDataFields();
   } // end default constructor
   
   public boolean isEmpty()
   {
      boolean result;
      
      if (getLength() == 0)
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
   
   public void clear()
   {
      initializeDataFields();
   } // end clear
   
   public void add(T newEntry)          // OutOfMemoryError possible
   {
      // Add new entry to beginning of chain:
      // Create a new node that references the current first node of the chain
      Node newNode = new Node(newEntry, firstNode);
      firstNode = newNode; // Make chain begin at new node
      
      length++;
   } // end add

   public int getLength()
   {
      return length;
   } // end getLength
   
   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Comparable[length]; // Warning: unchecked cast
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < length) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
   
   public void sort()
   {
      insertionSort(firstNode);
   }  // end sort
   
   private void insertionSort(Node firstNode)
   {
      // If fewer than two items are in the list, there is nothing to do
      if (length > 1)
      {
         // Assertion: firstNode != null
         
         // Break chain into 2 pieces: sorted and unsorted
         Node unsortedPart = firstNode.getNextNode();
         // Assertion: unsortedPart != null
         firstNode.setNextNode(null);
         
         while (unsortedPart != null)
         {
            Node nodeToInsert = unsortedPart;
            unsortedPart = unsortedPart.getNextNode();
            insertInOrder(nodeToInsert);
         } // end while
      } // end if
   } // end insertionSort
   
   private void insertInOrder(Node nodeToInsert)
   {
      T item = nodeToInsert.getData();
      Node currentNode = firstNode;
      Node previousNode = null;

      // Locate insertion point
      while ( (currentNode != null) && 
              (item.compareTo(currentNode.getData()) > 0) )
      {
         previousNode = currentNode;
         currentNode = currentNode.getNextNode();
      } // end while

      // Make the insertion
      if (previousNode != null)
      {  // Insert between previousNode and currentNode
         previousNode.setNextNode(nodeToInsert);
         nodeToInsert.setNextNode(currentNode);
      }
      else // Insert at beginning
      {
         nodeToInsert.setNextNode(firstNode);
         firstNode = nodeToInsert;
      } // end if
   } // end insertInOrder
   
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
      firstNode = null;
      length = 0;
   } // end initializeDataFields
   
   // Returns a reference to the node at a given position.
   // Precondition: The chain is not empty;
   //               1 <= givenPosition <= length.
   private Node getNodeAt(int givenPosition)
   {
      // Assertion: (firstNode != null) &&
      //            (1 <= givenPosition) && (givenPosition <= length)
      Node currentNode = firstNode;
      
      // Traverse the chain to locate the desired node
      // (skipped if givenPosition is 1)
      for (int counter = 1; counter < givenPosition; counter++)
         currentNode = currentNode.getNextNode();
      // Assertion: currentNode != null
      return currentNode;
   } // end getNodeAt

   private class Node
   {
      private T data;    // Entry in bag
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
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
} // end LinkedChainList

