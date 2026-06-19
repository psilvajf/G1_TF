import { Button, Form } from "react-bootstrap";
import { useState } from 'react';
import emailjs from '@emailjs/browser';

function EMAIL() {
    const [form, setForm] = useState({
        nome: '',
        email: '',
        telefone: '',
        mensagem: '',
    });

    const handleChangeForm = (event) => {
        setForm({ ...form, [event.target.name]: event.target.value });
    };

    const sendEmail = (e) => {
        e.preventDefault();
        if (form.nome === "" || form.email === "" || form.telefone === "" || form.mensagem === "") {
            alert("Informe todos os campos");
            return;
        }

        emailjs.sendForm('service_c64gmup', 'template_f7aebv9', e.target, {
            publicKey: 'jp6u744vgR8dDF4Yg',
        }).then(
            () => {
                alert("Formulário enviado com sucesso");
                setForm({ nome: '', email: '', telefone: '', mensagem: '' });
            },
            () => {
                alert("Falha no envio");
            }
        );
    };

    return (
        <div style={{ backgroundColor: '#6b6bc9', padding: '40px 0' }}>
            <h2 style={{ textAlign: 'center', color: 'white', marginBottom: 40 }}>
                Formulário de contato
            </h2>

            <div style={{ display: 'flex', justifyContent: 'center', gap: 50 }}>
                {/* Formulário */}
                <Form onSubmit={sendEmail} className="bg-body-tertiary p-4 rounded" style={{ width: 500 }}>
                    <Form.Group className="mb-3">
                        <Form.Label><strong>Nome</strong></Form.Label>
                        <Form.Control type="text" name="nome" placeholder="Digite seu nome" value={form.nome} onChange={handleChangeForm} />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label><strong>Email</strong></Form.Label>
                        <Form.Control type="email" name="email" placeholder="Digite seu email" value={form.email} onChange={handleChangeForm} />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label><strong>Telefone</strong></Form.Label>
                        <Form.Control type="tel" name="telefone" placeholder="Digite seu telefone" value={form.telefone} onChange={handleChangeForm} />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label><strong>Envie sua dúvida</strong></Form.Label>
                        <Form.Control as="textarea" name="mensagem" rows={3} placeholder="Digite sua mensagem" value={form.mensagem} onChange={handleChangeForm} />
                    </Form.Group>

                    <Button variant="warning" type="submit" style={{ width: "100%" }}>
                        <strong>Enviar</strong>
                    </Button>
                </Form>

                {/* Informações de contato */}
                <div style={{ border: '2px solid black', padding: 20, backgroundColor: 'white', width: 350 }}>
                    <h4 style={{ textAlign: 'center', marginBottom: 20 }}>Informações de contato</h4>
                    <p><strong>Nome:</strong> Pedro Nascimento Silva</p>
                    <p><strong>Email:</strong> pedrosilvajf@gmail.com</p>
                    <p><strong>Telefone:</strong> (32) 98482-3482</p>
                    <p><strong>Tema:</strong> Pokémon</p>
                </div>
            </div>
        </div>
    );
}

export default EMAIL;
