import React from "react";
import './Login.css';

function Login(props) {
    function handleGoogleLogin() {
        // Redirect to Google login route or implement Google login logic here
        window.location.href = "/auth/google"; // Update with your Google login endpoint
    }

    return (
        <div className="Login">
            <h2>Log in</h2>
            <button onClick={handleGoogleLogin} className="google-login">Use your Google login</button>
        </div>
    );
}

export default Login;
