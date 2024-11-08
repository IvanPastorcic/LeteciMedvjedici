import React from "react";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from "./components/Login/Login";
import Register from "./components/Register/Register";
import RegisterForm from "./components/RegisterForm/RegisterForm";
import Anonymous from "./components/Anonymous/Anonymous";
import AnonHeader from "./components/AnonHeader/AnonHeader";
import FirstInfoFrame from "./components/FirstInfoFrame/FirstInfoFrame";
import Footer from "./components/Footer/Footer";
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
              <div className="PageBody">
                
                <div className="LeftSection">
                  
                    <FirstInfoFrame />
                  
                </div>

                
                <div className="RightSection">
                  <div className="LoginRegisterBox">
                    <Login />
                    <div className="Separator"></div>       
                    <Register />
                  </div>
                  <Anonymous />
                  
                </div>
              </div>
            }
          />
          <Route path="/register" element={<RegisterForm />} />
        </Routes>
        <Footer /> 
      </div>
    </Router>
  );
}

export default LoginPage;