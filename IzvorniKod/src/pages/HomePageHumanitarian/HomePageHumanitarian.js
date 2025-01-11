import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import ResourceRequests from "../../components/ResourceRequests/ResourceRequests";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './HomePageHumanitarian.css';

const HomePageHumanitarian = () => {
    const [reports, setReports] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [needs, setNeeds] = useState([]);
    const navigate = useNavigate();
    const [reportsLoading, setReportsLoading] = useState(true);
    const [reportsError, setReportsError] = useState(null);

    const [actions, setActions] = useState([]);
    const [actionsLoading, setActionsLoading] = useState(true);
    const [actionsError, setActionsError] = useState(null);
    

/*
//dummy data samo za prikaz
    const [aids, setAids] = useState([  
        {id: 1, date : "26.10.2024", organisationName: "THE RED CROSS", aidInfo: "informacije o sklonistima"},
        {id: 2, date : "27.10.2024", organisationName: "ORGANISATION2", aidInfo: "informacije o HRANI"},
        {id: 3, date : "28.10.2024", organisationName: "ORGANISATION3", aidInfo: "informacije o VODI"}
    ])*/

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

    useEffect(() => {
        const fetchNeeds = async() => {
            try {
                const needsResponse = await axios.get("http://localhost:8081/needs/all");
                setNeeds(needsResponse.data);
                setLoading(false);
            } catch (error) {
                console.error("Error fetching data:", error);
                setError("Failed to load data");
                setLoading(false);
            }
        };
        fetchNeeds();
    }, []);

    const navigateToMap = () => {
        navigate('/map');
    };

    const handleAnonymousReport = () => {
        navigate('/report');
    };

    const navigateToManageResources = () => {
        navigate('/manageresource');
    };

    const navigateToAddNewAction = () => {
        navigate('/addnewaction');
    };

    return (
        <div className="HomePageHumanitarian">
            <div className="header">
                <AnonHeader />
            </div>

            <div className="buttonsHomePageHumanitarian">
                <button className="report-button" onClick={handleAnonymousReport}>REPORT</button>
                <button className="manage-resources-button" onClick={navigateToManageResources}>MANAGE RESOURCES</button>
                <button className="add-new-action-button" onClick={navigateToAddNewAction}>ADD NEW ACTION</button>
                <button className="see-map-button" onClick={navigateToMap}>SEE MAP</button>
                
            </div>

            <div className="PageBodyHumanitarian">
                <div className="LeftSectionHumanitarian">
                    <ResourceRequests needs = {needs} />
                </div>

                <div className="MiddleSectionHumanitarian">
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

export default HomePageHumanitarian;
