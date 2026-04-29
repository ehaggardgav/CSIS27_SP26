import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
   A driver that demonstrates the class FrequencyCounter.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class Driver
{
   public static void main(String[] args) 
   {
      FrequencyCounter wordCounter = new FrequencyCounter();
      String fileName = "Data.txt"; // Or file name could be read

      try
      {
         Scanner data = new Scanner(new File(fileName));
         wordCounter.readFile(data);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found: " + e.getMessage());
      }

      System.out.println("Here is a list of the words that appear in the data file,\n" + 
                         "along with their frequencies of ocurrence:");
      wordCounter.display();

      // Test Question 8, Chapter 19
      System.out.println("\nHere are the same words organized by their frequencies of ocurrence:");
      wordCounter.display(1);
      wordCounter.display(2);
      wordCounter.display(3);
      wordCounter.display(4);
      wordCounter.display(5);
      wordCounter.display(6);

      System.out.println("Bye!");
   }  // end main	
}  // end Driver

/*
 Here is a list of the words that appear in the data file,
 along with their frequencies of ocurrence:
 are 1
 birthday 5
 happy 5
 how 1
 now 1
 old 1
 to 3
 you 4
 
 Here are the same words organized by their frequencies of ocurrence:
 Words that occur 1 times:
 are
 how
 now
 old
 Words that occur 2 times:
 (There are none.)
 Words that occur 3 times:
 to
 Words that occur 4 times:
 you
 Words that occur 5 times:
 birthday
 happy
 Words that occur 6 times:
 (There are none.)
 Bye!
 */
