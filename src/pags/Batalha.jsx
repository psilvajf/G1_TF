import { useState } from "react";

export default function Batalha() {


const [resultado, setResultado] =
    useState(null);

const batalhar = async () => {

    try {

        const response =
            await fetch(
                "http://localhost:8080/api/batalha",
                {
                    method: "POST",
                    headers: {
                        "Content-Type":
                            "application/json"
                    },
                    body: JSON.stringify({
                        pokemon1: 1,
                        pokemon2: 2
                    })
                }
            );

        const data =
            await response.json();

        setResultado(data);

    } catch (error) {

        alert(
            "Erro ao iniciar batalha"
        );

    }

};

return (

    <div className="container mt-4">

        <h1>Batalha Pokémon</h1>

        <button
            className="btn btn-danger"
            onClick={batalhar}
        >
            Iniciar Batalha
        </button>

        {resultado && (

            <div
                className="card mt-4 p-3"
            >

                <h2>
                    Vencedor:
                    {resultado.vencedor}
                </h2>

                <p>
                    Força Pokémon 1:
                    {resultado.forcaPokemon1}
                </p>

                <p>
                    Força Pokémon 2:
                    {resultado.forcaPokemon2}
                </p>

            </div>

        )}

    </div>

);


}
