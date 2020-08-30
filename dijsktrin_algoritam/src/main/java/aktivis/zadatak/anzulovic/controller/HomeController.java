package aktivis.zadatak.anzulovic.controller;

import aktivis.zadatak.anzulovic.service.DijkstraService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    private DijkstraService service;

    public HomeController(DijkstraService service) {
        this.service = service;
    }

    @GetMapping
    public String getPath(String incidenceMatrix,String edgesWeights, String firstVertex, String lastVertex){
        return service.getSolution(incidenceMatrix, edgesWeights, firstVertex, lastVertex);
    }



}
