public class Lab4Q {


    interface QueueInterface<T> {
        void enqueue (T data);
        T dequeue ();
        T peek ();
        boolean isEmpty();
        int size();
    }

static class LinkedQueue<T> implements QueueInterface<T> {
    
    private static class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }    
    }    

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }
}

public void enqueue (T data) {
    Node<T> newNode = new Node<>(data);

    if (rear == null) {
        front = rear = newNode;
        return;
    }

    rear.next = newNode;
    rear = newNode;
}
    
}
