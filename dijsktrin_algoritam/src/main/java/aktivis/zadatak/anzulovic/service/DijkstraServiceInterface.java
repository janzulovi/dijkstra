package aktivis.zadatak.anzulovic.service;

import aktivis.zadatak.anzulovic.objects.CreatePathDTO;
import aktivis.zadatak.anzulovic.objects.SolutionDTO;

import java.util.List;

public interface DijkstraServiceInterface {

    SolutionDTO createPath(CreatePathDTO createPathDTO);

    List<CreatePathDTO> getAllData();
}
