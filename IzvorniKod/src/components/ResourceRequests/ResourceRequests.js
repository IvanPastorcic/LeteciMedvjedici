import React, { useState } from "react";
import "./ResourceRequests.css";

function ResourceRequests() {
  const [filter, setFilter] = useState("");

  // Sample data za sad
  const resourceRequests = [
    {
      location: "LOCATION",
      needs: { water: "50L", "first aid kit": "30" },
    },
    {
      location: "LOCATION",
      needs: { water: "50L", "first aid kit": "30" },
    },
    {
      location: "LOCATION",
      needs: { water: "50L", "first aid kit": "30" },
    },
  ];

  const filteredRequests = resourceRequests.filter((request) =>
    filter
      ? Object.keys(request.needs).includes(filter.toLowerCase())
      : true
  );

  const handleFilterClick = (resource) => {
    setFilter(resource === filter ? "" : resource); 
  };

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
        {filteredRequests.map((request, index) => (
          <div key={index} className="request-card">
            <p className="request-location">{request.location}</p>
            <p className="request-needs">
              needs:{" "}
              {Object.entries(request.needs)
                .map(([key, value]) => `${key} ${value}`)
                .join(", ")}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ResourceRequests;
