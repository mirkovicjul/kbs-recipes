import './LoginForm.css';
import React, { Component } from 'react';

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = { value: '' };
    this.state.name = props.name;

    this.handleLogin = this.handleLogin.bind(this);
    this.handleSignIn = this.handleSignIn.bind(this);
  }

  handleLogin(event) {
    alert('A name was submitted: ' + this.state.value + this.name);
    event.preventDefault();
  }

  handleSignIn(event) {
    alert('A name was submitted: ' + this.state.value + this.name);
    event.preventDefault();
  }

  render() {
    return (
      <div class="container">
        <div class="login-box">
          <form>
            <div class="form-group">
              <label for="name">Username</label>
              <input type="text" class="form-control" id="username" name="username" />
            </div>

            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" name="password" />
            </div>

            <div class="alert alert-danger">
              Incorrect username or password!
            </div>

            <button type="submit" class="btn btn-success">Login</button>
          </form>
        </div>

        <div class="register-box">
          <form>
            <div class="form-group">
              <label for="name">Username</label>
              <input type="text" class="form-control" id="new-username" name="username" />
            </div>

            <div class="form-group">
              <label for="name">E-mail</label>
              <input type="text" class="form-control" id="new-email" name="email"></input>
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="new-password" name="password" />
            </div>
            <div class="alert alert-danger">
              Username already in use!
        </div>
            <div class="alert alert-success">
              Registration successful!
        </div>
            <button type="submit" class="btn btn-success">Join</button>
          </form>
        </div>

        <div class="fork">
          <img src={process.env.PUBLIC_URL + '/spoon.png'} alt="🥄" />
        </div>
      </div>

    );
  }
}

export default LoginForm;