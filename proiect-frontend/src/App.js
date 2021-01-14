import React from 'react';
import './App.css';
import { BrowserRouter } from 'react-router-dom';
import RoutesContainer from './RoutesContainer';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <RoutesContainer/>
      </div>
    </BrowserRouter>
  );
}

export default App;