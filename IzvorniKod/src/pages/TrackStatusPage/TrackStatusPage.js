import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import './TrackStatusPage.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ProfileHeader from "../../components/ProfileHeader/ProfileHeader";
import BackButton from '../../components/BackButton/BackButton';

const TrackStatus = () => {
    const { reportID } = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const isAnon = location.state?.isAnon || false;  // Ako nema state-a, pretpostavi da nije anoniman

    const [status, setStatus] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [inputReportID, setInputReportID] = useState("");

    useEffect(() => {
        if (reportID) {
            const fetchStatus = async () => {
                setLoading(true);
                setError(null);
                try {
                    const response = await axios.get(`https://safebear-backend.onrender.com/reports/${reportID}`);
                    setStatus(response.data.status);
                } catch (err) {
                    setError("Failed to fetch the report status.");
                } finally {
                    setLoading(false);
                }
            };
            fetchStatus();
        }
    }, [reportID]);

    const handleTrackStatus = () => {
        if (inputReportID.trim() !== "") {
            navigate(`/track-status/${inputReportID}`);
        } else {
            alert("Please enter a valid report ID.");
        }
    };

    return (
        <div className="track-status">
            {/* Ovdje se dinamički prikazuje header */}
            {isAnon ? <AnonHeader /> : <ProfileHeader />}

            <div className="trackstatus-content">
                <BackButton />
                <h1>Track Report Status</h1>

                <div className="trackstatus-input">
                    <input
                        type="text"
                        placeholder="Enter your Report ID"
                        value={inputReportID}
                        onChange={(e) => setInputReportID(e.target.value)}
                        className="report-id-input"
                    />
                    <button onClick={handleTrackStatus} className="track-button">
                        Track Status
                    </button>
                </div>

                {reportID && (
                    <div className="trackstatus-text">
                        <p>Report ID: <strong>{reportID}</strong></p>
                        {loading && <p>Loading status...</p>}
                        {error && <p>{error}</p>}
                        {!loading && !error && <p>Status: <strong>{status}</strong></p>}
                    </div>
                )}
            </div>
        </div>
    );
};

export default TrackStatus;
