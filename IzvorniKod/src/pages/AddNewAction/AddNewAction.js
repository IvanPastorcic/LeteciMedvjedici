

import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./AddNewAction.css"; 
import AnonHeader from '../../components/AnonHeader/AnonHeader';
import Footer from "../../components/Footer/Footer";
import axios from 'axios';

const AddNewAction = () => {
  const navigate = useNavigate();
  const [actionName, setActionName] = useState("");
  const [locationInput, setLocationInput] = useState("");
  const [description, setDescription] = useState("");
  const [isLocationValid, setIsLocationValid] = useState(true);
  const [locations, setLocations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const response = await axios.get("http://localhost:8081/location/settlementnames");
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

  const handleLocationInputChange = (e) => {
    const value = e.target.value;
    setLocationInput(value);
    setIsLocationValid(locations.includes(value));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!actionName || !locationInput || !description) {
      alert("Please fill out all fields.");
      return;
    }

    if (!isLocationValid) {
      alert("Please enter a valid location.");
      return;
    }

    const payload = {
      actionName: actionName,
      settlementName: locationInput,
      description: description,
    };

    try {
      const response = await axios.post("http://localhost:8081/actions/add", payload, {withCredentials: true});
      alert("Action successfully added!");
      navigate("/home");
    } catch (error) {
      console.error("Error adding action:", error);
      alert("Failed to add action. Please try again.");
    }
  };

  return (
    <div className="page-container">
      <AnonHeader />
      <div className="header-container">
        <button className="back-button" onClick={() => navigate('/home')}>
          &lt; Back to newsfeed
        </button>
        <h1>ADD NEW ACTION</h1>
      </div>

      <div className="add-action-container">
        <form className="add-action-form" onSubmit={handleSubmit}>
          <div className="left-column">
            <label htmlFor="actionName">Add a name:</label>
            <input
              id="actionName"
              placeholder="Action name"
              className="description-input"
              type="text"
              value={actionName}
              onChange={(e) => setActionName(e.target.value)}
            />

            <p className="section-title">Location</p>
            <p>Please use correct capitalization. For Zagreb please choose one of the following: Brezovica, Črnomerec, Donja Dubrava, Donji Grad, Gornja Dubrava, Gornji Grad- Medvešćak, Maksimir, Novi Zagreb-istok, Novi Zagreb-zapad, Pešćenica-Žitnjak, Podsljeme (Šestine-Gračani-Markuševec), Podsused-Vrapče, Sesvete, Stenjevec, Trešnjevka-jug, Trešnjevka-sjever, Trnje</p>
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
          </div>

          <div className="right-column">
            <div className="form-section description-section">
              <label htmlFor="description">ADD A DESCRIPTION</label>
              <textarea
                id="description"
                placeholder="Add description"
                className="description-input"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              ></textarea>
            </div>
          </div>

          <div className="form-section submit-section">
            <button type="submit" className="submit-button">Submit</button>
          </div>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default AddNewAction;


/*import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./AddNewAction.css"; 
import AnonHeader from '../../components/AnonHeader/AnonHeader';
import Footer from "../../components/Footer/Footer";
import axios from 'axios';

const AddNewAction = () => {
  const navigate = useNavigate();
  const [selectedResources, setSelectedResources] = useState([]);
  const [locationInput, setLocationInput] = useState("");
  const [isLocationValid, setIsLocationValid] = useState(true);
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); 
  const [locations, setLocations] = useState([]);
    

  const resources = ["water", "food", "shelter", "first aid kit", "heater", "sand"];

  const toggleResource = (resource) => {
    setSelectedResources((prevSelected) =>
      prevSelected.includes(resource)
        ? prevSelected.filter((res) => res !== resource)
        : [...prevSelected, resource]
    );
  };

  const handleLocationInputChange = (e) => {
    const value = e.target.value;
    setLocationInput(value);
    // Check if the input matches any location in the list
    setIsLocationValid(locations.includes(value));
  };

  useEffect(() => {   

    const fetchLocations = async () => {
      try {
        const response = await axios.get("http://localhost:8081/location/settlementnames"); 
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


  return (
    <div className="page-container">
    <AnonHeader />
    <div className="header-container">
      <button className="back-button" onClick={() => navigate('/home')}>
        &lt; Back to newsfeed
      </button>
      <h1>ADD NEW ACTION</h1>
    </div>
  
    <div className="add-action-container">
      <div className="add-action-form">
        <div className="left-column">
          {/* Lokacija }

          <label htmlFor="actionName">Add a name:</label>
            <input
              id="actionName"
              placeholder="Action name"
              className="description-input"
              type="text"
            />

          <p className="section-title">Location</p>
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
          {/* Dostupni resursi }
          <div className="form-section resources-section">
            <label>AVAILABLE RESOURCES</label>
            <div className="resources-buttons">
              {resources.map((resource) => (
                <button
                  key={resource}
                  className={`resource-button ${
                    selectedResources.includes(resource) ? "selected" : ""
                  }`}
                  onClick={() => toggleResource(resource)}
                >
                  {resource}
                </button>
              ))}
            </div>
          </div>
        </div>
  
        <div className="right-column">
          {/* Dodavanje opisa }
          <div className="form-section description-section">
            <label htmlFor="description">ADD A DESCRIPTION</label>
            <textarea
              id="description"
              placeholder="add description"
              className="description-input"
            ></textarea>
          </div>
        </div>
      </div>
    </div>
    <Footer />
  </div>
  
  );
};

export default AddNewAction;
*/