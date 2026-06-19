import React from 'react';
import { Link } from 'react-router-dom';
import pikachuImg from '../pikachu.jpg';

function Home() {

return (


<div
  style={{
    minHeight: '100vh',
    backgroundColor: '#2c2c54',
    color: 'white',
    padding: '40px'
  }}
>

  <div className="container">

    <h1
      style={{
        textAlign: 'center',
        marginBottom: '30px'
      }}
    >
      Bem-vindo ao Pokémon RPG
    </h1>

    <div
      className="text-center"
      style={{
        marginBottom: '30px'
      }}
    >

      <img
        src={pikachuImg}
        alt="Pikachu"
        style={{
          width: '300px',
          borderRadius: '15px'
        }}
      />

    </div>

    <div
      className="card p-4"
      style={{
        backgroundColor: '#40407a',
        color: 'white'
      }}
    >

      <h3>Treinador</h3>

      <p>
        E-mail:
        {' '}
        {localStorage.getItem('user_email')}
      </p>

      <p>
        Status:
        Online
      </p>

    </div>

    <br />

    <div className="row">

      <div className="col-md-3">

        <Link
          to="/captura"
          className="btn btn-success w-100"
        >
          Capturar Pokémon
        </Link>

      </div>

      <div className="col-md-3">

        <Link
          to="/equipe"
          className="btn btn-primary w-100"
        >
          Minha Equipe
        </Link>

      </div>

      <div className="col-md-3">

        <Link
          to="/batalha"
          className="btn btn-danger w-100"
        >
          Batalha
        </Link>

      </div>

      <div className="col-md-3">

        <Link
          to="/ranking"
          className="btn btn-warning w-100"
        >
          Ranking
        </Link>

      </div>

    </div>

    <br />

    <div
      className="card p-4"
      style={{
        backgroundColor: '#40407a',
        color: 'white'
      }}
    >

      <h3>Objetivo do Jogo</h3>

      <p>
        Capture Pokémon, monte sua equipe,
        participe de batalhas e suba no ranking
        dos melhores treinadores.
      </p>

    </div>

  </div>

</div>


);

}

export default Home;
