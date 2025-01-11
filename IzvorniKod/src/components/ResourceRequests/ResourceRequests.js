import React, { useState } from "react";
import "./ResourceRequests.css";

function ResourceRequests(props) {
  const needs = props.needs;

  const [filter, setFilter] = useState("");

  const handleFilterClick = (resource) => {
    setFilter(resource === filter ? "" : resource); // Toggle filter
  };

  // Filter the needs based on the selected filter
  const filteredNeeds = filter
    ? needs.filter((need) => need.needType.toLowerCase() === filter.toLowerCase())
    : needs;

  return (
    <div className="resource-requests-container">
      <div className="filter-container">
        <p className="filter-title">Filter by resource:</p>
        <div className="filter-buttons">
          {["water", "food", "shelter", "first aid kit", "heater", "sand"].map(
            (resource) => (
              <button
                key={resource}
                className={`filter-button ${
                  filter === resource ? "active" : ""
                }`}
                onClick={() => handleFilterClick(resource)}
              >
                {resource}
              </button>
            )
          )}
        </div>
      </div>

      <div className="requests-container">
        {filteredNeeds.map((need) => (
          <div key={need.id} className="request-card">
            <p className="request-user">{need.appUser.username}</p>
            <div className="request-needs">
              <p>Need type: {need.needType}</p>
              <p>Need quantity: {need.quantity}</p>
              <p>Need location: {need.address}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ResourceRequests;
