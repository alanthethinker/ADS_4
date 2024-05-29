import java.util.*;

public class Search<VertexType> {
    protected Set<VertexType> marked;
    protected Map<VertexType, VertexType> edgeTo;
    protected final VertexType source;

    public Search(VertexType source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(VertexType v) {
        return marked.contains(v);
    }

    public Iterable<VertexType> pathTo(VertexType v) {
        if (!hasPathTo(v)) return null;

        LinkedList<VertexType> ls = new LinkedList<>();
        for (VertexType i = v; i != source; i = edgeTo.get(i)) {
            ls.push(i); // inverted adding
        }

        ls.push(source);

        return ls;
    }
}

