import './HomePageAdmin.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import BackButton from "../../components/BackButton/BackButton";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ReportComponentAdmin from '../../components/Report/ReportComponentAdmin';
import ProfileHeader from '../../components/ProfileHeader/ProfileHeader';

const HomePageAdmin = () => {
    const [reports, setReports] = useState([]);
    const [denied, setDenied] = useState([]);
    const [processing, setProcessing] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // Stanja za collapse sekcije
    const [showAccepted, setShowAccepted] = useState(true); // Default otvoreno
    const [showDenied, setShowDenied] = useState(false);
    const [showProcessing, setShowProcessing] = useState(false);

    const [searchReportId, setSearchReportId] = useState(''); // State for search input
    const [searchedReport, setSearchedReport] = useState(null); // State for the searched report

    useEffect(() => {
        const fetchReports = async () => {
            try {
                const [accepted, denied, processing] = await Promise.all([
                    axios.get("http://localhost:8081/reports/accepted", { withCredentials: true }),
                    axios.get("http://localhost:8081/reports/denied", { withCredentials: true }),
                    axios.get("http://localhost:8081/reports/processing", { withCredentials: true })
                ]);
                setReports(accepted.data);
                setDenied(denied.data);
                setProcessing(processing.data);
            } catch (error) {
                console.error("Error fetching data:", error);
                setError("Failed to load data");
            } finally {
                setLoading(false);
            }
        };
        fetchReports();
    }, []);

    // Handle search input change
    const handleInputChange = (e) => {
        setSearchReportId(e.target.value);
    };

    // Handle search by report ID
    const handleSearch = async () => {
        if (!searchReportId.trim()) {
            alert("Please enter a valid report ID.");
            return;
        }

        try {
            setLoading(true);
            const response = await axios.get(`http://localhost:8081/reports/${searchReportId}`, {
                withCredentials: true,
            });
            setSearchedReport(response.data);
        } catch (error) {
            console.error("Error searching report:", error);
            setError("No report found with the given ID.");
        } finally {
            setLoading(false);
        }
    };

    // Clear search and show all reports
    const handleClearSearch = () => {
        setSearchReportId(''); // Clear the search input field
        setSearchedReport(null); // Reset the searched report state
        setError(null); // Clear any error message
    };

    return (
        <div className="HomePageAdmin">
            <div className="header">
                <ProfileHeader />
            </div>

            <div className="buttonsHomePageAdmin">
                <BackButton></BackButton>
                <button className="edit-users" onClick={() => navigate('/editusersadmin')}>EDIT USERS</button>
                <button className="see-map-button-admin" onClick={() => navigate('/map')}>SEE MAP</button>
            </div>

            <div className="PageBodyAdmin">
                <div className="ReportSectionAdmin">
                    <h2>Reports</h2>

                    <div className="search">
                        <input
                            type="text"
                            value={searchReportId}
                            onChange={handleInputChange}
                            placeholder="Search reports by ID"
                        />
                        <button onClick={handleSearch} className="search-button">
                            Search
                        </button>
                        {searchedReport && (
                            <button onClick={handleClearSearch} className="clear-search-button">
                                Clear Search
                            </button>
                        )}
                    </div>

                    {searchedReport ? (
                        <div className="SearchedReport">
                            <h3>Searched Report:</h3>
                            <ReportComponentAdmin reports={[searchedReport]} />
                        </div>
                    ) : (
                        <>
                            {/* Accepted Reports */}
                            <div className="CollapsibleSection">
                                <h4 onClick={() => setShowAccepted(!showAccepted)} className="CollapsibleHeader">
                                    Accepted Reports {showAccepted ? "⬆" : "⬇"}
                                </h4>
                                {showAccepted && (
                                    <div className="ReportContentAdmin">
                                        {loading ? <p>Loading...</p> : <ReportComponentAdmin reports={reports} />}
                                    </div>
                                )}
                            </div>

                            {/* Denied Reports */}
                            <div className="CollapsibleSection">
                                <h4 onClick={() => setShowDenied(!showDenied)} className="CollapsibleHeader">
                                    Denied Reports {showDenied ? "⬆" : "⬇"}
                                </h4>
                                {showDenied && (
                                    <div className="ReportContentAdmin">
                                        {loading ? <p>Loading...</p> : <ReportComponentAdmin reports={denied} />}
                                    </div>
                                )}
                            </div>

                            {/* Processing Reports */}
                            <div className="CollapsibleSection">
                                <h4 onClick={() => setShowProcessing(!showProcessing)} className="CollapsibleHeader">
                                    Processing Reports {showProcessing ? "⬆" : "⬇"}
                                </h4>
                                {showProcessing && (
                                    <div className="ReportContentAdmin">
                                        {loading ? <p>Loading...</p> : <ReportComponentAdmin reports={processing} />}
                                    </div>
                                )}
                            </div>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
};

export default HomePageAdmin;
