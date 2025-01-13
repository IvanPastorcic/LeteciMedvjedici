import React, { useState, useEffect } from "react";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import { useNavigate } from "react-router-dom"; 
import './ReportNeedPage.css';
import axios from "axios";

function ReportNeedPage() {
    const [needs, setNeeds] = useState({});
    const reportId = localStorage.getItem("reportId")
    const navigate = useNavigate();
  
    const needUnits = {
      water: "liters",
      food: "people",
      shelter: "people",
      first_aid_kit: "kits",
      heaters: "heaters",
      sand_bags: "liters",
    };
  
    const handleNeedClick = (need) => {
      setNeeds((prevNeeds) => ({
        ...prevNeeds,
        [need]: prevNeeds[need] || "", 
      }));
    };
  
    const handleQuantityChange = (need, value) => {
      setNeeds((prevNeeds) => ({
        ...prevNeeds,
        [need]: value,
      }));
    };
  
    /*
    const handleSubmit = () => {
      const submittedNeeds = Object.entries(needs).filter(
        ([, quantity]) => quantity
      );
  
      if (submittedNeeds.length === 0) {
        alert("Please specify at least one need.");
        return;
      }
  
      const needsToSubmit = submittedNeeds.map(([need, quantity]) => ({
        type: need,
        quantity: parseInt(quantity, 10),
        unit: needUnits[need],
      }));

      //za slanje info u backend
/*       try {
        
        const response = await axios.post("http://localhost:8081/needs/add", {
          needs: needsToSubmit, 
    
        console.log("Submitted successfully:", response.data);
        alert("Needs submitted successfully!");
    
        
        navigate("/home");
      } catch (error) {
        console.error("Error submitting needs:", error);
        alert("Failed to submit needs. Please try again.");
      } 
  
      console.log("Submitting needs:", needsToSubmit);
      alert("Needs submitted successfully!");
      navigate("/home");
    };*/

    /*
    const handleSubmit = async () => {
      const submittedNeeds = Object.entries(needs).filter(
        ([, quantity]) => quantity
      );
    
      if (submittedNeeds.length === 0) {
        alert("Please specify at least one need.");
        return;
      }
    
      // Prepare the data to send to the backend
      const needsToSubmit = submittedNeeds.map(([need, quantity]) => ({
        type: need.toUpperCase(),  // Ensure the type matches your backend's enum format
        location: "Some Location", // Replace with actual location input if needed //DODATI OVO PREKO INPUT FIELDA
        quantity: parseInt(quantity, 10),  // Ensure quantity is an integer
      }));
    
      try {
        // Make the POST request to your backend
        const response = await axios.post("http://localhost:8081/needs/add", {
           needsToSubmit,
        }, { withCredentials: true,});
    
        console.log("Submitted successfully:", response.data);
        alert("Needs submitted successfully!");
        navigate("/home");
    
      } catch (error) {
        console.error("Error submitting needs:", error);
        alert("Failed to submit needs. Please try again.");
      }
    };*/

    const handleSubmit = async () => {
      const submittedNeeds = Object.entries(needs).filter(
        ([, quantity]) => quantity
      );
    
      if (submittedNeeds.length === 0) {
        alert("Please specify at least one need.");
        return;
      }
    
      // Prepare the data to send to the backend
      const needsToSubmit = submittedNeeds.map(([need, quantity]) => ({
        type: need.toUpperCase(),
        location: "Some Location", // Replace with actual input
        quantity: parseInt(quantity, 10),
        id: reportId,
      }));
    
      try {
        // Send the array directly
        const response = await axios.post(
          "http://localhost:8081/needs/add",
          needsToSubmit,
          { withCredentials: true }
        );
    
        console.log("Submitted successfully:", response.data);
        console.log(reportId);
        alert("Needs submitted successfully!");
        navigate("/home");
      } catch (error) {
        console.error("Error submitting needs:", error);
        console.log(reportId);
        alert("Failed to submit needs. Please try again.");
      }
    };
    
  
    return (
        <div className="report-need-page">
            <AnonHeader />
            <h1>Report a Need</h1>
            <p>Select the needs and specify the quantities:</p>
            <div className="need-options">
            {Object.keys(needUnits).map((need) => (
                <div key={need} className="need-item">
                <button
                    className={`need-button ${
                    needs[need] !== undefined ? "active" : ""
                    }`}
                    onClick={() => handleNeedClick(need)}
                >
                    {need.replace("_", " ").toUpperCase()}
                </button>
                {needs[need] !== undefined && (
                    <input
                    type="number"
                    placeholder={`Enter quantity in ${needUnits[need]}`}
                    value={needs[need]}
                    onChange={(e) => handleQuantityChange(need, e.target.value)}
                    className="quantity-input"
                    />
                )}
                </div>
            ))}
            </div>
           
            <button className="submit-button" onClick={handleSubmit}>
            Submit Needs
            </button>
        </div>
      
    );
  }
  
  export default ReportNeedPage;
