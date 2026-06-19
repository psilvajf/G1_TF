import { Container, Nav, Navbar, Button } from 'react-bootstrap';
import logox from '../apis.svg';
import { Link, useNavigate } from 'react-router-dom';
import '../App.css';
import capa from '../capa.jpg';
import PrivateRoute from './PrivateRoute';
import { RiLogoutCircleRLine } from 'react-icons/ri';
import emailjs from 'emailjs-com';

const sendLogToEmail = (menuName) => {

const serviceID = 'service_c64gmup';
const templateID = 'template_vw7o3d2';
const userID = 'jp6u744vgR8dDF4Yg';

const templateParams = {
menu_name: menuName,
timestamp: new Date().toLocaleString(),
user_name: localStorage.getItem('username') || 'Visitante',
user_email:
localStorage.getItem('user_email')
|| '[email@desconhecido.com](mailto:email@desconhecido.com)',
};

emailjs.send(
serviceID,
templateID,
templateParams,
userID
)
.then(response => {
console.log(
'Log enviado com sucesso:',
response.status,
response.text
);
})
.catch(error => {
console.error(
'Erro ao enviar log:',
error
);
});
};

const Menu = ({ children }) => {

const navigate = useNavigate();

const handleLogout = () => {


sendLogToEmail('Logout');

localStorage.removeItem('auth');
localStorage.removeItem('username');
localStorage.removeItem('user_email');

alert("Logout realizado com sucesso!");

navigate('/');


};

return (
<> <PrivateRoute>


    <Navbar
      expand="lg"
      style={{
        backgroundImage: `url(${capa})`,
        backgroundSize: 'cover'
      }}
    >

      &nbsp;&nbsp;

      <img
        className='App-logo'
        src={logox}
        width={80}
        alt="logo"
      />

      &nbsp;&nbsp;

      <Button variant='dark'>
        <h6>
          Pokémon RPG
        </h6>
      </Button>

      &nbsp;

      <Container>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />

        <Navbar.Collapse id="basic-navbar-nav">

          <Nav className="me-auto">

            <Button
              variant='outline-warning'
              onClick={() => sendLogToEmail('Home')}
            >
              <Link
                to="/home"
                style={{
                  textDecoration: 'none',
                  color: 'inherit'
                }}
              >
                <h5>Home</h5>
              </Link>
            </Button>

            &nbsp;

            <Button
              variant='outline-success'
              onClick={() => sendLogToEmail('Captura')}
            >
              <Link
                to="/captura"
                style={{
                  textDecoration: 'none',
                  color: 'inherit'
                }}
              >
                <h5>Capturar Pokémon</h5>
              </Link>
            </Button>

            &nbsp;

            <Button
              variant='outline-primary'
              onClick={() => sendLogToEmail('Equipe')}
            >
              <Link
                to="/equipe"
                style={{
                  textDecoration: 'none',
                  color: 'inherit'
                }}
              >
                <h5>Minha Equipe</h5>
              </Link>
            </Button>

            &nbsp;

            <Button
              variant='outline-danger'
              onClick={() => sendLogToEmail('Batalha')}
            >
              <Link
                to="/batalha"
                style={{
                  textDecoration: 'none',
                  color: 'inherit'
                }}
              >
                <h5>Batalha</h5>
              </Link>
            </Button>

            &nbsp;

            <Button
              variant='outline-info'
              onClick={() => sendLogToEmail('Ranking')}
            >
              <Link
                to="/ranking"
                style={{
                  textDecoration: 'none',
                  color: 'inherit'
                }}
              >
                <h5>Ranking</h5>
              </Link>
            </Button>

            &nbsp;

            <Button
              variant='outline-secondary'
              onClick={() => sendLogToEmail('Sobre')}
            >
              <Link
                to="/email"
                style={{
                  textDecoration: 'none',
                  color: 'inherit'
                }}
              >
                <h5>Sobre</h5>
              </Link>
            </Button>

            &nbsp;

            <Nav.Link onClick={handleLogout}>
              <RiLogoutCircleRLine
                color={"FFFFFF"}
                size={24}
              />
            </Nav.Link>

          </Nav>

        </Navbar.Collapse>

      </Container>

    </Navbar>

    {children}

    <div className='align-self-end'>

      <footer className="bg-light text-center text-lg-start position-fixed bottom-0 w-100">

        <div
          className="text-center p-3"
          style={{
            backgroundColor: "white"
          }}
        >

          © 2026 Pokémon RPG - Trabalho Acadêmico

        </div>

      </footer>

    </div>

  </PrivateRoute>
</>


);
};

export default Menu;
