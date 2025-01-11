import './HomePage.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const HomePage = () => {
    const [showInfo, setShowInfo] = useState(false);
    const [reports, setReports] = useState([]);
    const [reportsLoading, setReportsLoading] = useState(true);
    const [reportsError, setReportsError] = useState(null);

    const [actions, setActions] = useState([]);
    const [actionsLoading, setActionsLoading] = useState(true);
    const [actionsError, setActionsError] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        // Fetch Reports
        const fetchReports = async () => {
            try {
                const response = await axios.get("http://localhost:8081/reports");
                setReports(response.data);
            } catch (error) {
                console.error("Error fetching reports:", error);
                setReportsError("Failed to load reports.");
            } finally {
                setReportsLoading(false);
            }
        };

        // Fetch Actions
        const fetchActions = async () => {
            try {
                const response = await axios.get("http://localhost:8081/actions");
                setActions(response.data);
            } catch (error) {
                console.error("Error fetching actions:", error);
                setActionsError("Failed to load actions.");
            } finally {
                setActionsLoading(false);
            }
        };

        fetchReports();
        fetchActions();
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
                <button className="information-button" onClick={handleInformation}>INFORMATION</button>
                <button className="report-button" onClick={handleAnonymousReport}>REPORT</button>
                <button className="see-map-button" onClick={navigateToMap}>SEE MAP</button>
            </div>

            <div className="PageBodyHome">
                {showInfo && (
                    <div className="LeftSectionHome">
                        <Info />
                    </div>
                )}

                <div className="MiddleSectionHome">
                    {reportsLoading ? (
                        <p>Loading reports...</p>
                    ) : reportsError ? (
                        <p className="error">{reportsError}</p>
                    ) : (
                        <ReportComponent reports={reports} />
                    )}
                </div>

                <div className="RightSectionHome">
                    <div className='aid-section-name'>
                        <h2>AID ACTIONS:</h2>
                    </div>
                    {actionsLoading ? (
                        <p>Loading actions...</p>
                    ) : actionsError ? (
                        <p className="error">{actionsError}</p>
                    ) : (
                        <AidActions actions={actions} />
                    )}
                </div>
            </div>
        </div>
    );
};

export default HomePage;
