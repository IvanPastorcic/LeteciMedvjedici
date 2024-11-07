import React from "react";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from "./components/Login/Login";
import Register from "./components/Register/Register";
import RegisterForm from "./components/RegisterForm/RegisterForm";
import Anonymous from "./components/Anonymous/Anonymous";
import AnonHeader from "./components/AnonHeader/AnonHeader";
import "./LoginPage.css";


function LoginPage() {
  return (
    <Router>
      <div>
        <AnonHeader />
        <Routes>
          <Route 
            path="/" 
            element={
              <div className="FormContainer">
                <Login />
                <div className="Separator"></div>
                <Register />
              </div>
            } 
          />
          <Route path="/register" element={<RegisterForm />} />
        </Routes>
        <Anonymous />
      </div>
    </Router>
  );
}

export default LoginPage;