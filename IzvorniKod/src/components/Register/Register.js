import React from "react";
import './Register.css';
import { useNavigate } from 'react-router-dom';

function Register(props){
    const [error, setError] = React.useState('');
    const navigate = useNavigate(); 

    function onChange(event){
        const {name, value} = event.target;
    }

    function onSubmit(e){
        e.preventDefault();
        setError("");
        
    }

    return (
        <div className="Register">
        <h3>Don't have an account yet?</h3>
        {error && <p className="error">{error}</p>} {/* Prikaz greške */}
        <button onClick={() => navigate('/register')}>Register now</button> 
    </div>
    )
}

export default Register;
