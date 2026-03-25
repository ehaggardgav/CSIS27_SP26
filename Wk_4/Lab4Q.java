public class Lab4Q {

    /** 
     * An interface for a generic queue ADT.
     * interface "QueueInterface" defines the operations that a queue must support.
     */
    interface QueueInterface<T> {
        void enqueue(T data); //adds data to end of queue
        T dequeue(); //removes and returns entry at front of the queue
        T peek(); //returns, but does not remove entry at front of queue
        boolean isEmpty(); //checks if queue is empty
        int size(); //returns number of entries in queue
    }

    /**
     * class "LinkedQueue" is the implementation of the QueueInterface.
     * Uses nodes with references to the head and tail.
     */
    static class LinkedQueue<T> implements QueueInterface<T> {
    /**
     * class Node<T> is encapsulated in LinkedQueue
     */   
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

        @Override  //used to check for errors before code compiles
        public void enqueue(T data) {
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

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }
        
    }

    /**
     * method to test queue operations.
     */
    public static void main(String[] args) {
        QueueInterface<Integer> q = new LinkedQueue<>();

        q.enqueue(5);
        q.enqueue(10);
        q.enqueue(15);
        q.enqueue(20);

        System.out.println(q.dequeue());
        System.out.println(q.peek());
        System.out.println(q.size());
    }
    
}
