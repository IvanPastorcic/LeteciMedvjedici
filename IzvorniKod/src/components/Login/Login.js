import React from "react";
import './Login.css';

function Login(props){
    const [loginForm, setLoginForm] = React.useState({email: '', password: ''});
    const [error, setError] = React.useState('');

    function onChange(event){
        const {name, value} = event.target;
        setLoginForm(oldForm => ({...oldForm, [name]:value}))
    }

    function validateEmail(email){
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    function validatePassword(password){
        return password.length >= 8;
    }

    function onSubmit(e){
        e.preventDefault();
        setError("");

        if (!validateEmail(loginForm.email)) {
            setError("Incorrect e-mail");
            return;
        }

        if (!validatePassword(loginForm.password)) {
            setError("Incorrect password");
            return;
        }

        const body = `e-mail=${loginForm.email}&password=${loginForm.password}`;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: body
        };

        return fetch('/login', options)
        .then(response => {
            if(response.status === 401){
                setError("Login failed");
            }
        });
    }

    return (
        <div className="Login">
            <h2>Log in</h2>
            <form onSubmit={onSubmit}>
                <div className="FormRow">
                    <label>e-mail</label>
                    <input name="email" onChange={onChange} value={loginForm.email}/>
                </div>
                <div className="FormRow">
                    <div className="PasswordRow">
                        <label>password</label>
                        <a href="/forgot-password" className="ForgotPassword">Forgot password?</a>
                    </div>
                    <input name="password" type="password" onChange={onChange} value={loginForm.password}/>
                </div>
                {error && <div className="Error">{error}</div>}
                <button type="submit">Log in</button>

                <div className="separator">
                <hr />
                
                <hr />
            </div>

            <button type="submit">Use your google login</button>
            </form>
        </div>
    )
}



export default Login;