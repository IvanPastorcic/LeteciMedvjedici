import React from "react";
import './Login.css';

function Login(props) {
    function handleGoogleLogin() {
        window.location.href = "https://safebear-backend.onrender.com/login"
    }

    return (
        <div className="Login">
            <h2>Log in</h2>
            <button onClick={handleGoogleLogin} className="google-login">Use your Google login</button>
        </div>
    );
}

export default Login;
