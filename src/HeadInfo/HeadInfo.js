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
            <td> doc. RNDr. Martin Stanek PhD.</td>
          </tr>
          <tr>
            <th> Téma </th>
            <td> 
              Automatická verifikácia kryptografických protokolov v programe Verifpal
            </td>
          </tr>
          <tr>
            <th> Anotácia </th>
            <td> 
              Program Verifpal umožňuje analýzu bezpečnostných vlastností kryptografických protokolov spôsobom,
              ktorý je podstatne jednoduchší ako u iných súčasných nástrojov s rovnakým zameraním.
              Cieľom práce je rozšíriť Verifpal o chýbajúcu funkčnosť, umožniť modelovať širšiu triedu protokolov
              a zároveň prispieť k dôkazom úplnosti a korektnosti analýzy.
            </td>
          </tr>
          <tr>
            <th> Stav </th>
            <td> 
              Skontaktoval som sa s tvorcom Verifpal, dohodli sme sa na spolupráci.
              Nasledujúci krok je poriadne sa oboznámiť z programom Verifpal, aby som sa mohol zapojiť do jeho vývoja.
              Modelovanie v momentálom stave programu neviedlo k dobrým výsledkom. V programe ešte výrazne chýbajú niektoré potrebné konštrukcie.
            </td>
          </tr>

        </table>
      </div>
    )
  }
}