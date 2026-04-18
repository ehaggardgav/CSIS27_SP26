/**
 * THis is a linked list implementation to perform
 * insertion sort, that includes step-by-step output after each
 * insertion to visualize how the linked structure changes during sorting.
 */

import java.util.Arrays;

public class LinkedInsertionSort<T extends Comparable<? super T>> {

    private Node firstNode;
    private int length;

    public LinkedInsertionSort() {
        firstNode = null;
        length = 0;
    }

    public void add(T newEntry) {
        Node newNode = new Node(newEntry, firstNode);
        firstNode = newNode;
        length++;
    }

    public void sort() {
        insertionSort();
    }

    private void insertionSort() {
        if (length > 1) {

            Node unsortedPart = firstNode.getNextNode();
            firstNode.setNextNode(null);

            printState();

            while (unsortedPart != null) {
                Node nodeToInsert = unsortedPart;
                unsortedPart = unsortedPart.getNextNode();

                insertInOrder(nodeToInsert);

                printState();
            }
        }
    }

    private void insertInOrder(Node nodeToInsert) {
        T item = nodeToInsert.getData();
        Node currentNode = firstNode;
        Node previousNode = null;

        while (currentNode != null &&
               item.compareTo(currentNode.getData()) > 0) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        if (previousNode != null) {
            previousNode.setNextNode(nodeToInsert);
            nodeToInsert.setNextNode(currentNode);
        } else {
            nodeToInsert.setNextNode(firstNode);
            firstNode = nodeToInsert;
        }
    }

    public T[] toArray() {
    int count = 0;
    Node current = firstNode;

    while (current != null) {
        count++;
        current = current.getNextNode();
    }

    @SuppressWarnings("unchecked")
    T[] result = (T[]) new Comparable[count];

    current = firstNode;
    int index = 0;

    while (current != null) {
        result[index++] = current.getData();
        current = current.getNextNode();
    }

    return result;
    }

    private void printState() {
        System.out.println(Arrays.toString(toArray()));
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data, Node nextNode) {
            this.data = data;
            this.next = nextNode;
        }

        private T getData() { return data; }
        private Node getNextNode() { return next; }
        private void setNextNode(Node nextNode) { next = nextNode; }
    }
}
