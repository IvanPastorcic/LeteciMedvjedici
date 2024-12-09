import React from "react";
import './Login.css';

function Login(props) {
    function handleGoogleLogin() {
        let BACKEND_URL = 'http://localhost:8081';
        if(process.env.BACKEND_URL) {
            BACKEND_URL = process.env.BACKEND_URL;
        }
        window.location.href = `${BACKEND_URL}/login`
    }

    return (
        <div className="Login">
            <h2>Log in</h2>
            <button onClick={handleGoogleLogin} className="google-login">Use your Google login</button>
        </div>
    );
}

export default Login;
