import { Component, OnInit } from '@angular/core';
import { HistoryItem } from 'src/app/model/history';
import { HistoryService } from 'src/app/services/history.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  history: HistoryItem[]

  constructor(private historyService: HistoryService) { }

  ngOnInit() {
    this.historyService.getHistory().subscribe(response => this.history = response);
  }

}
