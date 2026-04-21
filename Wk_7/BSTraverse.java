import java.util.Scanner;
import java.util.Stack;

public class BSTraverse {
    
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
}
