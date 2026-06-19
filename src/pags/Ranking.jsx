import React, { useEffect, useState } from "react";

export default function Ranking() {


const [jogadores, setJogadores] =
    useState([]);

useEffect(() => {

    fetch(
        "http://localhost:8080/api/ranking"
    )
        .then(response => response.json())
        .then(data => setJogadores(data))
        .catch(error =>
            console.error(error)
        );

}, []);

return (

    <div className="container mt-4">

        <h1>Ranking dos Treinadores</h1>

        <table className="table table-striped">

            <thead>

                <tr>

                    <th>#</th>
                    <th>Nome</th>
                    <th>Nível</th>
                    <th>Pontuação</th>

                </tr>

            </thead>

            <tbody>

                {jogadores.map(
                    (jogador, index) => (

                        <tr key={jogador.id}>

                            <td>
                                {index + 1}
                            </td>

                            <td>
                                {jogador.nome}
                            </td>

                            <td>
                                {jogador.nivel}
                            </td>

                            <td>
                                {jogador.pontuacaoTotal}
                            </td>

                        </tr>

                    )
                )}

            </tbody>

        </table>

    </div>

);


}
