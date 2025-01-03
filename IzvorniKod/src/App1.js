import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
//import './pages/report_page/ReportPage';




import ReportPage from './pages/report_page/ReportPage';



function App() {
  return (
    <Router>
      <div className="app-container">
        <Routes>
          
        <Route path="/ReportPage" element={<ReportPage />} />

        </Routes>
      </div>
    </Router>
  );
}

export default App;
