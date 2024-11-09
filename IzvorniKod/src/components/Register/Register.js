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
            <h2>Register</h2>
            <div className="register-content">
                <p>Don't have an account yet?</p>
                {error && <p className="error">{error}</p>} {/* Prikaz greške */}
                <button className="register-button" onClick={() => navigate('/register')}>Use your Google account to register</button> 
            </div>
        
    </div>
    )
}

export default Register;
