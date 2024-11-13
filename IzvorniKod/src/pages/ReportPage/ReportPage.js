import React, { useState } from "react";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import { FaArrowLeft, FaFire, FaWater, FaBolt, FaMountain, FaMapMarkerAlt, FaCamera, FaHome } from "react-icons/fa";
import { MdOutlineBrokenImage } from "react-icons/md";
import { useNavigate } from "react-router-dom"; 
import './ReportPage.css';

function ReportPage(){
  const navigate = useNavigate();
  const [activeButton, setActiveButton] = useState(null);

  const goBack = () => {
    navigate(-1); // Go back to the previous page
  };

  const handleButtonClick = (type) => {
    setActiveButton(type);
  };

  const containerStyle = {
    display: 'flex',
    justifyContent: 'space-around',
    alignItems: 'center',
    marginTop: '20px',
  };

  const iconContainerStyle = {
    textAlign: 'center',
    padding: '10px',
    borderRadius: '5px',
    cursor: 'pointer',
  };

  const paragraphStyle = {
    marginTop: '5px',
    fontSize: '14px',
  };

  return (
    <div>
      <AnonHeader />
      <div className="app-container">
      
      {/* Odvojeni naslov */}
      <div className="header-container">
          <button className="back-button" onClick={goBack}>
            <FaArrowLeft /> Back
          </button>
          <h1 className="header-title">REPORT AN EMERGENCY:</h1>
      </div>

      {/* Donji kontejner */}
      <div className="content-container">
        <h2 className="section-title">What happened?</h2>
        <div style={containerStyle}>
          <button 
            style={{
              ...iconContainerStyle, 
              backgroundColor: activeButton === "fire" ? "gray" : "white"
            }}
            onClick={() => handleButtonClick("fire")}
          >
            <FaFire size={50} />
            <p style={paragraphStyle}>FIRE</p>
          </button>
          <button 
            style={{
              ...iconContainerStyle, 
              backgroundColor: activeButton === "earthquake" ? "gray" : "white"
            }}
            onClick={() => handleButtonClick("earthquake")}
          >
            <FaHome size={50}  />
            <p style={paragraphStyle}>EARTHQUAKE</p>
          </button>
          <button 
            style={{
              ...iconContainerStyle, 
              backgroundColor: activeButton === "flooding" ? "gray" : "white"
            }}
            onClick={() => handleButtonClick("flooding")}
          >
            <FaWater size={50} />
            <p style={paragraphStyle}>FLOODING</p>
          </button>
          <button 
            style={{
              ...iconContainerStyle, 
              backgroundColor: activeButton === "heavy_storm" ? "gray" : "white"
            }}
            onClick={() => handleButtonClick("heavy_storm")}
          >
            <FaBolt size={50} />
            <p style={paragraphStyle}>HEAVY STORM</p>
          </button>
          <button 
            style={{
              ...iconContainerStyle, 
              backgroundColor: activeButton === "landslide" ? "gray" : "white"
            }}
            onClick={() => handleButtonClick("landslide")}
          >
            <FaMountain size={50} />
            <p style={paragraphStyle}>LANDSLIDE</p>
          </button>
        </div>

        <hr className="divider" />
        <h2 className="section-title">Where did it happen?</h2>
        <div className="location-inputs">
          <button className="location-button">
            <FaMapMarkerAlt /> At my location
          </button>
          <input type="text" placeholder="Somewhere else: input address" className="address-input" />
        </div>
        <hr className="divider" />
        <div className="footer-section">
          <div className="optional-section">
            <p>OPTIONAL: <span>add a short description and photos for more information.</span></p>
            <div className="description-input">
              <FaCamera size={30} className="camera-icon" />
              <input type="text" placeholder="Add description here..." />
            </div>
          </div>
          <button className="submit-button">SUBMIT REPORT</button>
        </div>
      </div>
    </div>
    </div>
  );
}

export default ReportPage;
