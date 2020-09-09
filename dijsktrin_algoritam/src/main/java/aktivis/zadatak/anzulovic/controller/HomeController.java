package aktivis.zadatak.anzulovic.controller;

import aktivis.zadatak.anzulovic.objects.CreatePathDTO;
import aktivis.zadatak.anzulovic.objects.SolutionDTO;
import aktivis.zadatak.anzulovic.repository.DijkstraRepository;
import aktivis.zadatak.anzulovic.service.DijkstraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    @Autowired
    private DijkstraService service;

    public HomeController() {
        this.service = DijkstraService.getInstance();
    }

    /*String incidenceMatrix =    "1,1,1,0,0,0,0,0,0,0 \n" +
            "1,0,0,1,1,0,0,0,0,0 \n" +
            "0,1,0,1,0,1,1,1,0,0 \n" +
            "0,0,1,0,0,1,0,0,1,0 \n" +
            "0,0,0,0,0,0,1,0,1,1 \n" +
            "0,0,0,0,1,0,0,1,0,1";

    String edgesWeights =  "1: 3\n" +
            "2: 7\n" +
            "3: 4\n" +
            "4: 2\n" +
            "5: 9\n" +
            "6: 1\n" +
            "7: 3\n" +
            "8: 6\n" +
            "9: 3\n" +
            "10: 3";
*/
    @GetMapping
    public List<CreatePathDTO> getAll(){
        return service.getAllData();
    };

    @PostMapping
    public SolutionDTO createPath(@RequestBody final CreatePathDTO createPathDTO){
        //System.out.println(service.createPath(createPathDTO).getClass());
        return service.createPath(createPathDTO);
    }



}
