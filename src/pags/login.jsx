import React, { useState } from "react";
import { Button, Form, Spinner } from "react-bootstrap";
import { useNavigate, Link } from "react-router-dom";
import '../App.css';
import teste from '../pokemon.jpg';

function Login(props) {


const [form, setForm] = useState({
    login: "",
    senha: ""
});

const [loading, setLoading] = useState(false);

const navigate = useNavigate();

const handleChangeForm = (event) => {

    setForm({
        ...form,
        [event.target.name]: event.target.value
    });

};

const hanldeSubmitForm = async (event) => {

    event.preventDefault();

    setLoading(true);

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: form.login,
                    senha: form.senha
                })
            }
        );

        if (response.ok) {

            localStorage.setItem("auth", "true");
            localStorage.setItem("user_email", form.login);

            alert("Login realizado com sucesso!");

            navigate("/home");

        } else {

            alert("Usuário ou senha inválidos!");

            setForm({
                login: "",
                senha: ""
            });

        }

    } catch (error) {

        console.error(error);

        alert("Erro ao conectar com o servidor!");

    }

    setLoading(false);

};

if (props.menu === "0") {

    return <h1>Sem acesso a essa página....</h1>;

}

return (

    <div
        style={{
            display: 'flex',
            height: '100vh',
            paddingTop: '15%',
            paddingLeft: '3%',
            marginLeft: '2%',
            backgroundColor: '#555',
            position: 'relative',
            overflow: 'hidden'
        }}
    >

        <div
            className="position-relative"
            style={{
                width: 700,
                zIndex: 1
            }}
        >

            <h1
                style={{
                    backgroundColor: '#eee',
                    padding: '20px',
                    marginBottom: 0
                }}
            >
                Enciclopédia Pokémon
            </h1>

            <h2
                className="cor_navbar corForm"
                style={{
                    backgroundColor: 'rgb(108, 108, 195)',
                    color: 'white',
                    padding: '10px'
                }}
            >
                Acesse sua conta
            </h2>

            <Form
                className="w-50"
                onSubmit={hanldeSubmitForm}
            >

                <Form.Group className="meuPadraoFormGroup">

                    <Form.Label>
                        <h5 className="corForm">
                            E-mail
                        </h5>
                    </Form.Label>

                    <Form.Control
                        onChange={handleChangeForm}
                        value={form.login}
                        type="email"
                        placeholder="Digite seu e-mail"
                        name="login"
                        style={{ maxWidth: '300px' }}
                        disabled={loading}
                        required
                    />

                </Form.Group>

                <Form.Group className="meuPadraoFormGroup">

                    <Form.Label>
                        <h5 className="corForm">
                            Senha
                        </h5>
                    </Form.Label>

                    &nbsp;

                    <Form.Control
                        onChange={handleChangeForm}
                        value={form.senha}
                        type="password"
                        placeholder="Senha"
                        name="senha"
                        style={{ maxWidth: '300px' }}
                        disabled={loading}
                        required
                    />

                    <br />

                    <Button
                        variant="warning"
                        type="submit"
                        disabled={loading}
                    >

                        {loading ? (

                            <Spinner
                                as="span"
                                animation="border"
                                size="sm"
                                role="status"
                                aria-hidden="true"
                                variant="dark"
                            />

                        ) : (

                            <h6 className="corForm">
                                ENTRAR
                            </h6>

                        )}

                    </Button>

                    <br />
                    <br />

                    <Link
                        to="/cadastro"
                        className="btn btn-primary"
                    >
                        Criar Conta
                    </Link>

                </Form.Group>

            </Form>

        </div>

        <img
            src={teste}
            alt="Banner Pokémon"
            style={{
                position: 'absolute',
                top: 0,
                right: 0,
                height: '100vh',
                width: 'auto',
                objectFit: 'cover',
                borderLeft: '2px solid white',
                zIndex: 0
            }}
        />

    </div>

);


}

export default Login;
