import TreePackage.SearchTreeInterface;
import TreePackage.BinarySearchTree;
import java.util.Iterator;
/**
 A class that implements the ADT dictionary by using a binary search tree.
 The dictionary is sorted and has distinct search keys.
 
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
*/
public class BstDictionary<K extends Comparable<? super K>, V>
implements DictionaryInterface<K, V>
{
   private SearchTreeInterface<Entry<K, V>> bst;
   
   public BstDictionary()
   {
      bst = new BinarySearchTree<>();
   } // end default constructor
   
   public V add(K key, V value)
   {
      Entry<K, V> newEntry = new Entry<>(key, value);
      Entry<K, V> returnedEntry = bst.add(newEntry);
      
      V result = null;
      if (returnedEntry != null)
         result = returnedEntry.getValue();
      
      return result;
   } // end add
   
   public V remove(K key)
   {
      Entry<K, V> findEntry = new Entry<>(key, null);
      Entry<K, V> returnedEntry = bst.remove(findEntry);
      
      V result = null;
      if (returnedEntry != null)
         result = returnedEntry.getValue();
      
      return result;
   } // end remove

   public V getValue(K key)
   {
      Entry<K, V> findEntry = new Entry<>(key, null);
      Entry<K, V> returnedEntry = bst.getEntry(findEntry);

      V result = null;
      if (returnedEntry != null)
         result = returnedEntry.getValue();
         
      return result;
   } // end getValue

   public boolean contains(K key)
   {
      return getValue(key) != null;
   } // end contains
   /* 
   // Alternate version  
   public boolean contains(K key)
   {
      Entry<K, V> findEntry = new Entry<>(key, null);

      return bst.contains(findEntry);
   } // end contains
   */
   public boolean isEmpty()
   {
      return bst.isEmpty();
   } // end isEmpty

   public int getSize()
   {
      return bst.getNumberOfNodes();
   } // end getSize

   public void clear()
   {
      bst.clear();
   } // end clear

   public Iterator<K> getKeyIterator()
   {
      return new KeyIterator();
   } // end getKeyIterator

   public Iterator<V> getValueIterator()
   {
      return new ValueIterator();
   } // end getValueIterator

   private class KeyIterator implements Iterator<K>
   {
      Iterator<Entry<K, V>> localIterator;
      
      public KeyIterator()
      {
         localIterator = bst.getInorderIterator();
      } // end default constructor
      
      public boolean hasNext()
      {
         return localIterator.hasNext();
      } // end hasNext
      
      public K next()
      {
         Entry<K, V> nextEntry = localIterator.next();
         return nextEntry.getKey();
      } // end next
      
      public void remove()
      {
         throw new UnsupportedOperationException();
      } // end remove
   } // end KeyIterator

   private class ValueIterator implements Iterator<V>
   {
      Iterator<Entry<K, V>> localIterator;

      public ValueIterator()
      {
         localIterator = bst.getInorderIterator();
      } // end default constructor

      public boolean hasNext() 
      {
         return localIterator.hasNext();
      } // end hasNext

      public V next()
      {
         Entry<K, V> nextEntry = localIterator.next();
         return nextEntry.getValue();
      } // end next

      public void remove()
      {
         throw new UnsupportedOperationException();
      } // end remove
   } // end ValueIterator

   private class Entry<S extends Comparable<? super S>, T>
           implements Comparable<Entry<S, T>>
   {
      private S key;
      private T value;
      
      private Entry(S searchKey, T dataValue)
      {
         key = searchKey;
         value = dataValue;
      } // end constructor
      
      public int compareTo(Entry<S, T> other)
      {
         return key.compareTo(other.key);
      } // end compareTo

      public boolean equals(Object other)
      {
         boolean result;

         if ((other == null) || (getClass() != other.getClass()))
            result = false;
         else
         {
            // The cast is safe because the argument other is of class Entry at this point
            @SuppressWarnings("unchecked")
            Entry<S, T> otherEntry = (Entry<S, T>)other;
            result = key.equals(otherEntry.key);
         } // end if

         return result;
      } // equals

      private S getKey()
      {
         return key;
      } // end getKey

      private T getValue()
      {
         return value;
      } // end getValue

      private void setValue(T newValue)
      {
         value = newValue;
      } // end setValue
               
      public String toString()
      {
         return key.toString() + " " + value.toString();
      } // end toString 
   } // end Entry
} //  end BstDictionary
