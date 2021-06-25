import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { HistoryItem } from '../model/history';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  apiUrl = environment.apiUrl

  constructor(private http: HttpClient) { }

  getHistory() {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.get<HistoryItem[]>(`${this.apiUrl}history`, config);
  }
}
