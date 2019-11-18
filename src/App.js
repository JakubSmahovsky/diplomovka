import React, { Component } from 'react';
import './App.css';

import HeadInfo from './HeadInfo/HeadInfo.js';
import Resources from './Resources/Resources.js';
import Login from './Login/Login.js';

export default class App extends Component {
  constructor(props) {
    super(props) 
    
    this.state = {
      loginStatus: false
    }

    this.setLoginStatus = this.setLoginStatus.bind(this)
  }

  setLoginStatus = (loginStatus) => {
    this.setState({
      loginStatus
    })
  }
  
  render () {
    return (
      <div className="App">
        {this.state.loginStatus ? '' : <Login setLoginStatus={this.setLoginStatus}/>}
        <HeadInfo/>
        <Resources/>
      </div>
    );}
}
