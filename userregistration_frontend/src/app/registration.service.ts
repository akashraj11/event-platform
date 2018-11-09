import { Injectable } from '@angular/core';
import {HttpHeaders,HttpClient} from '@angular/common/http'
import { Observable, throwError } from 'rxjs';
import { UserType } from './user';

const httpOptions = {
  headers: new HttpHeaders (
    {
      'Content-Type':'application/json'
    }
  )
}


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  url ="/api/v1/user";

  constructor(private httpClient:HttpClient) { }
  saveUserDetails( user): Observable<any>
  {
    return this.httpClient.post(this.url,user,httpOptions)
  }
}
