import TreePackage.*;
import java.util.Scanner;

/**
   A guessing game, as described in Segments 24.26 and 24.27 of Chapter 24
   and that is the first solution to Project 2 of Chapter 25.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class GuessingGame
{
   
	public static void main(String[] args)
	{
      String response;
      DecisionTree<String> gameTree = new DecisionTree<>("Is it in North America?", "Brazil", "U.S.A.");
      do
      {
         System.out.println("Think of a country and I will guess it. ");
         gameTree = play(gameTree);
         System.out.print("Play again? ");
         response = getUserResponse();
      } while (response.toLowerCase().equals("yes"));
      System.out.println("Bye!");
	}  // end main
   
   public static String getUserResponse()
   {
      Scanner keyboard = new Scanner(System.in);
      String response = keyboard.nextLine();
      
      return response;
   } // end getUserResponse
   
   public static boolean isUserResponseYes()
   {
      String answer = getUserResponse();
      if (answer.toLowerCase().equals("yes"))
         return true;
      else
         return false;
   } // end isUserResponseYes
   
   public static DecisionTree<String> play(DecisionTree<String> gameTree)
   {
      gameTree.resetCurrentNode(); // Initialize current node to root
      while (!gameTree.isAnswer())
      {
         // Ask current question
         System.out.println(gameTree.getCurrentData());
         if (isUserResponseYes())
            gameTree.advanceToYes();
         else
            gameTree.advanceToNo();
      } // end while
      assert gameTree.isAnswer(); // Assertion: Leaf is reached
      
      // Make guess
      System.out.println("My guess is " + gameTree.getCurrentData() +
                         ". Am I right?");
      if (isUserResponseYes())
         System.out.println("I win.");
      else
         learn(gameTree);
      
      return gameTree;
   } // end play
   
   // < Implementation is the solution to Project 2 in Chapter 25. >
   // Responds to the user when this program makes a wrong guess and
   // extends the decision tree so that this guess is not made again.
   public static void learn(DecisionTree<String> gameTree)
   {
      System.out.println("I give up; what are you thinking of? ");
      String correctAnswer = getUserResponse();
      String currentAnswer = gameTree.getCurrentData();
      
      System.out.println("Give me a question whose answer is yes for " +
                         correctAnswer + " but no for " + currentAnswer);
      String newQuestion = getUserResponse();
      
      gameTree.setCurrentData(newQuestion);
      gameTree.setResponses(currentAnswer, correctAnswer);
   } // end learn
} // end GuessingGame
/*
 Think of a country and I will guess it.
 Is it in North America?
 yes
 My guess is U.S.A.. Am I right?
 yes
 I win.
 Play again? yes
 Think of a country and I will guess it.
 Is it in North America?
 no
 My guess is Brazil. Am I right?
 no
 I give up; what are you thinking of?
 England
 Give me a question whose answer is yes for England but no for Brazil
 Is it in Europe?
 Play again? yes
 Think of a country and I will guess it.
 Is it in North America?
 no
 Is it in Europe?
 yes
 My guess is England. Am I right?
 yes
 I win.
 Play again? no
 Bye!
 */
