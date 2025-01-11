import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AddNewAction.css"; 
import AnonHeader from '../../components/AnonHeader/AnonHeader';
import Footer from "../../components/Footer/Footer";

const AddNewAction = () => {
  const navigate = useNavigate();
  const [selectedResources, setSelectedResources] = useState([]);

  const resources = ["water", "food", "shelter", "first aid kit", "heater", "sand"];

  const toggleResource = (resource) => {
    setSelectedResources((prevSelected) =>
      prevSelected.includes(resource)
        ? prevSelected.filter((res) => res !== resource)
        : [...prevSelected, resource]
    );
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
      <div className="add-action-form">
        <div className="left-column">
          {/* Lokacija */}
          <div className="form-section location-section">
            <label htmlFor="location">LOCATION</label>
            <input
              type="text"
              id="location"
              placeholder="type your location..."
              className="location-input"
            />
          </div>
  
          {/* Dostupni resursi */}
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
          {/* Dodavanje opisa */}
          <div className="form-section description-section">
            <label htmlFor="description">ADD A DESCRIPTION</label>
            <textarea
              id="description"
              placeholder="add images"
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
