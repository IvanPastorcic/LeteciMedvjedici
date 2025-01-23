import './ReportOpen.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import BackButton from '../../components/BackButton/BackButton';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ReportOpen = () => {
    const [report, setReport] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
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
                <BackButton />
            </div>

            <div className="PageBodyHomeReportOpen">
                <div className="ReportDetails">
                    <div className="ReportDateName">
                        <p className="aid-date">{new Date(report.time).toLocaleDateString()}</p>
                        <p className="username">{report.user ? report.user.username : "Anonymous"}</p>
                    </div>

                    <h2>
                        {report.disaster ? `${report.disaster.disasterType} REPORT - ${report.disaster.settlement ? report.disaster.settlement.settlementName : "Unknown Area"}` : "Unknown Disaster"}
                    </h2>

                    <div className='ReportAndDescription'>
                        <p>ID: {report.id}</p>
                        <p>{report.shortDescription}</p>
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
                        <p><strong>Location:</strong> {report.location ? `${report.location.latitude}, ${report.location.longitude}` : "Unknown location"}</p>                    </div>
                </div>
            </div>
        </div>
    );
};

export default ReportOpen;
