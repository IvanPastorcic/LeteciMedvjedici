import React, { useState, useEffect } from "react";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import BackButton from "../../components/BackButton/BackButton";
import { useNavigate } from "react-router-dom"; 
import './ReportNeedPage.css';
import axios from "axios";

function ReportNeedPage() {
  const [needs, setNeeds] = useState({});
  const [locationInput, setLocationInput] = useState("");
  const [isLocationValid, setIsLocationValid] = useState(true);
  const [locations, setLocations] = useState([]);
  const [filteredLocations, setFilteredLocations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
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

    useEffect(() => {
      const fetchLocations = async () => {
        try {
          const response = await axios.get("https://safebear-backend.onrender.com/location/settlementnames");
          const locationNames = response.data.map(location => location.settlementName || location);
          setLocations(locationNames);
          setFilteredLocations(locationNames);
          setLoading(false);
        } catch (error) {
          setError("Failed to load locations");
          setLoading(false);
        }
      };
      fetchLocations();
    }, []);
  
    const handleNeedClick = (need) => {
      setNeeds((prevNeeds) => {
        if (prevNeeds[need] !== undefined) {
          const updatedNeeds = { ...prevNeeds };
          delete updatedNeeds[need]; 
          return updatedNeeds;
        } else {
          return {
            ...prevNeeds,
            [need]: "", 
          };
        }
      });
    };
  
    const handleQuantityChange = (need, value) => {
      setNeeds((prevNeeds) => ({
        ...prevNeeds,
        [need]: value,
      }));
    };
  
   


    const handleLocationInputChange = (e) => {
      const value = e.target.value;
      setLocationInput(value);
      const sanitizedValue = value.trimEnd().toLowerCase();
      const filtered = locations.filter(location =>
        location.toLowerCase().includes(sanitizedValue)
      );
      setFilteredLocations(filtered);
      setIsLocationValid(filtered.length > 0);
    };




    const handleSubmit = async () => {
      const sanitizedLocation = locationInput.trim();
      if (!isLocationValid || !locations.some(location => location.trim().toLowerCase() === sanitizedLocation.toLowerCase())) {
        alert("Please enter a valid location from the list.");
        return;
      }
  
      const submittedNeeds = Object.entries(needs).filter(
        ([, quantity]) => quantity
      );
  
      if (submittedNeeds.length === 0) {
        alert("Please specify at least one need.");
        return;
      }
  
      const needsToSubmit = submittedNeeds.map(([need, quantity]) => ({
        type: need.toUpperCase(),
        location: sanitizedLocation,
        quantity: parseInt(quantity, 10),
        id: reportId,
      }));
  
      try {
        const response = await axios.post(
          "https://safebear-backend.onrender.com/needs/add",
          needsToSubmit,
          { withCredentials: true }
        );
  
        console.log("Submitted successfully:", response.data);
        alert("Needs submitted successfully!");
        navigate("/home");
      } catch (error) {
        console.error("Error submitting needs:", error);
        alert("Failed to submit needs. Please try again.");
      }
    };
    
  
    return (
        <div className="report-need-page">
            <AnonHeader />
            <div className="backbutton-report-need" >
              <BackButton /> 
            </div>
            
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

            <p2>Where are the needs required?</p2>
            <div className="location-inputs-reportneed">
              <input
                type="text"
                placeholder="Input location"
                className="address-input-reportneed"
                value={locationInput}
                onChange={handleLocationInputChange}
              />
              {!isLocationValid && <p className="error-text">Location not found. Please enter a valid location.</p>}
              {filteredLocations.length > 0 && locationInput && (
                <div className="location-dropdown">
                  {filteredLocations.map((location, index) => (
                    <div
                      key={index}
                      className="location-dropdown-item"
                      onClick={() => {
                        setLocationInput(location);
                        setIsLocationValid(true);
                        setFilteredLocations([]);
                      }}
                    >
                      {location}
                    </div>
                  ))}
                </div>
              )}
            </div>
           
            <button className="submit-button" onClick={handleSubmit}>
            Submit Needs
            </button>
        </div>
      
    );
  }
  
  export default ReportNeedPage;
