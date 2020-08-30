package aktivis.zadatak.anzulovic.objects;

public class Edge {

    private Integer id;
    private String vertexsource;
    private String vertexdestination;
    private Integer weight;

    public Edge(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVertexsource() {
        return vertexsource;
    }

    public void setVertexsource(String vertexsource) {
        this.vertexsource = vertexsource;
    }

    public String getVertexdestination() {
        return vertexdestination;
    }

    public void setVertexdestination(String vertexdestination) {
        this.vertexdestination = vertexdestination;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return vertexsource + " " + vertexdestination;
    }
}
