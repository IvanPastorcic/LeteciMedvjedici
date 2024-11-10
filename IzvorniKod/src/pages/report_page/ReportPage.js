import React from 'react';
import './ReportPage.css';




import { FaFire, FaWater, FaBolt, FaMountain, FaMapMarkerAlt, FaCamera } from 'react-icons/fa';
import { MdOutlineBrokenImage } from 'react-icons/md';

function ReportPage(){


  const containerStyle = {
    display: 'flex',
    justifyContent: 'space-around',
    alignItems: 'center',
    marginTop: '20px',
  };

  const iconContainerStyle = {
    textAlign: 'center',
  };

  const paragraphStyle = {
    marginTop: '5px',
    fontSize: '14px',
  };

  return (
    <div className="app-container">
      <header className="header">
        <p className="header-small-text">Need help? Want to report an emergency? Use SafeBear.</p>
      </header>

      {/* Odvojeni naslov */}
      <h1 className="header-title">REPORT AN EMERGENCY:</h1>

      {/* Donji kontejner */}
      <div className="content-container">
        <h2 className="section-title">What happened?</h2>
        <div style={containerStyle}>
          <button style={iconContainerStyle} onClick={() => alert('Fire reported!')}>
            <FaFire size={50} />
            <p style={paragraphStyle}>FIRE</p>
          </button>
          <button style={iconContainerStyle} onClick={() => alert('Earthquake reported!')}>
            <MdOutlineBrokenImage size={50} color="gray" />
            <p style={paragraphStyle}>EARTHQUAKE</p>
          </button>
          <button style={iconContainerStyle} onClick={() => alert('Flooding reported!')}>
            <FaWater size={50} />
            <p style={paragraphStyle}>FLOODING</p>
          </button>
          <button style={iconContainerStyle} onClick={() => alert('Heavy storm reported!')}>
            <FaBolt size={50} />
            <p style={paragraphStyle}>HEAVY STORM</p>
          </button>
          <button style={iconContainerStyle} onClick={() => alert('Landslide reported!')}>
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
  );
}


export default ReportPage;

