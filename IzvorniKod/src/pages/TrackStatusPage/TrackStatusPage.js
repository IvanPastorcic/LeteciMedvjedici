import React, { useState, useEffect } from 'react';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import BackButton from '../../components/BackButton/BackButton';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './TrackStatusPage.css';


//podesiti dohvacanje report id i statusa


const TrackStatus = () => {
    const { reportID } = useParams();
    const [status, setStatus] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchStatus = async () => {
            try {
                const response = await axios.get(`http://localhost:8081/reports/${reportID}`);
                setStatus(response.data.status); // ovisi kako backend vraca statuse
                setLoading(false);
            } catch (err) {
                setError("Failed to fetch the report status.");
                setLoading(false);
            }
        };

        fetchStatus();
    }, [reportID]);

    return (
        <div className="track-status">
            <AnonHeader /> 

            <div className="trackstatus-content">
                <BackButton /> 
                <h1>Track Report Status</h1>

                <div className="trackstatus-text" >
                    <p>Report ID: <strong>{reportID}</strong></p>
                    {loading && <p>Loading status...</p>}
                    {error && <p>{error}</p>}
                    {!loading && !error && (
                        <p>Status: <strong>{status}</strong></p>
                    )}
                </div>

            </div>
        </div>
    );
};

export default TrackStatus;
