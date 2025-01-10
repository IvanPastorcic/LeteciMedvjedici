import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage/LoginPage";
import ReportPage from "./pages/ReportPage/ReportPage"; 
import HomePage from "./pages/HomePage/HomePage";
import MapPage from "./pages/MapPage/MapPage";
import ReportOpen from "./pages/ReportOpen/ReportOpen";


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/report" element={<ReportPage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/map" element={<MapPage />} />
        <Route path="/reportopen" element={<ReportOpen />} />
    
      </Routes>
    </Router>
  );
}

export default App;