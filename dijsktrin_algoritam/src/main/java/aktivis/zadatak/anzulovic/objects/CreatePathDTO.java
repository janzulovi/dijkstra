package aktivis.zadatak.anzulovic.objects;

import javax.persistence.*;

@Entity(name = "createpath")
public class CreatePathDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "incidencematrix")
    private String incidenceMatrix;
    @Column(name = "edgeweight")
    private String edgeWeight;
    @Column(name = "firstvertex")
    private String firstVertex;
    @Column(name = "lastvertex")
    private String lastVertex;

    public CreatePathDTO() {
    }

    public CreatePathDTO(String incidenceMatrix, String edgeWeight, String firstVertex, String lastVertex) {
        this.incidenceMatrix = incidenceMatrix;
        this.edgeWeight = edgeWeight;
        this.firstVertex = firstVertex;
        this.lastVertex = lastVertex;
    }

    public String getIncidenceMatrix() {
        return incidenceMatrix;
    }

    public void setIncidenceMatrix(String incidenceMatrix) {
        this.incidenceMatrix = incidenceMatrix;
    }

    public String getEdgeWeight() {
        return edgeWeight;
    }

    public void setEdgeWeight(String edgeWeight) {
        this.edgeWeight = edgeWeight;
    }

    public String getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(String firstVertex) {
        this.firstVertex = firstVertex;
    }

    public String getLastVertex() {
        return lastVertex;
    }

    public void setLastVertex(String lastVertex) {
        this.lastVertex = lastVertex;
    }
}
