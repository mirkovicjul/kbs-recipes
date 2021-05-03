import './App.css';
import React, { Component } from 'react';
import reactDom from 'react-dom';
import LoginForm from './components/LoginForm.js';
import NavBar from './components/NavBar.js';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import { redirectToLogin } from './services/LoginService.js';


class App extends Component {
  constructor(props) {
    super(props);
  }
  
  render() {
    return redirectToLogin(
      <Router>
      <div>
        <NavBar />
        <Switch>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/users">
            <Users />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </div>
    </Router>
    );
  }
}

export default App;

function Home() {
  return <h2>Home</h2>;
}

function About() {
  return <h2>About</h2>;
}

function Users() {
  return <h2>Users</h2>;
}