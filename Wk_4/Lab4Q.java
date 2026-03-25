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

    private void checkEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    @Override
    public void enqueue (T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        
        size++;
    }

    @Override
    public T dequeue() {
        checkEmpty();

        T value = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        
        size--;
        return value;
    }

    @Override
    public T peek() {
        checkEmpty();
        return head.data;
    }

    
    }
    
}
