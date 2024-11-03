import React from "react";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from "./components/Login";
import Register from "./components/Register";
import RegisterForm from "./components/RegisterForm";
import Anonymous from "./components/Anonymous";
import "./HomePage.css";


function HomePage() {
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

export default HomePage;
