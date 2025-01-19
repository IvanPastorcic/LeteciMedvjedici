import './EditUsersAdmin.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import ProfileComponent from "../../components/ProfileComponent/ProfileComponent";
import BackButton from "../../components/BackButton/BackButton";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const EditUsersAdmin = () => {
    const [reports, setReports] = useState([]);
    const [denied, setDenied] = useState([]);
    const [processing, setProcessing] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const [searchReportId, setSearchReportId] = useState(''); // State for search input
    const [searchedReport, setSearchedReport] = useState(null); // State for the searched report

    // Fetch reports on mount
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

    const navigateToMap = () => {
        navigate('/map');
    };
    const navigateToHomeAdmin = () => {
        navigate('/homeadmin');
    };

    return (
        <div className="EditUsersAdmin">
            <div className="header">
                <AnonHeader />
            </div>

            <div className="buttonsHomePageAdmin">
                <BackButton />
                <button className="edit-users" onClick={navigateToHomeAdmin}>
                    EDIT REPORTS
                </button>
                <button className="see-map-button-admin" onClick={navigateToMap}>
                    SEE MAP
                </button>
            </div>

            <div className="PageBodyAdmin">
                <div className="ReportSectionAdmin">
                    <div>
                        <h2>Users</h2>
                        <div className="search">
                            <input
                                type="text"
                                value={searchReportId}
                                onChange={handleInputChange}
                                placeholder="Search users by username"
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
                        <div className="search">
                            <input
                                type="text"
                                value={searchReportId}
                                onChange={handleInputChange}
                                placeholder="Search users by email"
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
                    </div>

                    <div className="ReportsAdmin">
                        {searchedReport ? (
                            <div className="SearchedReport">
                                <h3>Searched Report:</h3>
                                <ProfileComponent reports={[searchedReport]} />
                            </div>
                        ) : (
                            <>
                                <div className="AcceptedReports">
                                    <h4>Users:</h4>
                                    {loading ? (
                                        <p>Loading the users...</p>
                                    ) : error ? (
                                        <p>{error}</p>
                                    ) : (
                                        <ProfileComponent reports={reports} />
                                    )}
                                </div>

                            </>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EditUsersAdmin;
