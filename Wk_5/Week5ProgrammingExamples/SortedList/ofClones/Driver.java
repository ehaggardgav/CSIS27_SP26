/** 
   A driver that demonstrates the class LinkedSortedListOfClones.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class Driver
{
	public static void main(String[] args) 
	{
		// Create names for a list
		ComparableCopyableName brent = new ComparableCopyableName("Brent", "Barka");
		ComparableCopyableName donna = new ComparableCopyableName("Donna", "Dalton");
		ComparableCopyableName jerry = new ComparableCopyableName("Jerry", "Java");
		ComparableCopyableName luke  = new ComparableCopyableName("Luke", "Lexter");
		ComparableCopyableName sue   = new ComparableCopyableName("Sue", "Smith");
		ComparableCopyableName tom   = new ComparableCopyableName("Tom", "Towers");
		
		// Create a list
   	SortedListOfClonesInterface<ComparableCopyableName> myList = new LinkedSortedListOfClones<>();
	
		myList.add(luke);
		myList.add(brent);
		myList.add(donna);
		myList.add(tom);
		myList.add(sue);
		myList.add(jerry);
		
		// Display the list
		System.out.println("List should contain\nBrent Barka, Donna Dalton, " + 
		                   "Jerry Java, Luke Lexter, Sue Smith, Tom Towers");
		System.out.println("\n\nList actually contains:");
		displayList(myList);
		System.out.println();

		// Change some names
		System.out.println("\nAfter changing Luke's last name to Jones, Luke is");
		luke.setLast("Jones");
		System.out.println(luke);
		System.out.println("The sorted list should be unchanged:");
		displayList(myList);

		System.out.println("\nAfter retrieving the 5th name (Sue Smith) from the list" +
		                   "\nand changing her last name to Doe, Sue is");
		ComparableCopyableName fifthName = myList.getEntry(5);
		fifthName.setLast("Doe");
		System.out.println(fifthName);
		System.out.println("The sorted list should be unchanged:");
		displayList(myList);
	}  // end main

   public static void displayList(SortedListOfClonesInterface<ComparableCopyableName> list)
	{
      int numberOfEntries = list.getLength();

      System.out.println("\nThe list contains " + numberOfEntries +
                         " entries, as follows:");
      for (int position = 1; position <= numberOfEntries; position++)
         System.out.println(list.getEntry(position) + 
                            " is entry " + position);
      System.out.println();
	} // end displayList
}  // end Driver

/*
 List should contain
 Brent Barka, Donna Dalton, Jerry Java, Luke Lexter, Sue Smith, Tom Towers
 
 
 List actually contains:
 
 The list contains 6 entries, as follows:
 Brent Barka is entry 1
 Donna Dalton is entry 2
 Jerry Java is entry 3
 Luke Lexter is entry 4
 Sue Smith is entry 5
 Tom Towers is entry 6
 
 
 
 After changing Luke's last name to Jones, Luke is
 Luke Jones
 The sorted list should be unchanged:
 
 The list contains 6 entries, as follows:
 Brent Barka is entry 1
 Donna Dalton is entry 2
 Jerry Java is entry 3
 Luke Lexter is entry 4
 Sue Smith is entry 5
 Tom Towers is entry 6
 
 
 After retrieving the 5th name (Sue Smith) from the list
 and changing her last name to Doe, Sue is
 Sue Doe
 The sorted list should be unchanged:
 
 The list contains 6 entries, as follows:
 Brent Barka is entry 1
 Donna Dalton is entry 2
 Jerry Java is entry 3
 Luke Lexter is entry 4
 Sue Smith is entry 5
 Tom Towers is entry 6
*/
