import './HomePageAuthorities.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const HomePageAuthorities = () => {
    const [reports, setReports] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [visibleReports, setVisibleReports] = useState(5);

    const [actions, setActions] = useState([]);
    const [actionsLoading, setActionsLoading] = useState(true);
    const [actionsError, setActionsError] = useState(null);
    const [visibleActions, setVisibleActions] = useState(5);

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
                setError("Failed to load data.");
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

    const navigateToMap = () => {
        navigate('/map');
    };

    const handleDownloadReports = async () => {
        try {
            const response = await axios.get("http://localhost:8081/reports/download", {
                responseType: "blob", 
                withCredentials: true, 
            });

            const blob = new Blob([response.data], { type: "application/json" });
            const url = URL.createObjectURL(blob);

            const link = document.createElement("a");
            link.href = url;
            link.download = "reports.json";
            link.click();

            URL.revokeObjectURL(url);
        } catch (error) {
            console.error("Error downloading reports:", error);
        }
    };

    const loadMoreReports = () => {
        setVisibleReports((prev) => prev + 5);
    };

    const loadMoreActions = () => {
        setVisibleActions((prev) => prev + 5);
    };

    const handleAnonymousReport = () => {
        navigate('/report');
    };

    return (
        <div className="HomePageAuthorities">
            <div className="header">
                <AnonHeader />
            </div>

            <div className="buttonsHomePageAuthorities">
            <button className="report-button" onClick={handleAnonymousReport}>REPORT</button>
                <button className="see-map-button" onClick={navigateToMap}>SEE MAP</button>
            </div>

            <div className="PageBodyAuthorities">
                <div className="LeftSectionAuthorities">
                    <div className="StatisticalAnalysisSection">
                        <h3>Statistical Analysis</h3>
                        <p>Download information about all reports.</p>
                        <button onClick={handleDownloadReports} className="download-button">
                            Download Reports as JSON
                        </button>
                    </div>
                </div>

                <div className="MiddleSectionAuthorities">
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

                <div className="RightSectionAuthorities">
                    <div className='aid-section-name'>
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

export default HomePageAuthorities;
