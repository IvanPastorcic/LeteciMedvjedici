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

    function validateUsername(username) {
        if (username.length <= 3) {
            return { isValid: false, error: "Username must be longer than 3 characters." };
        }
        
        const startsWithLetter = /^[a-zA-Z]/.test(username); 
        if (!startsWithLetter) {
            return { isValid: false, error: "Username must start with a letter." };
        }
    
        return { isValid: true }; 
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

        if (!validateEmail(registerForm.email)) {
            setError("Unacceptable e-mail");
            return;
        }

        const usernameValidation = validateUsername(registerForm.username);
        if (!usernameValidation.isValid) {
            setError(usernameValidation.error);
            return;
        }

        const passwordValidation = validatePassword(registerForm.password);
        if (!passwordValidation.isValid) {
            setError(passwordValidation.error);
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
        <div className="register-container">
            <h3 className="form-title">Register</h3>
    
            <div className="register-form">
                <form onSubmit={onSubmit}>
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

                    {error && <p style={{ color: "red",
                                        fontSize: "12px",
                                        textAlign: "center"
                     }}>{error}</p>}

                    <div className="separator">
                        <hr />
                        <span>or</span>
                        <hr />
                    </div>

                    <button type="submit" className="google-button">Use your google login</button>
                </form>

                <div className="create-account">
                    <p>All set? Click 'create account' <br /> and wait for confirmation.</p>
                    <button type="button" className="create-button" 
                        onClick={onSubmit}>create account</button>
                </div>
            </div>
    </div>

    );
}



export default RegisterForm;