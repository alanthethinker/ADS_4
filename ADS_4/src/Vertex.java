import java.util.*;

public class Vertex<T> {
    private T data;
    private Map<Vertex<T>, Double> adjacentVertices;

    public Vertex(T data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<T> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public int size() {
        return adjacentVertices.size();
    }

    public boolean containsAdjacentVertex(Vertex<T> destination) {
        return adjacentVertices.containsKey(destination);
    }

    public Map<Vertex<T>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Vertex<?> vertex = (Vertex<?>) obj;
        return Objects.equals(data, vertex.data) && size() == vertex.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, size());
    }
}
