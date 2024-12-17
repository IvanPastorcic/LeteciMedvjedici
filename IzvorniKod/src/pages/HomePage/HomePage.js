import './HomePage.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

//import Report from './Report';
//import { BrowserRouter as Router, Route, Routes} from "react-router-dom";

const HomePage = () => {

    const [showInfo, setShowInfo] = useState(false);
    const [reports, setReports] = useState([]); 
    const [loading, setLoading] = useState(true); // Loading state
    const [error, setError] = useState(null); 
    const navigate = useNavigate();
//dummy data samo za prikaz
    const [aids, setAids] = useState([  
        {id: 1, date : "26.10.2024", organisationName: "THE RED CROSS", aidInfo: "informacije o sklonistima"},
        {id: 2, date : "27.10.2024", organisationName: "ORGANISATION2", aidInfo: "informacije o HRANI"},
        {id: 3, date : "28.10.2024", organisationName: "ORGANISATION3", aidInfo: "informacije o VODI"}
    ])

    useEffect(() => {
        
        const fetchReports = async () => {
            try {
                const response = await axios.get("https://safebear-backend.onrender.com/reports"); 
                setReports(response.data); 
                setLoading(false); 
            } catch (error) {
                console.error("Error fetching reports:", error);
                setError("Failed to load reports"); 
                setLoading(false); 
            }
        };
        fetchReports(); 
    }, []); 


    

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };

    const navigateToMap = () => {
        navigate('/map'); 
    };

    const handleInformation = () => {
        setShowInfo(!showInfo); 
    };
    return ( 
            <div className="HomePage">
                <div className='header'>
                    <AnonHeader /> 
                </div>
            
            <div className="buttonsHomePage">
                <button className="information-button"
                onClick={handleInformation}> INFORMATION </button>
                <button className="report-button" 
                onClick={handleAnonymousReport}> REPORT </button>
                <button className="see-map-button"
                onClick={navigateToMap}> see map </button>
            </div>
                    <div className="PageBodyHome">
                        
                    {showInfo && (
                    
                        <div className="LeftSectionHome">
                        <Info />
                            
                        </div>
                        )}

                        <div className="MiddleSectionHome">
                           

                            {loading ? (
                                <p>Loading reports...</p> 
                            ) : error ? (
                                <p>{error}</p> 
                            ) : (
                                <ReportComponent reports={reports} /> 
                            )}
                        </div>
                        
                        <div className="RightSectionHome">
                            <div className='aid-section-name'>
                                <h2>AID ACTIONS:</h2>
                            </div>
                            
                            <br /> 
                            <AidActions aids={aids}/> 
                        </div>

                    </div>
                
                
            </div>
 
     );
}
 
export default HomePage;