import { useEffect, useState } from "react";

export default function Equipe() {


const [equipes, setEquipes] =
    useState([]);

useEffect(() => {

    fetch(
        "http://localhost:8080/api/equipes"
    )
        .then(response => response.json())
        .then(data => setEquipes(data))
        .catch(error =>
            console.error(error)
        );

}, []);

return (

    <div className="container mt-4">

        <h1>Minha Equipe</h1>

        {equipes.map(equipe => (

            <div
                key={equipe.id}
                className="card p-3 mb-3"
            >

                <h3>
                    {equipe.nome}
                </h3>

                <p>
                    Status:
                    {equipe.ativa
                        ? " Ativa"
                        : " Inativa"}
                </p>

            </div>

        ))}

    </div>

);


}
