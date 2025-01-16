import './ReportOpen.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaArrowLeft, FaFire, FaWater, FaBolt, FaMountain, FaHome } from "react-icons/fa";
import BackButton from '../../components/BackButton/BackButton';


const ReportOpen = () => {

    
    const [showInfo, setShowInfo] = useState(false);
    const [report, setReport] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

//dummy data samo za prikaz

   

    const [aids, setAids] = useState([  
        {id: 1, date : "26 Oct 2024 10:35", organisationName: "THE RED CROSS", aidInfo: "informacije o sklonistima"},
        {id: 2, date : "27 Oct 2024 11:00", organisationName: "ORGANISATION2", aidInfo: "informacije o HRANI"},
        {id: 3, date : "28 Oct 2024 15:58", organisationName: "ORGANISATION3", aidInfo: "informacije o VODI"}
    ])


    const { reportId } = useParams();

    
    useEffect(() => {
        
        const fetchReports = async () => {
            try {
                const response = await axios.get(`http://localhost:8081/reports/${reportId}`); 
                setReport(response.data); 
                setLoading(false); 
            } catch (error) {
                console.error("Error fetching reports:", error);
                setError("Failed to load reports"); 
                setLoading(false); 
            }
        };
        fetchReports(); 
    }, [reportId]); 

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;
    

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
            <div className="ReportOpen">
                <div className='header'>
                    <AnonHeader /> 
                </div>

                	<div className="BackReportOpen">
                        <BackButton/>
                    </div>
                    <div className="PageBodyHomeReportOpen">
                <div className="ReportDetails">
                    <div className="ReportDateName">
                        <text className="aid-date">{new Date(report.time).toLocaleDateString()}</text>
                        <text className="username">{report.user.username}</text>
                    </div>
                    <h2>
                        {report.disaster.disasterType} REPORT - {report.disaster.settlement.settlementName} AREA
                    </h2>
                    <text>{report.shortDescription}</text>
                    <div className="ReportExtendedDetails">
                        <p>Additional Information:</p>
                        <p>{report.additionalInformation}</p>
                    </div>
                    <div className="ReportImages">
                        {report.images && report.images.map((image, index) => (
                            <img key={index} src={image.url} alt={`Report Image ${index + 1}`} />
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ReportOpen;