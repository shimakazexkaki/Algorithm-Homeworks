class KeyValuePair<K, V> {
    private K key;
    private V value;
    private KeyValuePair<K, V> next;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public KeyValuePair<K, V> getNext() {
        return next;
    }

    public void setNext(KeyValuePair<K, V> next) {
        this.next = next;
    }
}

class CustomMap<K, V> {
    private KeyValuePair<K, V>[] buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public CustomMap() {
        buckets = new KeyValuePair[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public void put(K key, V value) {
        int index = hash(key);
        KeyValuePair<K, V> entry = buckets[index];

        // If bucket is empty, add new key-value pair
        if (entry == null) {
            buckets[index] = new KeyValuePair<>(key, value);
            size++;
            return;
        }

        // If key already exists, update the value
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
            entry = entry.getNext();
        }

        // Key doesn't exist, add new key-value pair to the end of the bucket
        entry = buckets[index];
        KeyValuePair<K, V> newEntry = new KeyValuePair<>(key, value);
        while (entry.getNext() != null) {
            entry = entry.getNext();
        }
        entry.setNext(newEntry);
        size++;

        // Check if rehashing is needed
        if ((double) size / buckets.length > 0.75) {
            rehash();
        }
    }

    public V get(K key) {
        int index = hash(key);
        KeyValuePair<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        KeyValuePair<K, V> prev = null;
        KeyValuePair<K, V> current = buckets[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev == null) {
                    buckets[index] = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    private void rehash() {
        KeyValuePair<K, V>[] oldBuckets = buckets;
        buckets = new KeyValuePair[oldBuckets.length * 2];
        size = 0;
        for (KeyValuePair<K, V> entry : oldBuckets) {
            while (entry != null) {
                put(entry.getKey(), entry.getValue());
                entry = entry.getNext();
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

class Graph {
    private CustomMap<String, CustomMap<String, Double>> graph;

    public Graph() {
        graph = new CustomMap<>();
    }

    public void addVertex(String vertex) {
        if (!graph.get(vertex).isEmpty()) {
            return;
        }
        graph.put(vertex, new CustomMap<>());
    }

    public void addEdge(String start, String end, Double weight) {
        addVertex(start);
        addVertex(end);
        graph.get(start).put(end, weight);
        graph.get(end).put(start, weight);
    }

    public Double getEdge(String start, String end) {
        return graph.get(start).get(end);
    }

    public CustomMap<String, CustomMap<String, Double>> get() {
        return graph;
    }

}

public class HW05_4111056027_1 extends WordChain {
    public static void main(String[] args) {
        String[] A = { "國立", "國立 中興 南區 國立 中興 大學 大學 大學 國立 學店" };
        // output = "國立 中興 南區 國立"
        System.out.println(new HW05_4111056027_1().sequenceProbability(A));
    }

    // depth2, the idea is to list all the words after A[0] and count the frequency
    // of each word
    // depth3, count every word's frequency after the words in depth2
    // depth4, count every word's frequency after the words in depth3
    // after all, we use the frequency as weight, and than we Kruskal or Prim to
    // find the maximum weight path
    @Override
    public String sequenceProbability(String[] A) {
        Graph graph = new Graph();
        String[] word = A[1].split(" ");
        graph.addVertex(A[0]);

        for (String w : word) {
            graph.addVertex(w);
        }

        // if (graph.getEdge(word[i + 1], word[j + 1]) != null) {
        // graph.addEdge(word[i + 1], word[j + 1], graph.getEdge(word[i + 1], word[j +
        // 1]) + 1);
        // } else {
        // graph.addEdge(word[i + 1], word[j + 1], 1.0);

        for (int i = 0; i < word.length - 1; i++) {
            if (word[i].equals(A[0])) {
                graph.addEdge(A[0], word[i + 1],
                        graph.getEdge(A[0], word[i + 1]) == null ? 1.0 : graph.getEdge(A[0], word[i + 1]) + 1);
            }
        }

        return "";
    }
}
