import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegistrationResponse } from '../model/registration-response';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  apiUrl: String = environment.apiUrl

  constructor(private http: HttpClient) { }

  register(data) {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json'
      }
    }
    return this.http.post<RegistrationResponse>(`${this.apiUrl}user/create`, data, config);
  }

}
1