import './HomePageAdmin.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const HomePageAdmin = () => {
    const [reports, setReports] = useState([]);
    const [loading, setLoading] = useState(true);
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
                const reportsResponse = await axios.get("http://localhost:8081/reports");
                setReports(reportsResponse.data);
                setLoading(false);
            } catch (error) {
                console.error("Error fetching data:", error);
                setError("Failed to load data");
                setLoading(false);
            }
        };
        fetchReports();
    }, []);

    const navigateToMap = () => {
        navigate('/map');
    };

    const handleAnonymousReport = () => {
        navigate('/report');
    };

    return (
        <div className="HomePageAdmin">
            <div className="header">
                <AnonHeader />
            </div>

            <div className="buttonsHomePageAdmin">
                <button className="report-button" onClick={handleAnonymousReport}>REPORT</button>
                <button className="see-map-button" onClick={navigateToMap}>SEE MAP</button>
            </div>

            <div className="PageBodyAdmin">
                <div className="MiddleSectionAdmin">
                    <h2>Reports</h2>
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
};

export default HomePageAdmin;
