import React from "react";
import './Login.css';

function Login(props) {
    function handleGoogleLogin() {
        const backendUrl = process.env.REACT_APP_BACKEND_URL || 'http://localhost:8081/safebear-backend';
        window.location.href = `${backendUrl}/login`;
    }

    return (
        <div className="Login">
            <h2>Log in</h2>
            <button onClick={handleGoogleLogin} className="google-login">Use your Google login</button>
        </div>
    );
}

export default Login;
