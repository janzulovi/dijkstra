package aktivis.zadatak.anzulovic;

import aktivis.zadatak.anzulovic.objects.Edge;
import aktivis.zadatak.anzulovic.objects.Graph;
import aktivis.zadatak.anzulovic.objects.Vertex;
import aktivis.zadatak.anzulovic.service.DijkstraService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ReactiveAdapterRegistry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication(
        scanBasePackages = {
                "aktivis.zadatak.anzulovic.objects",
                "aktivis.zadatak.anzulovic.repository"
        }
)
public class AnzulovicApplication {

        static List<Vertex> listOfVertexes = new ArrayList<>();
        static List<Edge> listaBridova = new ArrayList<>();

        public static void main(String[] args) {

            SpringApplication.run(AnzulovicApplication.class, args);

            String incidenceMatrix =    "1,1,1,0,0,0,0,0,0,0 \n" +
                                        "1,0,0,1,1,0,0,0,0,0 \n" +
                                        "0,1,0,1,0,1,1,1,0,0 \n" +
                                        "0,0,1,0,0,1,0,0,1,0 \n" +
                                        "0,0,0,0,0,0,1,0,1,1 \n" +
                                        "0,0,0,0,1,0,0,1,0,1 \n";

            String edges =  "1: 3\n" +
                            "2: 7\n" +
                            "3: 4\n" +
                            "4: 2\n" +
                            "5: 9\n" +
                            "6: 1\n" +
                            "7: 3\n" +
                            "8: 6\n" +
                            "9: 3\n" +
                            "10: 3";

            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < incidenceMatrix.length(); i++) {
                if (Character.isDigit(incidenceMatrix.charAt(i)))
                    list.add(Character.getNumericValue(incidenceMatrix.charAt(i)));
            }

            System.out.println(incidenceMatrix);
            System.out.println(edges);

            Integer br = 1;
            for (Integer i : list) {

                if (br % 10 == 0) {
                    System.out.print(i);
                    System.out.println();
                } else
                    System.out.print(i + ",");

                br += 1;
            }

            Integer numberOfEdges = 0;

            for (int i = 0; i < edges.length(); i++) {
                if (edges.charAt(i) == ':')
                    numberOfEdges+=1;
            }

            System.out.println();
            System.out.println(numberOfEdges);
            System.out.println(list.size());

            List<Integer> listOne = new ArrayList<>();



            //dobivanje rubova klasa Edge

            for(int i = 0; i < numberOfEdges; i++){
                Edge rub = new Edge();
                int prviVrh = 0;
                int drugiVrh = 0;
                for(int j = i; j < list.size(); j+=numberOfEdges){

                    if(list.get(j) == 1)
                        listOne.add(1);

                    if(list.get(j) == 1 && prviVrh == 0)
                        prviVrh = j/numberOfEdges+1;
                    else if(list.get(j) == 1 && prviVrh != 0)
                        drugiVrh = j/numberOfEdges+1;

                }
                rub.setVertexsource(String.valueOf(prviVrh));
                rub.setVertexdestination(String.valueOf(drugiVrh));
                listaBridova.add(rub);
            }

            for (Edge e: listaBridova) {
                System.out.println("vertexes of " + (listaBridova.indexOf(e)+1) + ". edge are " + e.getVertexsource() + " " + e.getVertexdestination());
            }


            Character before = '*';
            Integer indexOfEdge = 0;

            for (int i = 0; i < edges.length(); i++){

                if(Character.isDigit(edges.charAt(i)) && before == ' '){
                    listaBridova.get(indexOfEdge).setWeight(Character.getNumericValue(edges.charAt(i)));
                    indexOfEdge+=1;
                }

                before = edges.charAt(i);
            }





            for(int i = 0; i < list.size()/numberOfEdges; i++){
                Integer nameOfVertex = i+1;
                Vertex v = new Vertex("Node_" + nameOfVertex, "Node_" + nameOfVertex);
                listOfVertexes.add(v);
            }


            Graph graph = Graph.getInstance(listOfVertexes, listaBridova);
            DijkstraService service = DijkstraService.getInstance(graph);

            service.execute(listOfVertexes.get(0));

            String path = service.getPath(listOfVertexes.get(1));

            System.out.println(path);
        }
    }