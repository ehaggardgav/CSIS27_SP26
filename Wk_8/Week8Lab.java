public class Week8Lab {

    public static void main(String[] args) {
        LinearProbingHashMap map = new LinearProbingHashMap();

        map.put(10, "A");
        map.put(16, "B");
        map.put(22, "C");
        map.put(28, "D");
        map.put(34, "E");

        map.printTable();

        System.out.println("\nGet key 16: " + map.get(16));
        System.out.println("Get key 99: " + map.get(99));
    }

    // -----------------------------
    // Interface
    // -----------------------------
    interface MyMap {
        void put(int key, String value);
        String get(int key);
        void printTable();
    }

    // -----------------------------
    // Entry class
    // -----------------------------
    static class Entry {
        int key;
        String value;

        Entry(int key, String value) {   // FIXED typo here
            this.key = key;
            this.value = value;
        }
    }

    // -----------------------------
    // HashMap implementation
    // -----------------------------
    static class LinearProbingHashMap implements MyMap {

        private Entry[] table;
        private int size;   // FIXED (was "siz")
        private int count;
        private static final double LOAD_FACTOR_THRESHOLD = 0.5;

        public LinearProbingHashMap() {
            size = 6;
            table = new Entry[size];
            count = 0;
        }

        private int hash(int key) {
            return Math.abs(key) % size;
        }

        @Override
        public void put(int key, String value) {
            if ((double) count / size > LOAD_FACTOR_THRESHOLD) {
                resize();
            }

            int index = hash(key);

            while (table[index] != null) {
                if (table[index].key == key) {
                    table[index].value = value;
                    return;
                }
                index = (index + 1) % size;
            }

            table[index] = new Entry(key, value);
            count++;
        }

        @Override
        public String get(int key) {
            int index = hash(key);
            int startIndex = index;

            while (table[index] != null) {
                if (table[index].key == key) {
                    return table[index].value;
                }

                index = (index + 1) % size;

                if (index == startIndex) {
                    break;
                }
            }

            return null;
        }

        private void resize() {
            System.out.println("Resizing from " + size + " to " + (size * 2));

            Entry[] oldTable = table;

            size = size * 2;
            table = new Entry[size];
            count = 0;

            for (Entry entry : oldTable) {
                if (entry != null) {
                    put(entry.key, entry.value);
                }
            }
        }

        @Override
        public void printTable() {
            System.out.println("\nHash Table:");

            for (int i = 0; i < size; i++) {
                if (table[i] == null) {
                    System.out.println(i + ": empty");
                } else {
                    System.out.println(i + ": (" + table[i].key + ", " + table[i].value + ")");
                }
            }
        }
    }
}