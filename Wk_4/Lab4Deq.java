public class Lab4Deq {
    
    /**
     * An interface for a generic deque ADT.
     * Defines operations for adding and removing entries
     * from both the front and the end.
     */
    interface DequeInterface<T> {
        void addToHead(T data); //adds a new entry to the front of the deque.
        void addToTail(T data); //adds a new entry to the end of the deque.
        T removeHead(); //removes and returns the entry at the front of the deque.
        T removeTail(); //removes and returns the entry at the end of the deque.
        T getHead(); //returns, but does not remove, the entry at the front of the deque.
        T getTail(); //returns, but does not remove, the entry at the end of the deque.
        boolean isEmpty(); //checks if queue is empty
        int size(); //returns number of entries in queue
    }

    /**
     * class 'LinkedDeque' is an implementation of the DequeInterface.
     * uses a doubly linked chain of nodes.
     */
    static class LinkedDeque<T> implements DequeInterface<T> {
        /**
         * class Node<T> is encapsulated in LinkedDeque.
         */
        private static class Node<T> {
            private T data;
            private Node<T> next;
            private Node<T> prev;

            private Node(T data) {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }

        private Node<T> head; 
        private Node<T> tail; 
        private int size;     

    
        public LinkedDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        private void checkEmpty() {
            if (isEmpty()) {
                throw new IllegalStateException("Deque is empty");
            }
        }

        @Override  //used to check for errors before code compiles
        public void addToHead(T data) {
            Node<T> newNode = new Node<>(data);

            if (isEmpty()) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

            size++;
        }

        @Override
        public void addToTail(T data) {
            Node<T> newNode = new Node<>(data);

            if (isEmpty()) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }

            size++;
        }

        @Override
        public T removeHead() {
            checkEmpty();

            T value = head.data;
            head = head.next;

            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }

            size--;
            return value;
        }

        @Override
        public T removeTail() {
            checkEmpty();

            T value = tail.data;
            tail = tail.prev;

            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }

            size--;
            return value;
        }

        @Override
        public T getHead() {
            checkEmpty();
            return head.data;
        }

        @Override
        public T getTail() {
            checkEmpty();
            return tail.data;
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
     * method to test deque operations.
     */
    public static void main(String[] args) {
        DequeInterface<Integer> d = new LinkedDeque<>();

        d.addToHead(10);
        d.addToHead(5);
        d.addToTail(20);
        d.addToTail(25);

        System.out.println(d.getHead()); 
        System.out.println(d.getTail());  

        System.out.println(d.removeHead()); 
        System.out.println(d.removeTail());  

        System.out.println(d.size());
    }
}