import java.util.*;

public class WeightedGraph<VertexType> {
    private final boolean undirected;
    private final Map<VertexType, Vertex<VertexType>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(VertexType v) {
        if (hasVertex(v))
            return;

        map.put(v, new Vertex<VertexType>(v));
    }

    public void addEdge(VertexType source, VertexType dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops

        map.get(source).addAdjacentVertex(map.get(dest), weight);

        if (undirected)
        {
            map.get(dest).addAdjacentVertex(map.get(source), weight);
        }
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (VertexType v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(VertexType v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(VertexType source, VertexType dest) {
        if (!hasVertex(source)) return false;

        return map.get(source).containsAdjacentVertex(map.get(source));
    }

    public List<VertexType> adjacencyList(VertexType v) {
        if (!hasVertex(v)) return null;

        List<VertexType> vertices = new LinkedList<>();

        Map<Vertex<VertexType>, Double> map1 = map.get(v).getAdjacentVertices();

        for (Map.Entry<Vertex<VertexType>, Double> entry : map1.entrySet())
        {
            vertices.add(entry.getKey().getData());
        }

        return vertices;
    }

    public double getEdgeWeight(VertexType source, VertexType dest)
    {
        if (!hasVertex(source)) return 0;

        if (!hasVertex(dest)) return 0;

        Map<Vertex<VertexType>, Double> map1 = map.get(source).getAdjacentVertices();

        for (Map.Entry<Vertex<VertexType>, Double> entry : map1.entrySet())
        {
            if(entry.getKey().getData().equals(dest))
                return entry.getValue();
        }

        return map.get(source).getAdjacentVertices().get(dest);
    }

    public Iterable<Vertex<VertexType>> getEdges(VertexType v) {
        if (!hasVertex(v)) return null;

        return map.get(v).getAdjacentVertices().keySet();
    }
}