import React from "react";
import './Login.css';

function Login(props) {
    function handleGoogleLogin() {
       
       window.location.href = "http://localhost:8081/oauth2/authorization/google"
    }

    return (
        <div className="Login">
            <h2>Log in</h2>
            <button onClick={handleGoogleLogin} className="google-login">Use your Google login</button>
        </div>
    );
}

export default Login;
