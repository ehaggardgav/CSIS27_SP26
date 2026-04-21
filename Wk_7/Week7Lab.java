import java.util.Scanner;
import java.util.Stack;

public class Week7Lab {
    
    static class BinaryNode<T> {
    private BinaryNode<T> leftChild;  // Reference to left child
    private BinaryNode<T> rightChild; // Reference to right child

    public BinaryNode (T data) {
        this.data = data;
        leftChild = null;
        rightChild = null;
    }

    public T getData() {
        return data;
    }

    public BinaryNode<T> getLeftChild() {
        return leftChild;
    }

    public BinaryNode<T> getrightChild() {
        return rightChild;
    }

    public void setLeftChild (BinaryNode<T> left) {
        leftChild = left;
    }

    public void setrightChild (BinaryNode<T> right) {
        rightChild = right;
    }
    }

    static class BinarySearchTree<T extends Comparable<? super T>> {
    
        private BinaryNode<T> root;

        public void add (T newEntry) {
            if (root == null) {
                root = new BinaryNode<>(newEntry);
            } else {
                addEntry(root, newEntry);
            }
        }

        private void addEntry(BinaryNode<T> currentNode, T newEntry) {
            boolean done = false;

            while (!done) {
                int comparison = newEntry.compareTo(currentNode.getData());

                if (comparison < 0) {
                    if (currentNode.getLeftChild() !=null) {
                        currentNode = currentNode.getLeftChild();
                    } else {
                        currentNode.setLeftChild(new BinaryNode<>(newEntry));
                        done = true;
                    }
                } else {
                    if (currentNode.getrightChild() !=null) {
                        currentNode = currentNode.getrightChild();
                    } else {
                        currentNode.setrightChild(new BinaryNode<>(newEntry));
                        done = true;
                    }
                }
            }
        }

        public void preorderIterative() {
            if (root == null) return;

            Stack<BinaryNode<T>> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                BinaryNode<T> current = stack.pop();
                System.out.print(current.getData() + " ");

                if (current.getRightChild() != null)
                    stack.push(current.getRightChild());

                if (current.getLeftChild() != null)
                    stack.push(current.getLeftChild());
            }

            System.out.println();
        }

        public void postorderIterative() {
            if (root == null) return;

            Stack<BinaryNode<T>> stack1 = new Stack<>();
            Stack<BinaryNode<T>> stack2 = new Stack<>();

            stack1.push(root);

            while (!stack1.isEmpty()) {
                BinaryNode<T> current = stack1.pop();
                stack2.push(current);

                if (current.getLeftChild() != null)
                    stack1.push(current.getLeftChild());

                if (current.getRightChild() != null)
                    stack1.push(current.getRightChild());
            }

            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().getData() + " ");
            }

            System.out.println();
        }
    }

    }

    

    

    }
