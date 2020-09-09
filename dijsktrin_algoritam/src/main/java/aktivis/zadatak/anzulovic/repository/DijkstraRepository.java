package aktivis.zadatak.anzulovic.repository;

import aktivis.zadatak.anzulovic.objects.CreatePathDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DijkstraRepository extends JpaRepository<CreatePathDTO, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into createpath(incidencematrix, edgeweight, firstvertex, lastvertex) " +
            "values (:incidenceMatrix, " +
            ":edgeWeight, " +
            ":firstVertex, " +
            ":lastVertex)", nativeQuery = true)
    void addData(String incidenceMatrix, String edgeWeight, String firstVertex, String lastVertex);
}
