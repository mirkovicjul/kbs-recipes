import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UserPreferences } from '../model/user-preferences';
import { RegistrationResponse } from '../model/registration-response';

@Injectable({
  providedIn: 'root'
})
export class PreferencesService {

  apiUrl = environment.apiUrl

  constructor(private http: HttpClient) { }

  getUserPreferences() {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.get<UserPreferences>(`${this.apiUrl}userById`, config);
  }

  addLike(data){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}addLike`, data, config);

  }

  removeLike(data){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}removeLike`, data, config);
  }

  
  addDislike(data){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}addDislike`, data, config);
  }

  removeDislike(data){
    console.log(data)
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}removeDislike`, data, config);
  }

  addAllergy(data){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}addAllergy`, data, config);
  }

  removeAllergy(data){
    console.log(data)
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}removeAllergy`, data, config);
  }
  
  addUnavailable(data){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}addUnavailable`, data, config);
  }

  removeUnavailable(data){
    console.log(data)
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.post<HttpResponse<null>>(`${this.apiUrl}removeUnavailable`, data, config);
  }
}
