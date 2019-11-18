import React, { Component } from 'react';
import './HeadInfo.css';

export default class HeadInfo extends Component {
  render () {
    return (
      <div className="HeadInfo">
        <h2 className="Title">Základné informácie</h2>
        <table className="Temp_info">
          <tr>
            <th> Meno </th>
            <td> Jakub Šmahovský </td>
          </tr>
          <tr>
            <th> Mail </th>
            <td> jakubsmahovsky@gmail.com </td>
          </tr>
          <tr>
            <th> Školiteľ </th> 
            <td> doc. RNDr. Martin Stanek PhD. (pravdepodobne) </td>
          </tr>
          <tr>
            <th> Téma </th>
            <td> 
              Potrebujem vymyslieť znenie. Cieľom je prispieť do programu Verifpal, 
              ktorý slúži na automatickú formálnu verifikáciu kryptografických protokolov. 
              To môže predstavovať napríklad napísanie formálneho dôkazu úplnosti a/alebo korektnosti programu, 
              rozšírenie triedy útokov, ktoré program odhaľuje alebo opravu závažnejších chýb.
            </td>
          </tr>
          <tr>
            <th> Stav </th>
            <td> 
              Skontaktoval som sa s tvorcom Verifpal, dohodli sme sa na spolupráci. 
              Potrebujem vymyslieť zadanie a zadať ho oficiálne do AiS-u. 
              Oboznamujem sa s fungovaním Verifpal tým, že v ňom modelujem rôzne Protokoly.
            </td>
          </tr>

        </table>
      </div>
    )
  }
}