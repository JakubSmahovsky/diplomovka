import React, { Component } from 'react';
import './HeadInfo.css';

export default class HeadInfo extends Component {
  constructor(props) {
    super(props)
  }
  render () {
    return (
      <div className="HeadInfo">
        <h2 className="Title">Základné informácie</h2>
        <table className="Temp_info">
          <tr>
            <th> Meno </th>
            <th> Jakub Šmahovský </th>
          </tr>
          <tr>
            <td> Mail </td>
            <td> jakubsmahovsky@gmail.com </td>
          </tr>
          <tr>
            <td> Školiteľ </td> 
            <td> doc. RNDr. Martin Stanek PhD. (môže sa zmeniť) </td>
          </tr>
          <tr>
            <td> Téma </td>
            <td> Znenie zatiaľ nemám ale cieľ je prispieť do aplikácie Verifpal</td>
          </tr>
          <tr>
            <td> Stav </td>
            <td> Komunikácia s tvorcom aplikácie Verifpal</td>
          </tr>

        </table>
      </div>
    )
  }
}