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


}
