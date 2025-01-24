import './ReportOpenAdmin.css';
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
    const isAdmin = 1;
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
                        <text className="username">{report.user ? report.user.username : "Anonymous"}</text>
                    </div>

                    <h2>
                        {report.disaster ? `${report.disaster.disasterType} REPORT - ${report.disaster.settlement ? report.disaster.settlement.settlementName : "Unknown Area"}` : "Unknown Disaster"}
                    </h2>

                    <div className='ReportAndDescription'>
                        <text>ID: {report.id}</text>
                        <text>{report.shortDescription}</text>
                    </div>

                    <div className="ReportExtendedDetails">
                        <p>Additional Information:</p>
                        <p>{report.additionalInformation}</p>
                    </div>

                    <div className="ReportImages">
                        {report.images && report.images.map((image, index) => (
                            <img key={index} src={image.url} alt={`Report Image ${index + 1}`} />
                        ))}
                    </div>

                    <div className="ReportCoordinates">
                        <p><strong>Location:</strong> {report.location ? `${report.location.latitude}, ${report.location.longitude}` : "Unknown location"}</p>
                    </div>

                    {isAdmin && (
                        <div className="adminStatus">
                            <div className='currentStatus'>
                                <p>Current status:</p>
                                <p>{report.status}</p>
                            </div>
                            <div className="changeStatusContainer">
                                <select name="reportStatus">
                                    <option value="Accepted">Accepted</option>
                                    <option value="Processing">Processing...</option>
                                    <option value="Denied">Denied</option>
                                </select>
                                <button className='changeStatus'>Change status</button>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default ReportOpen;
