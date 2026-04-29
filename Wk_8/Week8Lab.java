public class Week8Lab {
    public static void main(String[] args) {

    }

   public int hashCode() {
        int h = 0;
        for (int i = 0; i < first.length(); i++) {
            h = h + first.charAt(i);
        }
        return h;
   } 

   interface MyMap {
        void put (int key, String value);
        String get (int key);
        void printTable();
   }

   static class Entry {
        int key;
        String value;

        Entry(int key, String value)n {
            this.key = key;
            this.value = value;
        }
   }

   static class LinearProbingHashMap implements MyMap {
        private Entry[] table;
        private int siz;
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
    }

    @Override
    public void put(int key, String value) {
        if ((double) count / size > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key);

        while (table [index] != null) {
            if (table[index].key == key) {
                table[index].value = value;
                return;
            }
            index = (index + 1) % size;
        }

        table[index] = new Entry(key, value);
        count ++;
    }

    
    

    


}
