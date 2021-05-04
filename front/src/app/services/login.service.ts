import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginResponse } from '../model/login-response';
import { ElementSchemaRegistry } from '@angular/compiler';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  apiUrl = environment.apiUrl

  constructor(private http: HttpClient) { }

  login(credentials: string) {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json'
      }
    }
    return this.http.post<LoginResponse>(`${this.apiUrl}login`, credentials, config);
  }

  setUser(token: string, admin: Boolean){
    localStorage.setItem('id_token', token);
    localStorage.setItem('admin', String(admin));
  }
  
  logout(){
    //this.admin = false;
    //this.loggedIn = false;
    //this.token = "";
    localStorage.removeItem("id_token");
    localStorage.removeItem("admin");
  }

  isLoggedIn(){
    if(localStorage.getItem("id_token"))
      return true;
    else
      return false;
  }

  isAdmin(){
    if(localStorage.getItem("admin") == "true")
      return true;
    else
      return false;
  }
}
