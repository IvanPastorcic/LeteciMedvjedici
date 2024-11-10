import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage/LoginPage";
import ReportPage from "./pages/ReportPage/ReportPage"; 


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/report" element={<ReportPage />} />
    
      </Routes>
    </Router>
  );
}

export default App;