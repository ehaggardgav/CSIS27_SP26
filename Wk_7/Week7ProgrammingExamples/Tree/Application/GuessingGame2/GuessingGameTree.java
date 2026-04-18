import TreePackage.*;
/**
   A decision tree for a guessing game using yes or no questions.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class GuessingGameTree extends DecisionTree<String>
{
   public GuessingGameTree(String question, String noAnswer, String yesAnswer)
   {
      BinaryTreeInterface<String> leftTree = new BinaryTree<>(noAnswer);
      BinaryTreeInterface<String> rightTree = new BinaryTree<>(yesAnswer);
      setTree(question, leftTree, rightTree);
   } // end default constructor

   // Replaces the current node, which must be a leaf,
   // with a question and two answers.
   // Parameters:
   //     question  A question ending in '?'.
   //     noAnswer  The response when the answer to the question is 'no'.
   //     yesAnswer The response when the answer to the question is 'yes'.
   public void extendTree(String question, String noAnswer, String yesAnswer)
   {
      setCurrentData(question);
      setResponses(noAnswer, yesAnswer);
   } // end extendTree
} // end GuessingGameTree
