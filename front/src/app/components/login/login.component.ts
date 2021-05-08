import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { LoginResponse } from 'src/app/model/login-response';
import { RegistrationService } from 'src/app/services/registration.service';
import { RegistrationResponse } from 'src/app/model/registration-response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: string;
  newAccount: string;
  response: LoginResponse;
  regResponse: RegistrationResponse;
  loginFailed: boolean = false;
  regFailed: boolean = false;
  regSuccess: boolean = false;

  constructor(private router: Router, private loginService: LoginService, private registerService: RegistrationService) { 
    if(this.loginService.isLoggedIn()){
      this.router.navigateByUrl('/home');
    }
  }

  ngOnInit() {
  }

  login(credentials) {
    this.credentials = "{\"username\":\"" + credentials.username + "\","
      + " \"password\":\"" + credentials.password + "\"}";

    this.loginService.login(this.credentials).subscribe((response) => {
      this.response = response;
      if(this.response.success == true){
        this.loginService.setUser(this.response.token, this.response.admin);
        this.router.navigateByUrl('/home');
      } 
      if(this.response.success == false){
        this.loginFailed = true;
      }
    });
  }

  register(data) {
    this.newAccount = "{\"username\":\"" + data.username + "\","
      + " \"password\":\"" + data.password + "\"}";
    console.log(this.newAccount);
    this.registerService.register(this.newAccount).subscribe((response) => {
      this.regResponse = response;
      this.regFailed = false;
      this.regSuccess = false;
      if(this.regResponse.success == true){
        this.regSuccess = true;
        this.router.navigateByUrl('/login');
      } else {
        this.regFailed = true;
      }
    });
  }

}