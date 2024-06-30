import java.util.ArrayList;

public class HW07_4111056027_2 extends LSD {
    public static void main(String[] args) throws Exception {
        HW07_4111056027_2 hw = new HW07_4111056027_2();
        // [[0, 1], [0, 2], [0, 4], [1, 3], [1, 4], [2, 5], [6, 7]]
        int[][] array = { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 6, 7 } };
        System.out.println(hw.distance(array));
    }

    public class graph {
        private int V = 10000;
        private ArrayList<Integer> adj[];

        graph() {
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        // print out the graph
        void printGraph() {
            for (int i = 0; i < 9633; i++) {
                System.out.println("size: " + adj[i].size());
                System.out.println("Adjacency list of vertex " + i);
                System.out.print("head");
                for (Integer n : adj[i]) {
                    System.out.print(" -> " + n);
                }
                System.out.println("\n");
            }
        }

        int bfs(int s) {
            boolean visited[] = new boolean[V];
            ArrayList<Integer> queue = new ArrayList();
            visited[s] = true;
            queue.add(s);

            int maxDis = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int u = queue.remove(0);
                    for (int neighbor : adj[u]) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
                maxDis++;
            }
            return maxDis;
        }

    }

    // Input: [[0, 1], [0, 2], [0, 4], [1, 3], [1, 4], [2, 5], [6, 7]]
    // Output: 4
    @Override // implement the longest shortest distance
    public int distance(int[][] array) {
        int n = array.length;
        graph g = new graph();
        for (int i = 0; i < n; i++) {
            g.addEdge(array[i][0], array[i][1]);
            g.addEdge(array[i][1], array[i][0]);
        }
        int result = 0;
        for (int i = 0; i < g.V; i++) {
            if (g.adj[i].size() == 0)
                continue;

            int step = g.bfs(i);
            if (step > result) {
                result = step;
            }
        }

        return result - 1;
    }
}
