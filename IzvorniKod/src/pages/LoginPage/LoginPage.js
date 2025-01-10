import React from "react";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import FirstInfoFrame from "../../components/FirstInfoFrame/FirstInfoFrame";
import Footer from "../../components/Footer/Footer";
import Login from "../../components/Login/Login";
import Register from "../../components/Register/Register";
import Anonymous from "../../components/Anonymous/Anonymous";
import "./LoginPage.css";

function LoginPage() {
  return (
    <div className="LoginPageContainer">
      <AnonHeader />
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
          {/*<Anonymous />*/}
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default LoginPage;
