import React from 'react';
import { useNavigate } from 'react-router-dom';
import './ConfirmationScreen.css';
import AnonHeader from '../../components/AnonHeader/AnonHeader';

function ConfirmationScreen() {
    const navigate = useNavigate();

    const handleReportNeedClick = () => {
        navigate('/report-need'); // Replace with the correct route for reporting needs
    };

    const handleGoHomeClick = () => {
        navigate('/home'); // Replace with the correct route for the home page
    };

    return (
    <div className="confirmation-screen">
        <AnonHeader /> 
        <h1>Report Submitted Successfully!</h1>
        <p>Thank you for your report.</p>
        <div className="button-container">
        <button className="report-need-button" onClick={handleReportNeedClick}>
            Report a Need
        </button>
        <button className="go-home-button" onClick={handleGoHomeClick}>
            Go to Home
        </button>
        </div>
    </div>
    );
}

export default ConfirmationScreen;
