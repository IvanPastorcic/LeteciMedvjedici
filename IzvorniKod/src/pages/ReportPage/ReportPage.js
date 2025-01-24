import React, { useState, useEffect } from "react";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import { FaFire, FaWater, FaBolt, FaMountain, FaHome } from "react-icons/fa";
import { useNavigate } from "react-router-dom"; 
import './ReportPage.css';
import axios from 'axios';
import Footer from "../../components/Footer/Footer";
import BackButton from "../../components/BackButton/BackButton";

function ReportPage() {
  const navigate = useNavigate();
  const [activeButton, setActiveButton] = useState(null);
  const [locationInput, setLocationInput] = useState(""); 
  const [isLocationValid, setIsLocationValid] = useState(true);
  const [description, setDescription] = useState(""); 
  const [locations, setLocations] = useState([]);
  const [filteredLocations, setFilteredLocations] = useState([]); 
  const [loading, setLoading] = useState(true); 
  const [latitude, setLatitude] = useState(null); 
  const [longitude, setLongitude] = useState(null); 
  const [isUsingCurrentLocation, setIsUsingCurrentLocation] = useState(false); 

  useEffect(() => {   
    const fetchLocations = async () => {
      try {
        const response = await axios.get("http://localhost:8081/location/settlementnames"); 
        const locationNames = response.data.map(location => location.settlementName || location);
        setLocations(locationNames);
        setFilteredLocations(locationNames); 
        setLoading(false); 
      } catch (error) {
        setLoading(false);
      }
    };
    fetchLocations(); 
    
    // Ako korisnik ne koristi trenutnu lokaciju, onda dohvatiti geolokaciju
    if (navigator.geolocation && !isUsingCurrentLocation) {
      navigator.geolocation.getCurrentPosition(
        position => {
          setLatitude(position.coords.latitude);
          setLongitude(position.coords.longitude);
        },
        error => {
          alert("Geolocation not available. Please manually input location.");
        }
      );
    } else if (navigator.geolocation && isUsingCurrentLocation) {
      // Ako je trenutna lokacija već postavljena, ne dohvaćamo je ponovo
      setLatitude(latitude);
      setLongitude(longitude);
    }
  }, [isUsingCurrentLocation]);

  const goBack = () => {
    navigate(-1); 
  };

  const handleButtonClick = (type) => {
    setActiveButton(type);
  };

  const handleLocationInputChange = (e) => {
    const value = e.target.value;
    setLocationInput(value);
    const sanitizedValue = value.trimEnd().toLowerCase();
    const filtered = locations.filter(location => 
      location.toLowerCase().includes(sanitizedValue)
    );
    setFilteredLocations(filtered);
    setIsLocationValid(filtered.length > 0 || value.trim() === ""); // Ako korisnik unese bilo koji tekst, filtriraj
  };

  const handleDescriptionChange = (e) => {
    setDescription(e.target.value); 
  };
/*
  const handleSubmit = async () => {
    // Sanitiziraj lokaciju
    const sanitizedLocation = locationInput.trim();

    // Provjera vrste hitne situacije
    const typeMapping = {
        fire: "WILDFIRE",
        earthquake: "EARTHQUAKE",
        flooding: "FLOOD",
        heavy_storm: "HURRICANE",
        landslide: "LANDSLIDE",
    };

    const emergencyType = typeMapping[activeButton];

    if (!emergencyType) {
        alert("Please select an emergency type.");
        return;
    }

    // Provjera opisa
    if (!description.trim()) {
        alert("Please enter a description.");
        return;
    }

    // Podaci za izvještaj
    const reportData = {
        settlementName: isUsingCurrentLocation ? "Current Location" : sanitizedLocation, // Ako je "use current location", koristi "Current Location"
        disasterType: emergencyType,
        shortDescription: description.trim(),
        coordinates: latitude + ", " + longitude, 
        photo: "", 
    };

    try {
        // Slanje izvještaja
        const response = await axios.post("http://localhost:8081/reports/add", reportData, {
            withCredentials: true,
        });

        // Preusmjeri na stranicu s potvrdom
        const reportId = response.data.id;
        localStorage.setItem("reportId", reportId);
        navigate(`/confirmation/${reportId}`);
    } catch (error) {
        alert("Failed to submit report.");
        console.error("Error submitting report:", error);
    }
};*/
const handleSubmit = async () => {
  const sanitizedLocation = locationInput.trim();

  const typeMapping = {
      fire: "WILDFIRE",
      earthquake: "EARTHQUAKE",
      flooding: "FLOOD",
      heavy_storm: "HURRICANE",
      landslide: "LANDSLIDE",
  };

  const emergencyType = typeMapping[activeButton];

  if (!emergencyType) {
      alert("Please select an emergency type.");
      return;
  }

  if (!description.trim()) {
      alert("Please enter a description.");
      return;
  }

  const reportData = {
      settlementName: isUsingCurrentLocation ? "Current Location" : sanitizedLocation,
      disasterType: emergencyType,
      shortDescription: description.trim(),
      coordinates: latitude + ", " + longitude, 
      photo: "", 
  };

  try {
    const response = await axios.post("http://localhost:8081/reports/add", reportData, {
        withCredentials: true,
    });

    const reportId = response.data.id;
    localStorage.setItem("reportId", reportId);
    navigate(`/confirmation/${reportId}`);
  } catch (error) {
    console.error("Error submitting report:", error);

    if (error.response) {
      // Attempt to extract the error message from various possible formats
      let errorMessage = "Failed to submit report.";

      if (typeof error.response.data === "string") {
        errorMessage = error.response.data; // Plain text error response
      } else if (error.response.data && error.response.data.message) {
        errorMessage = error.response.data.message; // Standard API response format
      } else if (error.response.data && typeof error.response.data === "object") {
        errorMessage = JSON.stringify(error.response.data); // Fallback to stringifying object
      }

      alert(`Error: ${errorMessage}`);
    } else if (error.request) {
      alert("No response from server. Please try again later.");
    } else {
      alert("An unexpected error occurred.");
    }
  }
};




const useCurrentLocation = () => {
  if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
          position => {
              setLatitude(position.coords.latitude);
              setLongitude(position.coords.longitude);
              setIsUsingCurrentLocation(true);
              setLocationInput(""); // Resetiraj unos lokacije kada se koristi trenutna lokacija
              alert("Your current location has been set.");
          },
          error => {
              alert("Geolocation failed. Please enable location services.");
          }
      );
  } else {
      alert("Geolocation is not supported by your browser.");
  }
};

useEffect(() => {
  if (navigator.geolocation && !isUsingCurrentLocation) {
      navigator.geolocation.getCurrentPosition(
          position => {
              setLatitude(position.coords.latitude);
              setLongitude(position.coords.longitude);
          },
          error => {
              alert("Geolocation not available. Please manually input location.");
          }
      );
  }
}, [isUsingCurrentLocation]);


  return (
    <div>
      <AnonHeader />
      <div className="report-app-container">
        <div className="report-header-container">
          <BackButton /> 
          <h1 className="report-header-title">REPORT AN EMERGENCY:</h1>
        </div>
        <div className="report-content-container">
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
          <hr className="report-divider" />
          <h2 className="section-title">Where did it happen?</h2>
          <div className="location-inputs">
            {isUsingCurrentLocation ? (
              <p>Location: Current Location</p>
            ) : (
              <>
                <input 
                  type="text" 
                  placeholder="Input location" 
                  className="address-input"
                  value={locationInput}
                  onChange={handleLocationInputChange}
                  disabled={isUsingCurrentLocation} // Onemogući unos kada je trenutna lokacija aktivna
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
              </>
            )}
          </div>
          <button className="use-location-button" onClick={useCurrentLocation}>
            Use My Current Location
          </button>
          <hr className="report-divider" />
          <div className="containerStylebottom">
            <h2 className="section-title-last">Add a short description</h2>
            <input 
              type="text" 
              placeholder="Input short description" 
              className="inputField" 
              value={description} 
              onChange={handleDescriptionChange}
            />
            <button className="report-submit-button" onClick={handleSubmit}>SUBMIT REPORT</button>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ReportPage;
