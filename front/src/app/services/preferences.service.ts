import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { UserPreferences } from '../model/user-preferences';

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
}
