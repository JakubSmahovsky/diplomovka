import React, { Component } from 'react';
import './Resource.css';
// import fileDownload from 'js-file-download';

export default class Resource extends Component {
  constructor(props) {
    super(props)

    this.handleLink = this.handleLink.bind(this)
    // this.requestFile = this.requestFile.bind(this)
  }

  handleLink = () => {
    window.open(this.props.srclink)
  }

  /*
  requestFile = event => {
    window.open("https://verifpal.com/res/pdf/manual.pdf")
    
    event.preventDefault()
    axios.post('/api/resource', {
      filename: "verifpal_manual.pdf"
    }).then(res => {
      fileDownload(res.data, "verifpal_manual.pdf")
    })
    
  }
  */

  render () {
    return (
      <tr>
        <td>{this.props.name}</td>
        {this.props.org ? <td>{this.props.org}</td> : ''}
        <td>{this.props.desc}</td>
        <td>
          <p className="Link" onClick={this.handleLink}>{this.props.srcdesc}</p>
        </td>
      </tr>
    );}
}
