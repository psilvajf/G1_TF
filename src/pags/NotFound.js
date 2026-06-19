import '../App.css';
import { Link, useNavigate } from 'react-router-dom';
import { RiLogoutCircleRLine } from 'react-icons/ri';
import { Nav,  Button } from 'react-bootstrap';


function NotFound() {

  const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('auth'); // Remove o item auth do localStorage
        alert("Logout realizado com sucesso!"); // Alerta de sucesso
        navigate('/email'); // Redireciona para a página de fipe
    }


  return (

    <>

      <h1 class="cor_navbar corForm" style={{ textAlign: 'center', height: 100 }}>404<br /><br />
        <Button variant='outline-light'>
          <Link to="/home" style={{ textDecoration: 'none', color: 'inherit' }}>
            <h5>Voltar para início</h5>
          </Link>
          
        </Button>
        
        <Nav.Link onClick={handleLogout}> <RiLogoutCircleRLine color={"FFFFFF"} size={24} /> </Nav.Link>&nbsp;
        
        </h1>
          

      <div className='align-self-end'>
        <footer className="bg-light text-center text-lg-start position-fixed bottom-0 w-100">
          <div className="text-center p-3" style={{ backgroundColor: "lightblue" }}>
            © 2025 MM:
            <a className="text-dark" href="https://uniacademia.edu.br" target='_blank' rel="noreferrer" > Desenvolvimento Front End </a>
          </div>
        </footer>
      </div>

    </>


  );
}

export default NotFound;
