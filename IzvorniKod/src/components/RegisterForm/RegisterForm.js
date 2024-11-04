import React from "react";
import './RegisterForm.css';

function RegisterForm(props){
    const [registerForm, setRegisterForm] = React.useState({email: '', username: '', password: '', repeatedPassword: ''});
    const [error, setError] = React.useState('');

    function onChange(event){
        const {name, value} = event.target;
        setRegisterForm(oldForm => ({...oldForm, [name]:value}))
    }

    function validateEmail(email){
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    function validateUsername(username){
        return username.length >= 3;
    }

    function validatePassword(password) {
        if (password.length <= 8) {
            return false;
        }
        const hasUpperCase = /[A-Z]/.test(password);
        const hasNumber = /[0-9]/.test(password);
        return hasUpperCase && hasNumber;
    }

    function onSubmit(e){
        e.preventDefault();
        setError("");

        if (!validateEmail(registerForm.email)) {
            setError("Unacceptable e-mail");
            return;
        }

        if (!validateUsername(registerForm.username)) {
            setError("Unacceptable username");
            return;
        }

        if (!validatePassword(registerForm.password)) {
            setError("Unacceptable password");
            return;
        }

        if (registerForm.password !== registerForm.repeatedPassword) {
            setError("Passwords do not match");
            return;
        }

        const body = `email=${registerForm.email}&username=${registerForm.username}&password=${registerForm.password}`;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: body
        };

        return fetch('/register', options)
        .then(response => {
            if (response.status === 400 ) { 
                setError("Registration failed");
            } else if (response.ok) {
                console.log("Registration successful");
            }
        })
        .catch(error => {
            setError("An error occurred. Please try again.");
        });
    }

    return (
        <div className="RegisterForm">
        <form onSubmit={onSubmit}>
            <h3>Register</h3>
            <div>
                <label>e-mail</label>
                <input 
                    type="email" 
                    name="email" 
                    value={registerForm.email} 
                    onChange={onChange} 
                />
            </div>
            <div>
                <label>username</label>
                <input 
                    type="text" 
                    name="username" 
                    value={registerForm.username} 
                    onChange={onChange} 
                />
            </div>
            <div>
                <label>password</label>
                <input 
                    type="password" 
                    name="password" 
                    value={registerForm.password} 
                    onChange={onChange} 
                />
            </div>
            <div>
                <label>repeat your password</label>
                <input 
                    type="password" 
                    name="repeatedPassword" 
                    value={registerForm.repeatedPassword} 
                    onChange={onChange} 
                />
            </div>
            {error && <p style={{ color: "red" }}>{error}</p>}

            <div className="separator">
                <hr />
                <span>or</span>
                <hr />
            </div>

            <button type="submit">Use your google login</button>
        </form>

        <div className="create-account">
            <p>All set? Click 'create account' and wait for confirmation.</p>
            <button type = "submit" className="create-button">create account</button>
        </div>
    </div>
    );
}



export default RegisterForm;