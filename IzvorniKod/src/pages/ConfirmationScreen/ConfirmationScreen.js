import React from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import './ConfirmationScreen.css';
import AnonHeader from '../../components/AnonHeader/AnonHeader';
import BackButton from '../../components/BackButton/BackButton';

function ConfirmationScreen() {
    const navigate = useNavigate();

    //podesiti dohvacanje reportIDja
    //ako se reportID daje preko lokacije
    //const location = useLocation();
    //const reportID = location.state?.reportID;

    const {reportID} = useParams();

    const handleTrackStatus = () => {
        navigate(`/track-status/${reportID}`); // do tracking pagea po reportIDju
    };


    const handleReportNeedClick = () => {
        navigate('/report-need'); 
    };

    const handleGoHomeClick = () => {
        navigate('/home'); 
    };

    return (
    <div className="confirmation-screen">
        <AnonHeader /> 
        
        <div className='back-button-confirmation'>
                <BackButton /> 
        </div>
        <div className='confirmation-screen-content'> 
            
            
            <h1>Report Submitted Successfully!</h1>
            <p>Thank you for your report.</p>

            {reportID ? (
                    <div className='report-id-info'>
                        <p>Your Report ID: <strong>{reportID}</strong></p>
                        <button className="tracking-button" onClick={handleTrackStatus}>
                            Track Status
                        </button>
                    </div>
                ) : (
                    <p>Error: Report ID is missing.</p>
            )}


            <div className="button-container">
                <button className="report-need-button" onClick={handleReportNeedClick}>
                    Report a Need
                </button>
                <button className="go-home-button" onClick={handleGoHomeClick}>
                    Go to Home
                </button>
            </div>
        </div>
    </div>
    );
}

export default ConfirmationScreen;
