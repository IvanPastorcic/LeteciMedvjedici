import React from "react";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from "./components/Login/Login";
import Register from "./components/Register/Register";
import Anonymous from "./components/Anonymous/Anonymous";
import AnonHeader from "./components/AnonHeader/AnonHeader";
import FirstInfoFrame from "./components/FirstInfoFrame/FirstInfoFrame";
import Footer from "./components/Footer/Footer";
import "./LoginPage.css";


function LoginPage() {
  return (
    <Router>
      <div className="LoginPageContainer">
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
        </Routes>
        <Footer /> 
      </div>
    </Router>
  );
}

export default LoginPage;