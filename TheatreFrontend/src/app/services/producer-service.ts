import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment as env } from "../../environments/environment";
import { Theatre } from "../domain/theatre";
@Injectable({
  providedIn: "root"
})
export class ProducerService {
  private _url: string = "http://localhost:8080/api/v1/addTheatre";
  private _url2: string = "http://localhost:8080/api/v1/deleteTheatre";
  private _url1: string = "http://localhost:8080/api/v1/producer";
  
  constructor(private http: HttpClient) {}

  addTheatre(email:string,theatre:Theatre)
  {
    console.log("add Theatre in service");
    console.log(email);
    const updateurl=`${this._url}/${email}/`;
    this.http.put(updateurl,theatre).subscribe(
      data => {
          console.log("POST Request is successful ", data);
      },
      error => {
          console.log("Error", error);
      });
  }

  deleteTheatre(email:string,theatreName : string)
  {
    console.log("delete Theatre in service");
    console.log(email);
    const updateurl=`${this._url2}/${email}/${theatreName}`;
    this.http.put(updateurl,theatreName).subscribe(
      data => {
          console.log("POST Request is successful ", data);
      },
      error => {
          console.log("Error", error);
      });
  }

  getTheatreByProducerEmail(email:string) {
    console.log("search in service");
    const geturl = `${this._url1}/${email}/`;
    this.http.get(geturl).subscribe(
      data => {
        console.log("get Request is successful ", data);
      },
      error => {
        console.log("Error", error);
      });
    return this.http.get(geturl);
  }
}