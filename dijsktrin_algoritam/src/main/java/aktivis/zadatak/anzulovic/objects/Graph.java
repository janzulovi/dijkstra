package aktivis.zadatak.anzulovic.objects;

import java.util.List;

public class Graph {
    private static Graph instance = null;

    private List<Vertex> vertexes;
    private List<Edge> edges;

    protected Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public static Graph getInstance(List<Vertex> vertexes, List<Edge> edges){
        if(instance == null)
            instance = new Graph(vertexes, edges);
        return instance;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
