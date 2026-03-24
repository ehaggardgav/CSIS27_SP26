/** 
   A driver that demonstrates the clonable aspects of the class CloneableAList.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class Driver2
{
	public static void main(String[] args) 
	{
		testList();
		System.out.println("\n\nDone.");
	}  // end main

	public static void testList()
	{
		// Create things for a list
      Thing<String> t15 = new Thing<String>("15");
      Thing<String> t25 = new Thing<String>("25");
      Thing<String> t35 = new Thing<String>("35");
      Thing<String> t45 = new Thing<String>("45");
      Thing<String> t55 = new Thing<String>("55");
      Thing<String> t65 = new Thing<String>("65");
      Thing<String> t75 = new Thing<String>("75");
      Thing<String> t85 = new Thing<String>("85");
      Thing<String> t95 = new Thing<String>("95");
      
      // Create a list of the things
      System.out.println("Creating a list that should contain 15 25 35 45 55 65 75 85 95.");
      CloneableListInterface<Thing<String>> myList = new CloneableAList<>();
      myList.add(t15);
      myList.add(t25);
      myList.add(t35);
      myList.add(t45);
      myList.add(t55);
      myList.add(t65);
      myList.add(t75);
      myList.add(t85);
      myList.add(t95);

      displayList(myList);
      System.out.println();
      
      // Change some data
      System.out.println("The list contains clones of the original entries.");
      System.out.println("After changing t65's value to 99, the list should be unchanged:");
      t65.setData("99");
      System.out.println("t65's value is " + t65);
      displayList(myList);
      
      System.out.println("\nAfter retrieving the 4th item (a clone of t45) from the list" +
                         "\nand changing its value to 88, ");
      Thing<String> item4 = myList.getEntry(4);
      item4.setData("88");
      System.out.println("The value of the retrieved item is " + item4);
      System.out.println("The list should be unchanged:");
      displayList(myList);

      System.out.println("===============================================");
      System.out.println("CLONE the list=================================");
      @SuppressWarnings("unchecked")
      CloneableListInterface<Thing<String>> myListClone = (CloneableAList<Thing<String>>)myList.clone();
      System.out.print("Display the clone:    ");
      displayList(myListClone);
      System.out.print("Display the original: ");
      displayList(myList);

      System.out.println("===============================================");
      System.out.println("Change the last item in the original list:=====");
      myList.replace(myList.getLength(), new Thing<String>("XX"));
      System.out.print("Display the clone:    ");
      displayList(myListClone);
      System.out.print("Display the original: ");
      displayList(myList);

      System.out.println("===============================================");
      System.out.println("Change the last item in the clone:=============");
      myListClone.replace(myListClone.getLength(), new Thing<String>("ZZ"));
      System.out.print("Display the clone:    ");
      displayList(myListClone);
      System.out.print("Display the original: ");
      displayList(myList);
   } // end testList

   public static void displayList(CloneableListInterface<Thing<String>> list)
   {
      System.out.println("The list contains " + list.getLength() +
                         " thing(s), as follows:");
      Object[] listArray = list.toArray();
      for (int index = 0; index < listArray.length; index++)
      {
         System.out.print(listArray[index] + " ");
      } // end for
      
      System.out.println();
   } // end displayList
} // end Driver2
/*
 Creating a list that should contain 15 25 35 45 55 65 75 85 95.
 The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 95
 
 The list contains clones of the original entries.
 After changing t65's value to 99, the list should be unchanged:
 t65's value is 99
 The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 95
 
 After retrieving the 4th item (a clone of t45) from the list
 and changing its value to 88,
 The value of the retrieved item is 88
 The list should be unchanged:
 The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 95
 ===============================================
 CLONE the list=================================
 Display the clone:    The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 95
 Display the original: The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 95
 ===============================================
 Change the last item in the original list:=====
 Display the clone:    The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 95
 Display the original: The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 XX
 ===============================================
 Change the last item in the clone:=============
 Display the clone:    The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 ZZ
 Display the original: The list contains 9 thing(s), as follows:
 15 25 35 45 55 65 75 85 XX
 
 
 Done.
*/
