/** Makes an alphabetical list of names as students enter a room.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public class AlphaList
{
   public static void main(String[] args)
   {
      ListInterface<String> alphaList = new AList<>();
      
      alphaList.add(1, "Amy");    // Amy
      alphaList.add(2, "Elias");  // Amy Elias
      alphaList.add(2, "Bob");    // Amy Bob Elias
      alphaList.add(3, "Drew");   // Amy Bob Drew Elias
      alphaList.add(1, "Aaron");  // Aaron Amy Bob Drew Elias
      alphaList.add(4, "Carol");  // Aaron Amy Bob Carol Drew Elias
      displayList(alphaList);
   }  // end main
   
   public static void displayList(ListInterface<String> list)
   {
      int numberOfEntries = list.getLength();
      System.out.println("The list contains " + numberOfEntries +
                         " entries, as follows:");
      for (int position = 1; position <= numberOfEntries; position++)
         System.out.println(list.getEntry(position) +
                            " is entry " + position);
      System.out.println();
   } // end displayList
} // AlphaList
/*
The list contains 6 entries, as follows:
Aaron is entry 1
Amy is entry 2
Bob is entry 3
Carol is entry 4
Drew is entry 5
Elias is entry 6
*/
