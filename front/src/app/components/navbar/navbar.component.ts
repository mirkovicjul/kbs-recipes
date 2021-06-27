import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private userService: LoginService) { }

  ngOnInit() {
  }

  logout(){
    this.userService.logout().subscribe(res => console.log(res));
  }

}
