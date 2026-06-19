import React, { useState } from "react";

export default function Captura() {


const [pokemon, setPokemon] =
    useState(null);

const [loading, setLoading] =
    useState(false);

const capturar = async () => {

    setLoading(true);

    try {

        const response =
            await fetch(
                "http://localhost:8080/api/captura",
                {
                    method: "POST"
                }
            );

        const data =
            await response.json();

        setPokemon(data);

    } catch (error) {

        alert("Erro ao capturar Pokémon");

    }

    setLoading(false);

};

return (

    <div className="container mt-4">

        <h1>Capturar Pokémon</h1>

        <button
            className="btn btn-success"
            onClick={capturar}
        >
            {loading
                ? "Capturando..."
                : "Capturar"}
        </button>

        {pokemon && (

            <div className="card mt-4 p-3">

                <h3>
                    {pokemon.nome}
                </h3>

                <p>
                    Nível: {pokemon.nivel}
                </p>

                <p>
                    Ataque: {pokemon.ataque}
                </p>

                <p>
                    Defesa: {pokemon.defesa}
                </p>

                <p>
                    Vida: {pokemon.vida}
                </p>

            </div>

        )}

    </div>

);


}
