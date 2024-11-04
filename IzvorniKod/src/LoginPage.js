import React from "react";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from "./components/Login/Login";
import Register from "./components/Register/Register";
import RegisterForm from "./components/RegisterForm/RegisterForm";
import Anonymous from "./components/Anonymous/Anonymous";
import "./LoginPage.css";


function LoginPage() {
    return (
      <Router>
      <Routes>
        <Route 
          path="/" 
          element={
            <div>
              <div className="FormContainer">
                <Login />
                <div className="Separator"></div>
                <Register />
              </div>
              <Anonymous />
            </div>
          } 
        />
        <Route path="/register" element={<RegisterForm />} />
      </Routes>
    </Router>
    );
}

export default LoginPage;
