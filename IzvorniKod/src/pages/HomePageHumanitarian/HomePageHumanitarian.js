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
    const [visibleReports, setVisibleReports] = useState(5);

    const [actions, setActions] = useState([]);
    const [actionsLoading, setActionsLoading] = useState(true);
    const [actionsError, setActionsError] = useState(null);
    const [visibleActions, setVisibleActions] = useState(5);

    const [needs, setNeeds] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchReports = async () => {
            try {
                const response = await axios.get("http://localhost:8081/reports/accepted", {
                    withCredentials: true,
                  });
                setReports(response.data);
            } catch (error) {
                console.error("Error fetching reports:", error);
                setError("Failed to load data");
            } finally {
                setLoading(false);
            }
        };

        const fetchActions = async () => {
            try {
                const response = await axios.get("http://localhost:8081/actions", {
                    withCredentials: true,
                  });
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
        const fetchNeeds = async () => {
            try {
                const response = await axios.get("http://localhost:8081/needs/all");
                setNeeds(response.data);
            } catch (error) {
                console.error("Error fetching needs:", error);
                setError("Failed to load data");
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

    const loadMoreReports = () => {
        setVisibleReports((prev) => prev + 5);
    };

    const loadMoreActions = () => {
        setVisibleActions((prev) => prev + 5);
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
                    <ResourceRequests needs={needs} />
                </div>

                <div className="MiddleSectionHumanitarian">
                    <h2>Reports</h2>
                    {loading ? (
                        <p>Loading reports...</p>
                    ) : error ? (
                        <p>{error}</p>
                    ) : (
                        <>
                            <ReportComponent reports={reports.slice(0, visibleReports)} />
                            {visibleReports < reports.length && (
                                <button className="load-more-button" onClick={loadMoreReports}>
                                    Load More Reports
                                </button>
                            )}
                        </>
                    )}
                </div>

                <div className="RightSectionHumanitarian">
                    <div className="aid-section-name">
                        <h2>AID ACTIONS:</h2>
                    </div>
                    {actionsLoading ? (
                        <p>Loading actions...</p>
                    ) : actionsError ? (
                        <p className="error">{actionsError}</p>
                    ) : (
                        <>
                            <AidActions actions={actions.slice(0, visibleActions)} />
                            {visibleActions < actions.length && (
                                <button className="load-more-button" onClick={loadMoreActions}>
                                    Load More Actions
                                </button>
                            )}
                        </>
                    )}
                </div>
            </div>
        </div>
    );
};

export default HomePageHumanitarian;
