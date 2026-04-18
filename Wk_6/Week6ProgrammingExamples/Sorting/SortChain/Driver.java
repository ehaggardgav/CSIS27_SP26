/** 
   A driver that demonstrates the class LinkedGroup.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class Driver
{
	public static void main(String[] args) 
	{
		LinkedGroup<String> myGroup = new LinkedGroup<>();

		System.out.println("Adding A, E, C, D, B, F, G to the group:");
      myGroup.add("A");
      myGroup.add("E");
      myGroup.add("C");
      myGroup.add("D");
      myGroup.add("B");
      myGroup.add("F");
      myGroup.add("G");

		System.out.println("\nGroup should contain\nG F B D C E A");
		displayGroup(myGroup);
		
      System.out.println("\nSorting group:");
      myGroup.sort();
      System.out.println("\nGroup should contain\nA B C D E F G");
      displayGroup(myGroup);

      System.out.println("\n\nDone.");

   } // end main

   public static void displayGroup(LinkedGroup<String> group)
   {
      System.out.println("The group contains " + group.getLength() +
                         " string(s), as follows:");
      Object[] groupArray = group.toArray();
      for (int index = 0; index < groupArray.length; index++)
      {
         System.out.print(groupArray[index] + " ");
      } // end for
      
      System.out.println();
   } // end displayGroup
}  // end Driver
/*
 Adding A, E, C, D, B, F, G to the group:
 
 Group should contain
 G F B D C E A
 The group contains 7 string(s), as follows:
 G F B D C E A
 
 Sorting group:
 
 Group should contain
 A B C D E F G
 The group contains 7 string(s), as follows:
 A B C D E F G
 
 
 Done.


*/
