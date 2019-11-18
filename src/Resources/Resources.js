import React, { Component } from 'react';
import Resource from './Resource/Resource.js';
import "./Resources.css"

export default class Resources extends Component {
  render () {
    const linkress=[
      {
        name: "Verifpal manuál",
        desc: "Manuál k programu Verifpal, verzia zo September 15 2019 (verzia zrdoja môže byť novšia).",
        srcdesc: "manual.pdf",
        srclink: "https://verifpal.com/res/pdf/manual.pdf"
      },
      {
        name: "ProVerif manuál",
        desc: "ProVerif je momentálny \"state of the art\" program na automatickú formálnu verifikáciu kryptografických protokolov. Ideálom tejto práce je priviesť Verifpal aspoň na rovnakú úroveň s programom ProVerif",
        srcdesc: "manual.pdf",
        srclink: "https://prosecco.gforge.inria.fr/personal/bblanche/proverif/manual.pdf"
      }
    ]

    return (
      <div>
        <h2 className="Title">Zdroje</h2>
        <p>Sťahovanie z lokálnych kópií sa mi nedarilo spojazdniť, preto sa dočasne súbory stiahnu zo zdrojov.</p>
        <table>
          <tr>
            <th>Názov</th>
            <th>Popis</th>
            <th>Zdroj</th>
          </tr>
          {linkress.map(ress => {return <Resource name={ress.name} desc={ress.desc} srcdesc={ress.srcdesc} srclink={ress.srclink}/>})}
        </table>
      </div>
  );}
}
