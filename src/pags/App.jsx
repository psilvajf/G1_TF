import '../App.css';
import React, { lazy, Suspense } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import EMAIL from './email';
import HOME from './home';

import Cadastro from './Cadastro';
import Captura from './Captura';
import Equipe from './Equipe';
import Batalha from './Batalha';
import Ranking from './Ranking';

function delayImport(factory, delay = 2000) {
return new Promise((resolve) => {
setTimeout(() => {
factory().then(resolve);
}, delay);
});
}

const Menu = lazy(() => delayImport(() => import('./Menu'), 2500));
const Login = lazy(() => delayImport(() => import('./login'), 3000));
const NotFound = lazy(() => delayImport(() => import('./NotFound'), 2500));

function App() {

return (


<BrowserRouter>

  <Suspense
    fallback={
      <div className="d-flex align-items-center flex-column vh-100 justify-content-center text-center py-3 suspense-loading">

        <div className="loading-container">

          <div className="pikachu-face">
            <div className="ear left"></div>
            <div className="ear right"></div>
            <div className="eye left"></div>
            <div className="eye right"></div>
            <div className="cheek left"></div>
            <div className="cheek right"></div>
            <div className="lightning left"></div>
            <div className="lightning right"></div>
            <div className="mouth"></div>
          </div>

        </div>

        <p className="text-white text-center text-xl pt-3">
          Aguarde um instante...
        </p>

      </div>
    }
  >

    <Routes>

      <Route
        path="/"
        element={<Login />}
      />

      <Route
        path="/cadastro"
        element={<Cadastro />}
      />

      <Route
        path="/home"
        element={
          <Menu>
            <HOME />
          </Menu>
        }
      />

      <Route
        path="/captura"
        element={
          <Menu>
            <Captura />
          </Menu>
        }
      />

      <Route
        path="/equipe"
        element={
          <Menu>
            <Equipe />
          </Menu>
        }
      />

      <Route
        path="/batalha"
        element={
          <Menu>
            <Batalha />
          </Menu>
        }
      />

      <Route
        path="/ranking"
        element={
          <Menu>
            <Ranking />
          </Menu>
        }
      />

      <Route
        path="/email"
        element={
          <Menu>
            <EMAIL />
          </Menu>
        }
      />

      <Route
        path="*"
        element={<NotFound />}
      />

    </Routes>

  </Suspense>

</BrowserRouter>


);

}

export default App;
