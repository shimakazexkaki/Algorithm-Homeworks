import java.util.*;

public class HW05_4111056027_3 extends WordChain {
    int vertices;
    HashMap<String, Integer> vertexMap;

    public static void main(String[] args) {
        String[] A = { "國立", "國立 中興 南區 國立 中興 大學 大學 大學 國立 學店" };
        // output = "國立 中興 南區 國立"
        System.out.println(new HW05_4111056027_3().sequenceProbability(A));

    }

    // graph methods
    public void addVertex(String vertex) {
        vertexMap.put(vertex, vertices);
    }

    public void removeVertex(String vertex) {
        vertexMap.remove(vertex);
    }

    @Override
    public String sequenceProbability(String[] A) {
        vertices = 0;
        vertexMap = new HashMap<>();
        String[] words = A[1].split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            // if this vertex is not in the map, add it
            if (!vertexMap.containsKey(words[i + 1])) {
                addVertex(words[i]);
                addEdge(A[0], words[i + 1]);
                vertices++;
            } else // if already exist, add 1 to the weight
                

        }
        return null;
    }
}
