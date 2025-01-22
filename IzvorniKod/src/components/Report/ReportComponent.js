import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ReportComponent = (props) => {
  const reports = props.reports;
  const isAdmin = 0; // Assuming `isAdmin` is a boolean
  const navigate = useNavigate();

  // State to manage report statuses
  const [statuses, setStatuses] = useState({});

  // Update statuses state whenever the reports prop changes
  useEffect(() => {
    const initialStatuses = reports.reduce((acc, report) => {
      acc[report.id] = report.status; // Map report ID to its initial status
      return acc;
    }, {});
    setStatuses(initialStatuses); // Update the statuses state
  }, [reports]);

  const handleReportClick = (reportId) => {
    navigate(`/reportopen/${reportId}`);
  };

  const handleChangeStatus = async (reportId) => {
    const newStatus = statuses[reportId]; // Get the current status for the report
    try {
      const response = await axios.patch(
        `http://localhost:8081/reports/${reportId}/status`,
        { status: newStatus }, // Sending ReportStatusDTO
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true,
        }
      );

      if (response.status === 200) {
        alert("Status updated successfully!");
      } else {
        throw new Error("Unexpected response from server");
      }
    } catch (error) {
      console.error("Error updating status:", error);
      alert("Failed to update status");
    }
  };

  const handleStatusChange = (reportId, newStatus) => {
    setStatuses((prevStatuses) => ({
      ...prevStatuses,
      [reportId]: newStatus, // Update the status for the specific report
    }));
  };

  return (
    <div>
      {reports.map((report) => (
        <div key={report.id} className="Report">
          <div className="ReportDateName">
            <span className="aid-date">
              {new Date(report.time).toLocaleDateString()}
            </span>
            <span className="username">{report.user.username}</span>
          </div>

          <h2
            onClick={() => handleReportClick(report.id)}
            className="clickable-title"
          >
            {report.disaster.disasterType} REPORT -{" "}
            {report.disaster?.settlement?.settlementName ?? report.geographicCoordinates} AREA
          </h2>
          <p>{report.shortDescription}</p>

          
          
        </div>
      ))}
    </div>
  );
};

export default ReportComponent;
