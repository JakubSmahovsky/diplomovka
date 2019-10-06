import React from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="Temp_header">
        <p>
          Toto je stránka diplomovej práce
        </p>
      </header>
      <table className="Temp_info">
        <tr>
          <th> Meno: </th>
          <th> Jakub Šmahovský </th>
        </tr>
        <tr>
          <td> Mail: </td>
          <td> jakubsmahovsky@gmail.com </td>
        </tr>
        <tr>
          <td> Školiteľ a téma: </td> 
          <td> comming soon </td>
        </tr>
      </table>
    </div>
  );
}

export default App;
