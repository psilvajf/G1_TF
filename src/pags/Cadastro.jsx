import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

export default function Cadastro() {


const navigate = useNavigate();

const [form, setForm] = useState({
    nome: "",
    email: "",
    senha: ""
});

const [loading, setLoading] = useState(false);

const handleChange = (e) => {

    setForm({
        ...form,
        [e.target.name]: e.target.value
    });

};

const handleSubmit = async (e) => {

    e.preventDefault();

    setLoading(true);

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/register",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    nome: form.nome,
                    email: form.email,
                    senha: form.senha
                })
            }
        );

        if (response.ok) {

            alert("Usuário cadastrado com sucesso!");

            navigate("/");

        } else {

            alert("Erro ao cadastrar usuário.");

        }

    } catch (error) {

        console.error(error);

        alert("Erro ao conectar com o servidor.");

    }

    setLoading(false);

};

return (

    <div className="container mt-5">

        <div className="row justify-content-center">

            <div className="col-md-6">

                <div className="card shadow">

                    <div className="card-body">

                        <h2 className="text-center mb-4">
                            Cadastro de Treinador Pokémon
                        </h2>

                        <form onSubmit={handleSubmit}>

                            <div className="mb-3">

                                <label className="form-label">
                                    Nome
                                </label>

                                <input
                                    type="text"
                                    name="nome"
                                    className="form-control"
                                    value={form.nome}
                                    onChange={handleChange}
                                    required
                                />

                            </div>

                            <div className="mb-3">

                                <label className="form-label">
                                    E-mail
                                </label>

                                <input
                                    type="email"
                                    name="email"
                                    className="form-control"
                                    value={form.email}
                                    onChange={handleChange}
                                    required
                                />

                            </div>

                            <div className="mb-3">

                                <label className="form-label">
                                    Senha
                                </label>

                                <input
                                    type="password"
                                    name="senha"
                                    className="form-control"
                                    value={form.senha}
                                    onChange={handleChange}
                                    required
                                />

                            </div>

                            <button
                                type="submit"
                                className="btn btn-primary w-100"
                                disabled={loading}
                            >
                                {loading
                                    ? "Cadastrando..."
                                    : "Cadastrar"}
                            </button>

                        </form>

                        <hr />

                        <p className="text-center">

                            Já possui conta?

                            <br />

                            <Link to="/">
                                Fazer Login
                            </Link>

                        </p>

                    </div>

                </div>

            </div>

        </div>

    </div>

);


}
