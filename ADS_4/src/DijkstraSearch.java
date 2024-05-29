import java.util.*;

public class DijkstraSearch<VertexType> extends Search<VertexType> {
    private final Set<VertexType> unsettledNodes;
    private final Map<VertexType, Double> distances;
    private final WeightedGraph<VertexType> graph;

    public DijkstraSearch(WeightedGraph<VertexType> graph, VertexType source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;

        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            VertexType currentNode = getVertexTypeWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (VertexType neighbor : graph.adjacencyList(currentNode)) {
                double newDistance = getShortestDistance(currentNode) + getDistance(currentNode, neighbor);

                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode); // inverted adding
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private double getDistance(VertexType node, VertexType target) {
        for (Vertex<VertexType> vertex2 : graph.getEdges(node)) {
            if (vertex2.getData().equals(target))
                return graph.getEdgeWeight(node, target);
        }

        throw new RuntimeException("Not found!");
    }

    private VertexType getVertexTypeWithMinimumWeight(Set<VertexType> vertices) {
        VertexType minimum = null;
        for (VertexType VertexType : vertices) {
            if (minimum == null) {
                minimum = VertexType;

                continue;
            }

            if (getShortestDistance(VertexType) < getShortestDistance(minimum))
                minimum = VertexType;
        }

        return minimum;
    }

    private double getShortestDistance(VertexType destination) {
        Double d = distances.get(destination);

        return (d == null ? Double.MAX_VALUE : d);
    }
}


