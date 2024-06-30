public class HW08_4111056027_1 extends LLK {
    public static void main(String[] args) {
        int[][] array = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 } };
        System.out.println(new HW08_4111056027_1().checkLLK(array)); // Expected output: true

        int[][] array2 = { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 0, 1 } };
        System.out.println(new HW08_4111056027_1().checkLLK(array2)); // Expected output: false
    }

    @Override
    public boolean checkLLK(int[][] array) {
        int n = array.length;

        for (int i = 0; i < n; i++) {
            SimpleHashMap slopeCount = new SimpleHashMap();

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double slope = (array[j][0] == array[i][0]) ? Double.MAX_VALUE
                            : (double) (array[j][1] - array[i][1]) / (array[j][0] - array[i][0]);

                    int count = slopeCount.getOrDefault(slope, 0);
                    slopeCount.put(slope, count + 1);

                    if (count + 1 >= 2) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

class SimpleHashMap {
    private static final int SIZE = 1024;
    private Entry[] table;

    public SimpleHashMap() {
        table = new Entry[SIZE];
    }

    public int getOrDefault(double key, int defaultValue) {
        int hash = hash(key);
        for (Entry e = table[hash]; e != null; e = e.next) {
            if (Double.compare(e.key, key) == 0) {
                return e.value;
            }
        }
        return defaultValue;
    }

    public void put(double key, int value) {
        int hash = hash(key);
        for (Entry e = table[hash]; e != null; e = e.next) {
            if (Double.compare(e.key, key) == 0) {
                e.value = value;
                return;
            }
        }
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[hash];
        table[hash] = newEntry;
    }

    private int hash(double key) {
        long bits = Double.doubleToLongBits(key);
        return (int) (bits ^ (bits >>> 32)) & (SIZE - 1);
    }

    private static class Entry {
        double key;
        int value;
        Entry next;

        Entry(double key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
