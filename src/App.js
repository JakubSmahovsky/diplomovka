import React, { Component } from 'react';
import axios from 'axios';
import './App.css';
import HeadInfo from './HeadInfo/HeadInfo.js';
import Resources from './Resources/Resources.js';

export default class App extends Component {
  constructor(props) {
    super(props)

    this.state = {
      loggedIn: false
    }

    this.handleChange = this.handleChange.bind(this)
    this.validateForm = this.validateForm.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)

    axios.get('/api/loginStatus')
    .then((res) => {
      this.setState({
        loggedIn: res.data.loggedIn,
        login: '',
        password: '',
        errStatus: false
      })
    })
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  validateForm = () => {
    if (!this.state.login.length > 0){
      this.setState({
        errStatus: true
      })
      return false
    }
    if (!this.state.password.length > 0) {
      this.setState({
        errStatus: true
      })
      return false
    }
    return true
  }

  handleSubmit = event => {
    event.preventDefault()
    if (!this.validateForm()) return

    axios.post('/api/login', {
      login: this.state.login,
      password: this.state.password
    })
    .then(res => {
      if (res.data.status === 'success') {
        this.setState({
          errStatus: false,
          loggedIn: true
        })
      } else {
        this.setState({
          errStatus: true,
        })
      }
    })
  }

  render () {
    return (
      <div className="App">
        <h2>Login</h2>
        <p>Na sťahovanie lokálnych kópií je nutné sa prihlásiť</p>
        <form onSubmit={this.handleSubmit}>
              <input value={this.state.login} onChange={this.handleChange} type="text" id="login"/>
              <input value={this.state.password} onChange={this.handleChange} type="password" id="password"/>
              <button type="submit" id="loginBtn">Login</button>
              {this.state.errStatus ? <p>Please log in with valid credetials.</p> : ''}
        </form>
        <HeadInfo/>
        <Resources/>
      </div>
    );}
}
