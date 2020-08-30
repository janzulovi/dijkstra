package aktivis.zadatak.anzulovic.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DijkstraRepository {

    private static DijkstraRepository instance = null;

    public DijkstraRepository() {
    }

    public static DijkstraRepository getInstance() {
        if(instance == null)
            instance = new DijkstraRepository();
        return instance;
    }

    public void saveData(String incidenceMatrix, String edgesWeights, String firstVertex, String lastVertex) {

    }
}
