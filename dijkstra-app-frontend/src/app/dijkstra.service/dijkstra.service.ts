import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpHeaders, HttpClient } from '@angular/common/http';

import { tap } from 'rxjs/operators';
import { SolutionDto } from '../SolutionDto';
import { CreatePathDto } from '../CreatePathDto';

@Injectable({
  providedIn: 'root'
})
export class DijkstraService {

  private url = 'http://localhost:8080/home';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor( private http: HttpClient) { }

  getSolution(incidenceMatrix: String, edgeWeight: String, firstVertex: String, lastVertex: String) : Observable<SolutionDto>{
    let obj ={
      incidenceMatrix,
      edgeWeight,
      firstVertex,
      lastVertex
    } as CreatePathDto;
    
     let temp = this.http.post<SolutionDto>(this.url, obj).pipe(
      tap((res: SolutionDto) => console.log(res)));
        console.log(temp);
      return temp;

    
  }
}