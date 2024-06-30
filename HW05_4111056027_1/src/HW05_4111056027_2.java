class Vertex<T> {
    private T data;

    public Vertex(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

class Edge<T> {
    private Vertex<T> source;
    private Vertex<T> destination;
    private int weight;

    public Edge(Vertex<T> source, Vertex<T> destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}

class Graph<T> {
    private Object[][] adjacencyMap; // 用來存儲邊的資訊，每個元素是一個邊的數組，包含源頂點、目標頂點和權重

    public Graph(int numVertices) {
        adjacencyMap = new Object[numVertices][numVertices];
    }

    public void addEdge(Vertex<T> source, Vertex<T> destination, int weight) {
        // 確保源頂點和目標頂點的索引不超出範圍
        int sourceIndex = getIndex(source);
        int destinationIndex = getIndex(destination);

        // if (sourceIndex == -1 || destinationIndex == -1)
        // throw new IllegalArgumentException("Vertex not found in the graph");

        // 添加邊的資訊到 adjacencyMap
        adjacencyMap[sourceIndex][destinationIndex] = new Edge<>(source, destination, weight);
    }

    private int getIndex(Vertex<T> vertex) {
        // 在 adjacencyMap 中查找頂點，返回其索引
        for (int i = 0; i < adjacencyMap.length; i++) {
            if (adjacencyMap[i][0] instanceof Vertex
                    && ((Vertex<T>) adjacencyMap[i][0]).getData().equals(vertex.getData())) {
                return i;
            }
        }
        // return -1;
        // 找不到頂點，返回 代表新的Edge
        return adjacencyMap.length;
    }

    public void displayGraph() {
        // 顯示圖的邊的資訊
        for (Object[] edgeInfo : adjacencyMap) {
            for (Object obj : edgeInfo) {
                if (obj instanceof Edge) {
                    Edge<T> edge = (Edge<T>) obj;
                    System.out.println("Edge: " + edge.getSource().getData() + " -> " + edge.getDestination().getData()
                            + ", Weight: " + edge.getWeight());
                }
            }
        }
    }

    public Edge<T> getEdge(Vertex<T> source, Vertex<T> destination) {
        // 返回源頂點到目標頂點的邊
        int sourceIndex = getIndex(source);
        int destinationIndex = getIndex(destination);

        if (sourceIndex == -1 || destinationIndex == -1)
            // throw new IllegalArgumentException("Vertex not found in the graph");
            return null;

        return (Edge<T>) adjacencyMap[sourceIndex][destinationIndex];
    }
}

public class HW05_4111056027_2 extends WordChain {

    public static void main(String[] args) {
        String[] A = { "國立", "國立 中興 南區 國立 中興 大學 大學 大學 國立 學店" };
        // output = "國立 中興 南區 國立"
        System.out.println(new HW05_4111056027_2().sequenceProbability(A));
    }

    // depth2, the idea is to list all the words after A[0] and count the frequency
    // of each word
    // depth3, count every word's frequency after the words in depth2
    // depth4, count every word's frequency after the words in depth3
    // after all, we use the frequency as weight, and than we Kruskal or Prim to
    // find the maximum weight path
    @Override
    public String sequenceProbability(String[] A) {

        Graph<String> graph = new Graph<>(A[1].split(" ").length);
        String[] word = A[1].split(" ");
        for (int i = 0; i < word.length - 1; i++) {
            System.out.println(word[i]);
            if (word[i].equals(A[0])) {
                Vertex<String> source = new Vertex<>(word[i]);
                Vertex<String> destination = new Vertex<>(word[i + 1]);
                // if Edge is already exist, add 1 to the weight
                if (graph.getEdge(source, destination) != null) {
                    Edge<String> edge = graph.getEdge(source, destination);
                    graph.addEdge(source, destination, edge.getWeight() + 1);
                } else {
                    graph.addEdge(source, destination, 1);
                }
            }
        }

        graph.displayGraph();
        return "null";
    }
}
