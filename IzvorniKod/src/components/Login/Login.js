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

    function validatePassword(password) {
        if (password.length <= 8) {
            return { isValid: false, error: "Password must be longer than 8 characters." };
        }
    
        const hasUpperCase = /[A-Z]/.test(password);
        if (!hasUpperCase) {
            return { isValid: false, error: "Password must contain at least one uppercase letter." };
        }
    
        const hasLowerCase = /[a-z]/.test(password);  
        if (!hasLowerCase) {
            return { isValid: false, error: "Password must contain at least one lowercase letter." };
        }
    
        const hasNumber = /[0-9]/.test(password);
        if (!hasNumber) {
            return { isValid: false, error: "Password must contain at least one number." };
        }
    
        return { isValid: true };  
    }

    function onSubmit(e){
        e.preventDefault();
        setError("");

        if (!validateEmail(loginForm.email)) {
            setError("Incorrect e-mail");
            return;
        }

        const passwordValidation = validatePassword(loginForm.password);
        if (!passwordValidation.isValid) {
            setError(passwordValidation.error);
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
                 {/*
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

                 {error && <p style={{ color: "red",
                                        fontSize: "14px",
                                        textAlign: "center"
                     }}>{error}</p>}
                
                <button type="submit">Log in</button>

                <div className="separator">
                <hr />
                
                <hr />
            </div>
*/}
            <button type="submit">Use your google login</button>
            </form>
        </div>
    )
}



export default Login;