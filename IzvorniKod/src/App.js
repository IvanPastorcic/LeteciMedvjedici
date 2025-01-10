import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage/LoginPage";
import ReportPage from "./pages/ReportPage/ReportPage"; 
import HomePage from "./pages/HomePage/HomePage";
import MapPage from "./pages/MapPage/MapPage";
=======
import HomePageAuthorities from "./pages/HomePageAuthorities/HomePageAuthorities";
import ConfirmationScreen from "./pages/ConfirmationScreen/ConfirmationScreen";
import ReportNeedPage from "./pages/ReportNeedPage/ReportNeedPage";
import HomePageHumanitarian from "./pages/HomePageHumanitarian/HomePageHumanitarian";
import HomePageAdmin from "./pages/HomePageAdmin/HomePageAdmin";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/report" element={<ReportPage />} />
        <Route path="/confirmation" element={<ConfirmationScreen />} />
        <Route path="/report-need" element={<ReportNeedPage />} /> 

        <Route path="/home" element={<HomePage />} />
        <Route path="/map" element={<MapPage />} /

        <Route path="/homeauthorities" element={<HomePageAuthorities />} />
        <Route path="/homehumanitarian" element={<HomePageHumanitarian />} />
        <Route path="/homeadmin" element={<HomePageAdmin />} />
    
      </Routes>
    </Router>
  );
}

export default App;