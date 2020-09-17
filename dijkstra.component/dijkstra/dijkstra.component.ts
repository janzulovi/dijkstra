import { Component, OnInit} from '@angular/core';
import { DijkstraService } from 'src/app/dijkstra.service/dijkstra.service';

import { SolutionDto } from 'src/app/SolutionDto';

@Component({
  selector: 'app-dijkstra',
  templateUrl: './dijkstra.component.html',
  styleUrls: ['./dijkstra.component.css']
})

export class DijkstraComponent implements OnInit {

  solution: SolutionDto;

  constructor(private dijkstra: DijkstraService) { }

  

  ngOnInit(): void {
  }

 

  getPath(ev: Event, incidenceMatrix: String, edgeWeight: String, firstVertex: String, lastVertex: String): void{
    
   ev.preventDefault();
   this.dijkstra.getSolution(incidenceMatrix, edgeWeight, firstVertex, lastVertex).subscribe(res => this.solution = res);
   console.log(this.solution);
   
  }


}
