import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<VertexType> extends Search<VertexType>{
    public BreadthFirstSearch(MyGraph<VertexType> graph, VertexType source) {
        super(source);

        bfs(graph, source);
    }

    private void bfs(MyGraph<VertexType> graph, VertexType current) {
        marked.add(current);


        Queue<VertexType> queue = new LinkedList<>();
        queue.add(current); //[0]

        while (!queue.isEmpty()) {
            VertexType v = queue.remove(); // []

            for (VertexType vertex : graph.adjacencyList(v)) {
                if (!marked.contains(vertex)) {
                    marked.add(vertex);
                    edgeTo.put(vertex, v); // {[1,0] [2,0] [3,0] [4 0] [5 1] [6 1] [7 2]}
                    queue.add(vertex); // [1,2,3,4]
                }
            }
        }
    }
}

