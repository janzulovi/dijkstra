package aktivis.zadatak.anzulovic.service;

import aktivis.zadatak.anzulovic.objects.Edge;
import aktivis.zadatak.anzulovic.objects.Graph;
import aktivis.zadatak.anzulovic.objects.Vertex;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstraService{

    private static DijkstraService instance = null;

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;


    private DijkstraService(Graph graph) {

        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public static DijkstraService getInstance(Graph graph){
        if(instance == null)
            instance = new DijkstraService(graph);
        return instance;
    }


    public String getSolution(String incidenceMatrix, String edgesWeights, String firstVertex, String lastVertex) {
        //repository.saveData(incidenceMatrix, edgesWeights, firstVertex, lastVertex);
        return "solution";
    }




    public void execute(Vertex source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getVertexsource().equals(node.getName())
                    && edge.getVertexdestination().equals(target.getName())) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        Vertex neighbourVertex = new Vertex();
            for (Edge edge : edges) {
            if (edge.getVertexsource().equals(node.getName())
                    && !isSettled(edge.getVertexdestination())) {
                neighbourVertex.setName(edge.getVertexdestination());
                neighbors.add(neighbourVertex);
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(String vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public String getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path.toString();
    }


}
