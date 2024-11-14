import React, { useState } from "react";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import { FaArrowLeft, FaFire, FaWater, FaBolt, FaMountain, FaHome } from "react-icons/fa";
import { useNavigate } from "react-router-dom"; 
import './ReportPage.css';
import axios from 'axios';

function ReportPage(){
  const navigate = useNavigate();
  const [activeButton, setActiveButton] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);

  // Simulirana baza podataka s lokacijama
  const [locations, setLocations] = useState([]);
  useEffect(() => {
        
    const fetchLocations = async () => {
        try {
            const response = await axios.get("http://localhost:8081/location/settlement"); 
            setReports(response.data); 
            setLoading(false); 
        } catch (error) {
            console.error("Error fetching locations:", error);
            setError("Failed to load locations"); 
            setLoading(false); 
        }
    };
    fetchReports(); 
}, []); 


  const goBack = () => {
    navigate(-1); // Go back to the previous page
  };

  const handleButtonClick = (type) => {
    setActiveButton(type);
  };

  const handleInputChange = (e) => {
    const query = e.target.value;
    setSearchQuery(query);

    if (query) {
      const matches = locations.filter(location => 
        location.toLowerCase().includes(query.toLowerCase())
      );
      setSearchResults(matches.length > 0 ? matches : ["No matching results found"]);
    } else {
      setSearchResults([]);
    }
  };

  const handleSuggestionClick = (suggestion) => {
    setSearchQuery(suggestion);
    setSearchResults([]); // Zatvori padajući izbornik nakon odabira
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
        <div className="location-inputs" style={{ position: 'relative' }}>
          <input 
            type="text" 
            placeholder="Input location" 
            className="address-input" 
            value={searchQuery}
            onChange={handleInputChange}
          />
          {searchResults.length > 0 && (
            <ul className="search-dropdown">
              {searchResults.map((result, index) => (
                <li key={index} className="search-item" onClick={() => handleSuggestionClick(result)}>
                  {result}
                </li>
              ))}
            </ul>
          )}
        </div>

        <hr className="divider" />
        
        {/* Submit button with original position */}
        <button className="submit-button">SUBMIT REPORT</button>
        
      </div>
    </div>
    </div>
  );
}

export default ReportPage;
