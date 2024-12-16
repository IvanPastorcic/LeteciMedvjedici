import React, { useState, useEffect } from "react";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import { FaArrowLeft, FaFire, FaWater, FaBolt, FaMountain, FaHome } from "react-icons/fa";
import { useNavigate } from "react-router-dom"; 
import './ReportPage.css';
import axios from 'axios';
import Footer from "../../components/Footer/Footer";

function ReportPage() {
  const navigate = useNavigate();
  const [activeButton, setActiveButton] = useState(null);
  const [locationInput, setLocationInput] = useState(""); // Track user's input for location
  const [isLocationValid, setIsLocationValid] = useState(true); // Track validity of location
  const [description, setDescription] = useState(""); // Track short description
  const [locations, setLocations] = useState([]);
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); 

  useEffect(() => {   
    const fetchLocations = async () => {
      try {
        const response = await axios.get("https://safebear-backend.onrender.com/location/settlementnames"); 
        const locationNames = response.data.map(location => location.settlementName || location);
        setLocations(locationNames); 
        setLoading(false); 
      } catch (error) {
        console.error("Error fetching locations:", error);
        setError("Failed to load locations"); 
        setLoading(false); 
      }
    };
    fetchLocations(); 
  }, []); 

  // Log constants to see what data we have
  useEffect(() => {
    console.log("locations:", locations);
    console.log("loading:", loading);
    console.log("activeButton:", activeButton);
    console.log("locationInput:", locationInput);
    console.log("isLocationValid:", isLocationValid);
    console.log("description:", description);
    console.log("error:", error);
  }, [locations, loading, activeButton, locationInput, isLocationValid, description, error]); 

  const goBack = () => {
    navigate(-1); // Go back to the previous page
  };

  const handleButtonClick = (type) => {
    setActiveButton(type);
  };

  const handleLocationInputChange = (e) => {
    const value = e.target.value;
    setLocationInput(value);
    // Check if the input matches any location in the list
    setIsLocationValid(locations.includes(value));
  };

  const handleDescriptionChange = (e) => {
    setDescription(e.target.value); // Set the description state based on user input
  };

  const handleSubmit = async () => {
    // Log all constants before submission
    console.log("Before submitting report:");
    console.log("locationInput:", locationInput);
    console.log("isLocationValid:", isLocationValid);
    console.log("activeButton:", activeButton);
    console.log("description:", description);

    // Validate input before submitting
    if (!isLocationValid) {
      alert("Please enter a valid location from the list.");
      return;
    }

    const typeMapping = {
      fire: "WILDFIRE",
      earthquake: "EARTHQUAKE",
      flooding: "FLOOD",
      heavy_storm: "HURRICANE",
      landslide: "TORNADO",
    };

    const emergencyType = typeMapping[activeButton];

    if (!emergencyType) {
      alert("Please select an emergency type.");
      return;
    }

    if (!description) {
      alert("Please enter a description.");
      return;
    }

    try {
      const response = await axios.post("https://safebear-backend.onrender.com/reports/add", {
        settlementName: locationInput,
        disasterType: emergencyType,
        shortDescription: description,
      }, {
        withCredentials: true,
      });
      console.log("Report submitted:", response.data);
      alert("Report submitted successfully!");
      navigate("/home");
    } catch (error) {
      console.log("Error response:", error.response || error); // Log the full error response or message.
      console.error("Error submitting report:", error);
      alert("Failed to submit report.");
    }
  };

  return (
    <div>
      <AnonHeader />
      <div className="app-container">
      
        {/* Header */}
        <div className="header-container">
          <button className="back-button" onClick={goBack}>
            <FaArrowLeft /> Back
          </button>
          <h1 className="header-title">REPORT AN EMERGENCY:</h1>
        </div>

        {/* Content */}
        <div className="content-container">
          <h2 className="section-title">What happened?</h2>
          <div className="containerStyle">
            <button 
              className="iconContainerStyle"
              style={{backgroundColor: activeButton === "fire" ? "gray" : "white"}}
              onClick={() => handleButtonClick("fire")}
            >
              <FaFire size={50} />
              <p className="paragraphStyle">FIRE</p>
            </button>
            <button  
              className="iconContainerStyle"
              style={{backgroundColor: activeButton === "earthquake" ? "gray" : "white"}}
              onClick={() => handleButtonClick("earthquake")}
            >
              <FaHome size={50} />
              <p className="paragraphStyle">EARTHQUAKE</p>
            </button>
            <button 
              className="iconContainerStyle"
              style={{backgroundColor: activeButton === "flooding" ? "gray" : "white"}}
              onClick={() => handleButtonClick("flooding")}
            >
              <FaWater size={50} />
              <p className="paragraphStyle">FLOODING</p>
            </button>
            <button  
              className="iconContainerStyle"
              style={{backgroundColor: activeButton === "heavy_storm" ? "gray" : "white"}}
              onClick={() => handleButtonClick("heavy_storm")}
            >
              <FaBolt size={50} />
              <p className="paragraphStyle">HEAVY STORM</p>
            </button>
            <button 
              className="iconContainerStyle"
              style={{backgroundColor: activeButton === "landslide" ? "gray" : "white"}}
              onClick={() => handleButtonClick("landslide")}
            >
              <FaMountain size={50} />
              <p className="paragraphStyle">LANDSLIDE</p>
            </button>
          </div>

          <hr className="divider" />
          <h2 className="section-title">Where did it happen?</h2>
          <p>Please use correct capitalization. For Zagreb please choose one of the following: Brezovica, Črnomerec, Donja Dubrava, Donji Grad, Gornja Dubrava, Gornji Grad- Medvešćak, Maksimir, Novi Zagreb-istok, Novi Zagreb-zapad, Pešćenica-Žitnjak, Podsljeme (Šestine-Gračani-Markuševec), Podsused-Vrapče, Sesvete, Stenjevec, Trešnjevka-jug, Trešnjevka-sjever, Trnje </p>
          <div className="location-inputs" style={{ position: 'relative' }}>
            <input 
              type="text" 
              placeholder="Input location" 
              className="address-input"
              value={locationInput}
              onChange={handleLocationInputChange}
            />
            {!isLocationValid && <p className="error-text">Location not found. Please enter a valid location.</p>}
          </div>

          <hr className="divider" />
          
          <div className="containerStyle">
            <input 
              type="text" 
              placeholder="Input short description" 
              className="inputField" 
              value={description} 
              onChange={handleDescriptionChange}
            />
            <button className="submit-button" onClick={handleSubmit}>SUBMIT REPORT</button>
          </div>
          
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ReportPage;
