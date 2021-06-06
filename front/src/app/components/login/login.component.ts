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
  registrationFailed: boolean = false;
  registrationSuccess: boolean = false;
  errorMessage: string;

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
        credentials.username = "";
        credentials.password = "";
        this.loginFailed = true;
      }
    });
  }

  register(data) {
    console.log(data);
    this.registrationFailed = false;
    this.registrationSuccess = false;
    this.newAccount = "{\"username\":\"" + data.username + "\","
      + " \"email\":\"" + data.email + "\","
      + " \"password\":\"" + data.password + "\"}";
    console.log(this.newAccount);
    this.registerService.register(this.newAccount).subscribe((response) => {
      data.username = "";
      data.password = "";
      data.email = "";
      this.regResponse = response;
      if(this.regResponse.success == true){
        this.registrationSuccess = true;
        this.router.navigateByUrl('/login');
      } else {
        this.registrationFailed = true;
      }
    });
  }

}
