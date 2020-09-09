package aktivis.zadatak.anzulovic.service;

import aktivis.zadatak.anzulovic.objects.CreatePathDTO;
import aktivis.zadatak.anzulovic.objects.Edge;
import aktivis.zadatak.anzulovic.objects.SolutionDTO;
import aktivis.zadatak.anzulovic.objects.Vertex;
import aktivis.zadatak.anzulovic.repository.DijkstraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstraService{

    private static DijkstraService instance = null;

    @Autowired
    private DijkstraRepository repository;

    private List<Vertex> nodes;
    private List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;


    private DijkstraService() {

    }

    public static DijkstraService getInstance(){
        if(instance == null)
            instance = new DijkstraService();
        return instance;
    }




    //izracunavanje puta

    public SolutionDTO createPath(CreatePathDTO createPathDTO) {

        repository.addData(createPathDTO.getIncidenceMatrix(), createPathDTO.getEdgeWeight(), createPathDTO.getFirstVertex(), createPathDTO.getLastVertex());

        List<Vertex> listOfVertexes = new ArrayList<>();
        List<Edge> listOfEdges = new ArrayList<>();
        List<Integer> integerMatrix = new ArrayList<>();

        Integer numberOfEdges = 0;
        Character before = '*';     // oznacava prethodni znak radi dobivanja cijene brida
        Integer indexOfEdge = 0;


        //dobivanje matrice u integer obliku
        for (int i = 0; i < createPathDTO.getIncidenceMatrix().length(); i++) {
            if (Character.isDigit(createPathDTO.getIncidenceMatrix().charAt(i)))
                integerMatrix.add(Character.getNumericValue(createPathDTO.getIncidenceMatrix().charAt(i)));
        }


        // brojanje vrhova
        for (int i = 0; i < createPathDTO.getEdgeWeight().length(); i++) {
            if (createPathDTO.getEdgeWeight().charAt(i) == ':')
                numberOfEdges+=1;

        }


        //dobivanje rubova klasa Edge

        for(int i = 0; i < numberOfEdges; i++){
            Edge rub = new Edge();
            int prviVrh = 0;
            int drugiVrh = 0;
            for(int j = i; j < integerMatrix.size(); j+=numberOfEdges){


                if(integerMatrix.get(j) == 1 && prviVrh == 0)
                    prviVrh = j/numberOfEdges+1;
                else if(integerMatrix.get(j) == 1 && prviVrh != 0)
                    drugiVrh = j/numberOfEdges+1;

            }
            rub.setSource(new Vertex("Node_"+prviVrh, "Node_"+prviVrh));
            rub.setDestination(new Vertex("Node_"+drugiVrh, "Node_"+drugiVrh));
            listOfEdges.add(rub);
        }

        for(int i = 0; i < createPathDTO.getEdgeWeight().length(); i++){
            if(Character.isDigit(createPathDTO.getEdgeWeight().charAt(i)) && before == ' '){
                listOfEdges.get(indexOfEdge).setWeight(Character.getNumericValue(createPathDTO.getEdgeWeight().charAt(i)));
                indexOfEdge+=1;
            }

            before = createPathDTO.getEdgeWeight().charAt(i);
        }


        // izrada liste vrhova

        for(int i = 0; i < integerMatrix.size()/numberOfEdges; i++){
            Integer nameOfVertex = i+1;
            Vertex v = new Vertex("Node_" + nameOfVertex, "Node_" + nameOfVertex);
            listOfVertexes.add(v);
        }

        this.nodes = listOfVertexes;
        this.edges = listOfEdges;

        this.execute(listOfVertexes.get(Integer.parseInt(createPathDTO.getFirstVertex())-1));
        LinkedList<Vertex> path = this.getPath(listOfVertexes.get(Integer.parseInt(createPathDTO.getLastVertex())-1));

        String pathString = path.toString();
        pathString = pathString.substring(1, pathString.length()-1).replace(',', '-').replace(' ', '>');  //formira se izlazni string


        return new SolutionDTO(pathString);
    }





    //logika algoritma


    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
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
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
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

    private boolean isSettled(Vertex vertex) {
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
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
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
        return path;
    }

    public List<CreatePathDTO> getAllData() {
        return repository.findAll();
    }
}