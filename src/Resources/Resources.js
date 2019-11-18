import React, { Component } from 'react';
import Resource from './Resource/Resource.js';
import "./Resources.css"

export default class Resources extends Component {
  render () {
    const books=[
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
      },
    ]

    const conferences = [
      {
        name: "NGI0 Helsinki",
        desc: "Verifpal bol publikovaný v tomto talku na NGI0 Helsinky 25. Septembra, 2019.",
        org: "NGI",
        srcdesc: "článok o Verifpal",
        srclink: "https://www.ngi.eu/blog/2019/10/04/whos_ngi_nadim_kobeissi_tells_more_about_verifpal/"
      },
      {
        name: "Mozilla Berlin",
        desc: "Verifpal bol publikovaný v tomto talku na Mozilla Berlin 5. Novembera, 2019.",
        org: "Berlin Crypto",
        srcdesc: "článok o Verifpal",
        srclink: "https://berlin-crypto.github.io/event/verifpal.html"
      },
      {
        name: "Eurocrypt2020",
        desc: "Eurocrypt2020 Zagreb 9. Mája, 2020. Na konferencii bude Verifpal tutorial.",
        org: "International Association for Cpytologic Research",
        srcdesc: "Eurocrypt2020",
        srclink: "https://eurocrypt.iacr.org/2020/"
      }
    ]

    return (
      <div>
        <h2 className="Title">Zdroje</h2>
        <p>Sťahovanie z lokálnych kópií sa mi nedarilo spojazdniť, preto sa dočasne súbory stiahnu z verejných zdrojov.</p>
        <table>
          <tr>
            <th className="TableTitle" colSpan="3">
              Knihy a články
            </th>
          </tr>
          <tr>
            <th>Názov</th>
            <th>Popis</th>
            <th>Zdroj</th>
          </tr>
          {books.map(ress => {return <Resource name={ress.name} desc={ress.desc} srcdesc={ress.srcdesc} srclink={ress.srclink}/>})}
        </table>
        <table>
          <tr>
            <th className="TableTitle" colSpan="4">
              Konferencie
            </th>
          </tr>
          <tr>
            <th>Názov</th>
            <th>Organizátor</th>
            <th>Popis</th>
            <th>Zdroj</th>
          </tr>
          {conferences.map(ress => {return <Resource name={ress.name} org={ress.org} desc={ress.desc} srcdesc={ress.srcdesc} srclink={ress.srclink}/>})}
        </table>
      </div>
  );}
}