import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage/LoginPage";
import ReportPage from "./pages/ReportPage/ReportPage"; 
import HomePage from "./pages/HomePage/HomePage";
import MapPage from "./pages/MapPage/MapPage";

import HomePageAuthorities from "./pages/HomePageAuthorities/HomePageAuthorities";
import ConfirmationScreen from "./pages/ConfirmationScreen/ConfirmationScreen";
import ReportNeedPage from "./pages/ReportNeedPage/ReportNeedPage";
import HomePageHumanitarian from "./pages/HomePageHumanitarian/HomePageHumanitarian";

import AddNewAction from "./pages/AddNewAction/AddNewAction";
import ManageResource from "./pages/ManageResource/ManageResource";

import HomePageAdmin from "./pages/HomePageAdmin/HomePageAdmin";
import ReportOpen from "./pages/ReportOpen/ReportOpen";
import NeedsOpen from "./pages/NeedsOpen/NeedsOpen";
import ProfilePage from "./pages/ProfilePage/ProfilePage";


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} /> 
        <Route path="/report" element={<ReportPage />} />
        <Route path="/confirmation" element={<ConfirmationScreen />} />
        <Route path="/report-need" element={<ReportNeedPage />} /> 
        <Route path="/reportopen" element={<ReportOpen />} /> 

        <Route path="/profile" element={<ProfilePage />} /> 

        <Route path="/home" element={<HomePage />} />
        <Route path="/map" element={<MapPage />} />

        <Route path="/homeauthorities" element={<HomePageAuthorities />} />
        <Route path="/homehumanitarian" element={<HomePageHumanitarian />} />
        <Route path="/needsopen" element={<NeedsOpen />} /> 


        <Route path="/addnewaction" element={<AddNewAction />} />
        <Route path="/manageresource" element={<ManageResource />} />

        <Route path="/homeadmin" element={<HomePageAdmin />} />

    

      </Routes>
    </Router>
  );
}

export default App;